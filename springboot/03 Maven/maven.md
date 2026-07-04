# Maven

## What is Maven?

Maven is a **build automation and project management tool** for Java projects.
Think of it as the assistant that takes your source code and turns it into a
finished, runnable artifact (a `.jar` or `.war` file) — while also handling
all the boring, repetitive stuff around that: downloading the libraries your
code depends on, compiling, running tests, and packaging everything up in a
standard, predictable way.

Every Maven project has a `pom.xml` ("Project Object Model") file at its root.
This is the project's blueprint — it declares:
- the project's name, version, and packaging type (jar/war/etc.)
- its **dependencies** (other libraries your code needs, e.g. Spring, JUnit)
- **plugins** that add extra build behavior (e.g. compiling with a specific
  Java version, running tests, creating an executable jar)

## Why do we use Maven?

Without a tool like Maven, you'd have to manage all of this by hand:

1. **Dependency management** — Java projects rely on external libraries.
   Manually downloading each `.jar` file, plus all of *their* dependencies,
   and keeping versions in sync is painful and error-prone. Maven reads your
   `pom.xml`, fetches the exact libraries you listed (and their transitive
   dependencies) from a central repository, and puts them on your classpath
   automatically.
2. **Standard project structure** — Every Maven project follows the same
   folder layout (`src/main/java`, `src/test/java`, `src/main/resources`,
   etc.). Once you know one Maven project, you know them all — no time
   wasted figuring out "where does the code live here?"
3. **Consistent, repeatable builds** — Running `mvn install` produces the
   same result on your machine, your teammate's machine, and the CI server,
   because the build steps are defined once in the `pom.xml` instead of
   being "whatever commands I happened to run."
4. **Lifecycle automation** — Maven bundles compiling, testing, packaging,
   and even deploying into one command instead of many manual steps.
5. **Easy to extend** — Plugins let you add capabilities (code coverage,
   static analysis, Docker image builds, etc.) without changing how you
   invoke the build.

## Steps to use Maven

1. **Install Maven** and confirm it's on your PATH:
   ```
   mvn -version
   ```
2. **Create a new project** (using the quickstart archetype), or open an
   existing one that already has a `pom.xml`:
   ```
   mvn archetype:generate -DgroupId=com.example -DartifactId=my-app \
       -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
   ```
3. **Understand the folder structure** Maven expects:
   ```
   my-app/
   ├── pom.xml
   └── src/
       ├── main/
       │   ├── java/        <- your source code
       │   └── resources/   <- config files, etc.
       └── test/
           └── java/        <- your test code
   ```
4. **Declare dependencies** in `pom.xml` inside the `<dependencies>` tag
   (e.g. Spring Boot starter, JUnit) — Maven downloads them for you.
5. **Build the project** by running lifecycle phases from the project's
   root folder (the one containing `pom.xml`):
   - `mvn compile` — compiles the source code
   - `mvn test` — runs the unit tests
   - `mvn package` — bundles compiled code into a `.jar`/`.war` in `target/`
   - `mvn install` — puts that package into your local Maven repository
     (`~/.m2`) so other local projects can depend on it
   - `mvn clean` — deletes the `target/` folder to start fresh
   (Each phase automatically runs the phases before it, e.g. `mvn package`
   also compiles and tests first.)
6. **Run/use the built artifact** — for a plain jar,
   `java -jar target/my-app-1.0.jar`; for a Spring Boot app,
   `mvn spring-boot:run` is the common shortcut during development.

## How to set up a Maven project with AWS

Once `mvn package` gives you a runnable jar (e.g. a Spring Boot app), the
simplest and most common way to get it running on AWS is to deploy it to an
**EC2** instance. Broad steps:

1. **Build the artifact locally first**
   ```
   mvn clean package
   ```
   This produces `target/my-app-1.0.jar`. Test it locally with
   `java -jar target/my-app-1.0.jar` before touching AWS.

2. **Launch an EC2 instance**
   - Go to the AWS Console → EC2 → "Launch Instance".
   - Pick an AMI (Amazon Linux 2023 or Ubuntu are common choices).
   - Choose an instance type (`t2.micro`/`t3.micro` is enough for learning
     and is free-tier eligible).
   - Create/select a **key pair** (`.pem` file) — this is what lets you SSH
     into the box, so keep it safe and never commit it to git.
   - Configure the **Security Group** (basically a firewall):
     - allow inbound **port 22** (SSH) from your IP
     - allow inbound **port 8080** (or whatever port your app listens on)
       from anywhere (`0.0.0.0/0`) if it should be publicly reachable

3. **Connect to the instance**
   ```
   ssh -i my-key.pem ec2-user@<EC2_PUBLIC_IP>
   ```

4. **Install Java on the instance** (Maven itself isn't needed on the
   server if you already built the jar locally — you only need a JRE to
   run it):
   ```
   sudo yum install -y java-17-amazon-corretto   # Amazon Linux
   # or: sudo apt install -y openjdk-17-jre-headless   # Ubuntu
   ```

5. **Copy the built jar to the instance**
   ```
   scp -i my-key.pem target/my-app-1.0.jar ec2-user@<EC2_PUBLIC_IP>:~
   ```

6. **Run the app on the instance**
   ```
   java -jar my-app-1.0.jar
   ```
   Visit `http://<EC2_PUBLIC_IP>:8080` in a browser to confirm it works.

7. **Keep it running after you log out / on reboot** — running the jar
   directly dies the moment you close the SSH session, so wrap it as a
   `systemd` service instead:
   ```ini
   # /etc/systemd/system/my-app.service
   [Unit]
   Description=My Maven App
   After=network.target

   [Service]
   ExecStart=/usr/bin/java -jar /home/ec2-user/my-app-1.0.jar
   Restart=always
   User=ec2-user

   [Install]
   WantedBy=multi-user.target
   ```
   Then:
   ```
   sudo systemctl enable my-app
   sudo systemctl start my-app
   ```

**Alternative (less manual, more "AWS-native"):** instead of managing EC2
by hand, you can deploy the same jar to **AWS Elastic Beanstalk**, which
handles provisioning the server, load balancing, and restarts for you —
you just upload the jar/zip through the console or `eb deploy` CLI and
Beanstalk runs it. This trades manual control for convenience, which is
usually the better trade-off once you're past the learning stage.



