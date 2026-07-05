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

## Generating a project: `mvn archetype:generate`

```
mvn archetype:generate -DgroupId=com.telusko -DartifactId=telusko-app \
    -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.0 \
    -DinteractiveMode=false
```

This is the command that scaffolds a brand-new Maven project for you instead
of creating folders and the `pom.xml` by hand. Breaking down each flag:

- `archetype:generate` — the Maven goal that says "generate a new project
  from a template."
- `-DgroupId=com.telusko` — sets the project's Group ID (see below).
- `-DartifactId=telusko-app` — sets the project's Artifact ID (see below) —
  this also becomes the generated folder name.
- `-DarchetypeArtifactId=maven-archetype-quickstart` — picks *which*
  template to use. `maven-archetype-quickstart` is the classic "bare
  minimum Java project" template (one sample class + one test class).
- `-DarchetypeVersion=1.0` — pins the version of that template itself.
- `-DinteractiveMode=false` — tells Maven not to ask you questions one by
  one in the terminal; just use the values you already passed in and
  generate the project immediately.

## Maven Terminologies

These are the core building blocks Maven uses to identify and describe a
project — you'll see them constantly in `pom.xml` and in commands like the
one above.

- **Archetype** — a project *template/skeleton*. Instead of manually
  creating `src/main/java`, `src/test/java`, and a starter `pom.xml`, you
  tell Maven "use this archetype" and it generates that standard structure
  for you, pre-filled with a sample class. Think of it as `create-react-app`
  but for Maven/Java projects.
- **Group ID** — identifies *who* the project belongs to, usually written
  as a reversed domain name (e.g. `com.telusko`, `com.example`). It's the
  same idea as a Java package name — it groups all of an organization's or
  individual's projects together and avoids naming clashes with other
  people's projects on the internet.
- **Artifact ID** — the name of *this specific project/module*
  (e.g. `telusko-app`). Combined with the Group ID, it uniquely identifies
  the project — this pair is how other projects reference it as a
  dependency, and it's also what names the final jar/war file
  (`telusko-app-1.0.jar`).
- **Version** — the release number of the project (e.g. `1.0`,
  `1.0-SNAPSHOT`). `SNAPSHOT` versions mean "still under active
  development, may change," while a plain number like `1.0` means a fixed,
  released build.
- **Packaging Type** — what kind of artifact this project produces:
  `jar` (a plain runnable/library jar, the default), `war` (a web
  application archive, deployed to a servlet container), or `pom` (a
  project that exists only to group/manage other projects, no code of its
  own).

## Maven Dependencies, Goals, and Repositories

- **Maven Dependencies** — the external libraries your project needs to
  compile and run (e.g. Spring Boot, JUnit, Jackson). You declare them by
  Group ID + Artifact ID + Version inside the `<dependencies>` tag of
  `pom.xml`, and Maven takes care of downloading them (and anything *they*
  depend on) automatically — you never manually hunt for `.jar` files.
- **Maven Goals** — a *goal* is one specific unit of work Maven can carry
  out, e.g. `compiler:compile`, `surefire:test`, or `jar:jar`. Goals belong
  to plugins (the format is `plugin:goal`) and are the actual "verbs" that
  do the work. A **phase** (like `compile` or `test`) is really just a
  named bucket that one or more goals are bound to — when you run
  `mvn test`, Maven is really running the goals bound to the `test` phase
  (and every phase before it).
- **Maven Repositories** — the storage locations Maven pulls dependencies
  from and publishes artifacts to. There are three kinds:
  - **Local repository** — a cache on your own machine, `~/.m2/repository`.
    Maven always checks here first before going anywhere else.
  - **Central repository** — the public, default repository
    (repo.maven.apache.org) that hosts most open-source Java libraries.
  - **Remote/private repository** — a custom repository your company or
    team hosts (e.g. Nexus, Artifactory, AWS CodeArtifact) for internal
    libraries that aren't meant to be public.

## Installing Maven on Amazon Linux (EC2)

Once you're SSH'd into an Amazon Linux EC2 instance, here's how to get
Maven itself set up there (useful if you want to build the jar *on* the
server instead of uploading a pre-built one):

1. **Install Maven**
   ```
   sudo yum install maven -y
   ```
2. **Confirm the install and check the version**
   ```
   mvn -v
   ```
3. **Maven's config file** — Maven actually has two different config
   files, and it's easy to mix them up:
   - `pom.xml` — lives inside each project; describes *that project*
     (its dependencies, plugins, version, etc.).
   - `settings.xml` — lives outside any single project (usually
     `~/.m2/settings.xml` or `$MAVEN_HOME/conf/settings.xml`); describes
     *how Maven itself behaves on this machine* — which repositories to
     use, mirrors, proxy settings, and credentials for private
     repositories. It's shared across every project you build on that
     machine.
4. **Install `tree`** (not a Maven tool, just a handy Linux utility for
   visualizing folder structures — useful for seeing the standard Maven
   layout Maven generated for you):
   ```
   sudo yum install tree -y
   ```
5. **View a project's folder structure**
   ```
   tree <folder-name>
   ```
   This prints the full directory tree, which is a quick way to confirm
   your `src/main/java`, `src/test/java`, and `pom.xml` are laid out the
   way Maven expects.

