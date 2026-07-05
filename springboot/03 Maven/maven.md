# Maven (dost wali language mein samjho)

## Maven hai kya?

Bhai simple bhasha mein — Maven ek **build tool** hai jo Java project ka pura
kaam-kaaj sambhalta hai. Socho tumhe koi project banana hai, usme bahut saari
libraries chahiye, code compile karna hai, test chalane hai, phir sab kuch
ek `.jar` ya `.war` file mein pack karna hai. Ye sab manually karna bahut
pakau kaam hai. Maven ye sab khud kar deta hai, bas usko bata do "kya chahiye"
aur wo laake de deta hai.

Har Maven project ke andar ek `pom.xml` file hoti hai (Project Object Model).
Ye file basically project ka "resume" hai — isme likha hota hai:
- project ka naam, version, aur type (jar hai ya war hai)
- **dependencies** — matlab kaunsi external libraries chahiye (jaise Spring, JUnit)
- **plugins** — extra kaam karne ke liye (jaise specific Java version pe compile
  karna, tests chalana, executable jar banana)

## Maven use kyu karte hai?

Agar Maven na ho to ye sab tumhe haath se karna padega, aur wo bhi bada
headache wala kaam hai:

1. **Dependency management** — Java projects mein bahar ki libraries lagti
   hai. Har ek `.jar` file ko manually dhundhna, download karna, aur unki
   apni dependencies bhi manage karna — bohot jhanjhat hai. Maven `pom.xml`
   padhta hai, exact wahi libraries (aur unki dependencies) khud download
   karke classpath mein daal deta hai.
2. **Same folder structure har jagah** — Har Maven project ka folder layout
   same hota hai (`src/main/java`, `src/test/java`, `src/main/resources`).
   Ek baar samajh liya, to har Maven project mein ghus sakte ho bina kisi
   confusion ke — "code kahan hai" ye sochna hi nahi padta.
3. **Build hamesha same result deta hai** — `mvn install` chalao apne laptop
   pe ya teammate ke laptop pe ya CI server pe — result same aayega, kyuki
   sab kuch `pom.xml` mein already likha hai, "jo mann kare wo command"
   wala scene nahi hai.
4. **Sab kaam ek jagah automate** — compile, test, package, deploy — sab
   ek hi command se ho jaata hai, alag-alag steps manually nahi karne
   padte.
5. **Easily extend kar sakte ho** — Plugins add karke naye features la
   sakte ho (code coverage, Docker image banana, etc.) bina apna build
   process badle.

## Maven use karne ke steps

1. **Maven install karo** aur check karo PATH mein hai ya nahi:
   ```
   mvn -version
   ```
2. **Naya project banao** (quickstart template se), ya jo already
   `pom.xml` wala project hai wo kholo:
   ```
   mvn archetype:generate -DgroupId=com.example -DartifactId=my-app \
       -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
   ```
3. **Folder structure samjho** jo Maven expect karta hai:
   ```
   my-app/
   ├── pom.xml
   └── src/
       ├── main/
       │   ├── java/        <- tumhara actual code yahan
       │   └── resources/   <- config files waghera yahan
       └── test/
           └── java/        <- test code yahan
   ```
4. **Dependencies daalo** `pom.xml` ke `<dependencies>` tag ke andar
   (jaise Spring Boot starter, JUnit) — Maven khud download kar lega.
5. **Project build karo** — project ke root folder se (jahan `pom.xml`
   hai) ye commands chalao:
   - `mvn compile` — sirf code compile karta hai
   - `mvn test` — saare unit tests chalata hai
   - `mvn package` — compiled code ko `.jar`/`.war` bana ke `target/`
     folder mein daal deta hai
   - `mvn install` — us package ko apne local Maven repo
     (`~/.m2`) mein daal deta hai, taaki dusre local projects bhi ise
     use kar sake
   - `mvn clean` — `target/` folder delete karke fresh start deta hai
   (Har step apne se pehle wale steps bhi khud chala deta hai — jaise
   `mvn package` chalane se pehle compile aur test bhi ho jaate hai.)
6. **Bana hua jar chalao** — plain jar ke liye:
   `java -jar target/my-app-1.0.jar`; Spring Boot app ke liye
   `mvn spring-boot:run` seedha shortcut hai development ke time.

## AWS pe Maven project kaise set up kare

Ek baar `mvn package` se jar ban jaaye (jaise Spring Boot app ka), to AWS
pe usko chalane ka sabse simple tarika hai **EC2** instance pe deploy
karna. Steps kuch aise hai:

1. **Pehle apne laptop pe hi build kar lo**
   ```
   mvn clean package
   ```
   Isse `target/my-app-1.0.jar` bnegi. AWS pe jaane se pehle
   `java -jar target/my-app-1.0.jar` chala ke local pe hi check kar lo ki
   sahi chal rahi hai.

2. **EC2 instance launch karo**
   - AWS Console mein jao → EC2 → "Launch Instance".
   - AMI choose karo (Amazon Linux 2023 ya Ubuntu common hai).
   - Instance type choose karo (`t2.micro`/`t3.micro` seekhne ke liye
     kaafi hai, aur free-tier mein aata hai).
   - Ek **key pair** (`.pem` file) banao ya select karo — isi se SSH
     karke instance ke andar ghusoge, isliye ise safe rakho aur kabhi
     git mein commit mat karo.
   - **Security Group** set karo (basically firewall hai):
     - port **22** (SSH) allow karo apne IP se
     - port **8080** (ya jo bhi port tumhari app use karti hai) allow
       karo `0.0.0.0/0` se, agar public access chahiye to

3. **Instance se connect karo**
   ```
   ssh -i my-key.pem ec2-user@<EC2_PUBLIC_IP>
   ```

4. **Instance pe Java install karo** (Maven yahan zaroori nahi agar jar
   already bana ke laaye ho — bas jar chalane ke liye JRE chahiye):
   ```
   sudo yum install -y java-17-amazon-corretto   # Amazon Linux
   # ya: sudo apt install -y openjdk-17-jre-headless   # Ubuntu
   ```

5. **Apni jar file instance pe copy karo**
   ```
   scp -i my-key.pem target/my-app-1.0.jar ec2-user@<EC2_PUBLIC_IP>:~
   ```

6. **App ko instance pe chalao**
   ```
   java -jar my-app-1.0.jar
   ```
   Browser mein `http://<EC2_PUBLIC_IP>:8080` khol ke check kar lo ki
   chal rahi hai.

7. **SSH band karne ke baad bhi chalti rahe** — jar ko directly chalane
   se problem ye hai ki SSH session band karte hi app bhi band ho jaati
   hai. Isliye isko `systemd` service bana do:
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
   Fir:
   ```
   sudo systemctl enable my-app
   sudo systemctl start my-app
   ```

**Ek aur tarika (kam manual, zyada "AWS-native"):** EC2 ko manually manage
karne ke bajaye, wahi jar **AWS Elastic Beanstalk** pe deploy kar sakte ho.
Ye server provision karna, load balancing, restarts — sab khud sambhal
leta hai — bas jar/zip console se ya `eb deploy` CLI se upload karna hai.
Thoda control kam milta hai lekin kaafi convenient hai, jab seekhna kaafi
ho jaaye tab is approach pe switch karna better hota hai.

## Project generate karna: `mvn archetype:generate`

```
mvn archetype:generate -DgroupId=com.telusko -DartifactId=telusko-app \
    -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.0 \
    -DinteractiveMode=false
```

Ye command naya Maven project bana deti hai automatically, taaki tumhe
manually folder banana aur `pom.xml` likhna na pade. Har flag ka matlab:

- `archetype:generate` — Maven ka goal jo bolta hai "template se naya
  project bana do."
- `-DgroupId=com.telusko` — project ka Group ID set karta hai (niche
  explain hai).
- `-DartifactId=telusko-app` — project ka Artifact ID set karta hai — yahi
  generated folder ka naam bhi ban jaata hai.
- `-DarchetypeArtifactId=maven-archetype-quickstart` — batata hai kaunsa
  template use karna hai. `maven-archetype-quickstart` sabse basic wala
  template hai (ek sample class + ek test class ke saath).
- `-DarchetypeVersion=1.0` — us template ka version fix karta hai.
- `-DinteractiveMode=false` — Maven ko bolta hai "terminal mein ek-ek
  sawaal mat pooch, jo values already de di hai unhi se seedha project bana
  de."

## Maven Terminologies (basic terms samjho)

Ye woh building blocks hai jo Maven project ko identify karne ke liye use
hote hai — inhe `pom.xml` mein aur upar wale command mein baar baar
dekhoge.

- **Archetype** — ek project ka *template/skeleton*. Manually
  `src/main/java`, `src/test/java` folder aur starter `pom.xml` banane ke
  bajaye, Maven ko bolo "ye archetype use karo" aur wo pura standard
  structure khud bana deta hai, sample class ke saath. Isko aise socho jaise
  `create-react-app` hota hai React ke liye, waisa hi ye Maven/Java ke
  liye hai.
- **Group ID** — batata hai ye project *kiska* hai, usually reversed domain
  name ki tarah likha jata hai (jaise `com.telusko`, `com.example`). Java
  package naam jaisa hi concept hai — isse pata chalta hai ye kis company
  ya person ka project hai, aur naming clash nahi hota doosre logo ke
  projects se.
- **Artifact ID** — *is particular project/module* ka naam (jaise
  `telusko-app`). Group ID ke saath milke ye project ko uniquely identify
  karta hai — koi aur project ise dependency ki tarah isi combo se refer
  karta hai, aur final jar/war file ka naam bhi isi se banta hai
  (`telusko-app-1.0.jar`).
- **Version** — project ka release number (jaise `1.0`, `1.0-SNAPSHOT`).
  `SNAPSHOT` ka matlab "abhi bhi development mein hai, badal sakta hai",
  aur plain number jaise `1.0` ka matlab "fixed, release ho chuka build".
- **Packaging Type** — ye project final mein kya banega: `jar` (plain
  runnable/library jar, default hota hai), `war` (web application, servlet
  container mein deploy hota hai), ya `pom` (aisa project jo sirf doosre
  projects ko group/manage karne ke liye hai, khud ka code nahi hota).

## Maven Dependencies, Goals, aur Repositories

- **Maven Dependencies** — external libraries jo tumhare project ko compile
  aur run karne ke liye chahiye (jaise Spring Boot, JUnit, Jackson). Inhe
  Group ID + Artifact ID + Version ke through `pom.xml` ke
  `<dependencies>` tag mein likhte ho, aur Maven khud download kar leta hai
  (aur unki dependencies bhi) — kabhi manually `.jar` file dhundhni nahi
  padti.
- **Maven Goals** — ek *goal* matlab Maven ka ek specific kaam, jaise
  `compiler:compile`, `surefire:test`, ya `jar:jar`. Goals plugins ke
  andar hote hai (format hota hai `plugin:goal`) aur yahi asli "verb" hai
  jo actual kaam karta hai. Ek **phase** (jaise `compile` ya `test`)
  basically ek naam ka bucket hai jisme ek ya zyada goals bind hote hai —
  jab tum `mvn test` chalate ho, to Maven asal mein `test` phase se jude
  hue saare goals chala raha hota hai (aur usse pehle wale saare phases
  ke bhi).
- **Maven Repositories** — wo jagah jahan se Maven dependencies leke aata
  hai aur artifacts publish karta hai. Teen type hote hai:
  - **Local repository** — tumhare apne machine pe cache,
    `~/.m2/repository`. Maven sabse pehle yahi check karta hai.
  - **Central repository** — public, default repository
    (repo.maven.apache.org), jahan zyada tar open-source Java libraries
    milti hai.
  - **Remote/private repository** — company/team ka apna custom repo
    (jaise Nexus, Artifactory, AWS CodeArtifact), internal libraries ke
    liye jo public nahi honi chahiye.

## Amazon Linux (EC2) pe Maven install karna

Ek baar Amazon Linux EC2 instance mein SSH kar liya, to Maven set up karne
ke steps (useful hai agar jar server pe hi build karni ho, upload karne ke
bajaye):

1. **Maven install karo**
   ```
   sudo yum install maven -y
   ```
2. **Install confirm karo aur version check karo**
   ```
   mvn -v
   ```
3. **Maven ki config file** — Maven mein do alag config files hoti hai,
   confuse mat hona:
   - `pom.xml` — har project ke andar hoti hai; *usi project* ke baare
     mein batati hai (dependencies, plugins, version, etc.).
   - `settings.xml` — kisi ek project ke bahar hoti hai (usually
     `~/.m2/settings.xml` ya `$MAVEN_HOME/conf/settings.xml`); ye batata
     hai *is machine pe Maven kaise behave karega* — kaunse repositories
     use karne hai, mirrors, proxy settings, aur private repos ke
     credentials. Ye us machine pe banaye jaane wale har project ke liye
     common hoti hai.
4. **`tree` install karo** (ye Maven ka tool nahi hai, bas ek handy Linux
   utility hai folder structure dekhne ke liye — kaafi kaam aata hai Maven
   ka generate kiya standard layout dekhne ke liye):
   ```
   sudo yum install tree -y
   ```
5. **Kisi project ka folder structure dekho**
   ```
   tree <folder-name>
   ```
   Isse pura directory tree print ho jaata hai, jisse turant confirm ho
   jaata hai ki `src/main/java`, `src/test/java`, aur `pom.xml` sahi jagah
   pe hai jaise Maven expect karta hai.
