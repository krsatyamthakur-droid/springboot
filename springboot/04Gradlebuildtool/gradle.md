# Gradle (ekdum simple bhasha mein, jaise ek chote bacche ko samjhaya jaata hai)

## Build tool hota kya hai?

Socho tumne bahut sara Java code likh liya. Ab usko **actually chalane
layak** banane ke liye kuch kaam karne padte hai:
- code ko compile karna (`.java` → `.class`, matlab computer ki bhasha
  mein badalna)
- code ko ek box (`.jar`/`.war` file) mein pack karna
- bahar ki libraries (jaise Hibernate, Spring) manage karna
- tests chalana
- aur aakhir mein app ko deploy (kahin chalane ke liye bhej) karna

Ye sab kaam manually karna bahut time leta hai aur galti hone ke chances
bhi rehte hai. **Build tool** ek software hai jo ye sab kaam **khud, ek
hi command se** kar deta hai. Jab build complete hota hai, output ek
`.jar` (normal Java app) ya `.war` (web app) file hoti hai — jisme
tumhara code + saari zaroori libraries pehle se pack hoti hai, taaki use
kahin bhi le jaake bina extra setup ke chala sako.

**Gradle** aur **Maven** dono is kaam ke liye use hone wale popular build
tools hai — inhe ek hi kaam ke liye do alag "assistants" socho.

## Gradle hai kya?

Gradle bhi Maven jaisa hi build tool hai (dependencies download karna,
compile karna, package banana), lekin iska style thoda alag hai:

- Maven apna sara config **XML** (`pom.xml`) mein likhta hai.
- Gradle apna config **Groovy** ya **Kotlin** language (`build.gradle` ya
  `build.gradle.kts`) mein likhta hai — jo ek real programming language
  hai, isliye zyada flexible aur likhne mein chota hota hai.
- Gradle apne builds ko **cache** karta hai — matlab agar code mein kuch
  nahi badla, to Gradle dubara compile hi nahi karta, seedha purana
  result use kar leta hai. Isse Gradle Maven se **kaafi fast** hota hai
  bade projects mein.

## Gradle project ka folder structure

Jab tum `gradle init` chalate ho, Gradle khud ek standard structure bana
deta hai:

```
my-project/
├── build.gradle         # Main Gradle build script
├── settings.gradle      # Multi-project settings (agar zaroorat ho)
├── gradlew               # Gradle wrapper script (Linux/Mac)
├── gradlew.bat           # Gradle wrapper script (Windows)
├── gradle/
│   └── wrapper/          # Wrapper files (Gradle ka version yaad rakhte hai)
├── src/
│   ├── main/
│   │   ├── java/         # Tumhara Java source code
│   │   └── resources/    # Configs, properties, static files
│   └── test/
│       ├── java/         # Unit test code
│       └── resources/    # Test-related configs/resources
└── build/                 # Auto-generated build output (classes, jars)
```

Har file/folder ka kaam:

- **`build.gradle`** — sabse important file, project ka "recipe". Isme
  likha hota hai: konsa plugin use karna hai (jaise `application`),
  dependencies kya hai, aur project kaise build hoga. Ye Maven ke
  `pom.xml` jaisa hi role play karta hai.
- **`settings.gradle`** — batata hai build mein **kaunse-kaunse projects/
  modules** shamil hai (jaise multi-module project mein `app`,
  `user-service` waghera). Chote, single-project builds mein ye sirf
  root project ka naam set karta hai.
- **`gradlew` / `gradlew.bat`** — ye **Gradle Wrapper** scripts hai. Inhi
  ki wajah se doosre developer ke computer mein Gradle install na ho tab
  bhi project chal jaata hai — wrapper khud sahi Gradle version download
  kar leta hai. `gradlew` Mac/Linux ke liye hai, `gradlew.bat` Windows ke
  liye.
- **`gradle/wrapper/`** — is folder mein wrapper ki config aur `.jar` file
  hoti hai, jisme likha hota hai exactly **kaunsa Gradle version** use
  karna hai — taaki sab developers ka build same version se ho.
- **`src/main/java`** — tumhara actual application code yahan likha jaata
  hai.
- **`src/main/resources`** — non-code files jaise `.properties`,
  `.yml`, config files, static assets — sab yahan rakhte hai.
- **`src/test/java`** — unit tests yahan likhe jaate hai (jaise
  `AppTest.java`).
- **`src/test/resources`** — sirf tests ke liye chahiye hone wale config
  files yahan.
- **`build/`** — ye folder Gradle **khud generate** karta hai (compiled
  `.class` files, final jar, reports waghera). Isko tumhe kabhi manually
  edit nahi karna — aur ise git mein commit bhi nahi karna chahiye
  (`gradle clean` isse delete kar deta hai).

## Gradle terminal mein use karne ka procedure

1. **Naya Gradle project banao**:
   ```
   gradle init
   ```
   Ye terminal mein kuch sawaal poochega (project type — application ya
   library, language — Java/Kotlin, build script language — Groovy ya
   Kotlin DSL, testing framework waghera). Jawab dete jao, aur Gradle
   khud upar wala poora folder structure bana dega.
2. Uske baad project ke andar jaake wrapper se hi commands chalao (isse
   apne system ka Gradle use nahi hota, project ke apne pinned version
   ka use hota hai):
   ```
   ./gradlew build     # Mac/Linux
   gradlew.bat build   # Windows
   ```

## Gradle Wrapper kya hota hai?

Ye ek bahut important cheez hai, isliye alag se achhe se samajhte hai.

Socho tumne ek project banaya jisme **Gradle version 8.5** use kiya. Ab
tumne ye project GitHub pe daal diya. Tumhara dost isko clone karta hai —
lekin uske computer mein Gradle install hi nahi hai, ya phir uske paas
**Gradle version 6** hai. Ab kya hoga? Ho sakta hai project uske system
pe sahi se chale hi na, ya different version ki wajah se error aa jaaye.

**Gradle Wrapper** isi problem ko solve karta hai. Ye ek chota script
hai jo **project ke andar hi save** hota hai (`gradlew` aur
`gradlew.bat`), aur jab bhi koi isse chalata hai:

1. Ye check karta hai ki sahi Gradle version (jo project ko chahiye) us
   computer pe already hai ya nahi.
2. Agar nahi hai, to wrapper **khud us exact version ko internet se
   download** kar leta hai (sirf ek baar, fir wo cache ho jaata hai).
3. Uske baad wahi pinned version use karke build chalata hai.

Isko aise socho jaise tumne apne dost ko tiffin diya, aur tiffin ke
andar hi ek **recipe card** rakh di jisme likha hai "isse garam karne ke
liye bilkul yehi wala microwave setting use karna" — ab dost ko khud
guess nahi karna padta, sahi setting khud tiffin ke saath aa gayi.

### Wrapper ke fayde (kyu use karte hai)

- **Har jagah same Gradle version** — tumhare laptop pe, teammate ke
  laptop pe, aur CI server pe — sabme exactly wahi Gradle version
  chalega jo project ko chahiye. Koi "mere system pe to chal raha tha"
  wala problem nahi hota.
- **Gradle install karne ki zaroorat nahi** — naye developer ko bas
  project clone karna hai aur `./gradlew build` chalana hai — Gradle
  khud install nahi karna padta.
- **Version upgrade karna easy hai** — jab naya Gradle version use karna
  ho, bas ek command chalao aur wrapper config update ho jaata hai, sabko
  automatically naya version mil jaata hai.

### Wrapper ke andar kya-kya hota hai

- **`gradlew`** — Mac/Linux ke liye shell script.
- **`gradlew.bat`** — Windows ke liye batch script.
- **`gradle/wrapper/gradle-wrapper.properties`** — isme likha hota hai
  *exactly kaunsa* Gradle version use karna hai (jaise
  `distributionUrl=...gradle-8.5-bin.zip`).
- **`gradle/wrapper/gradle-wrapper.jar`** — chota helper program jo
  download aur setup ka kaam karta hai.

Isliye jab bhi Gradle project use karo, hamesha `gradle build` ki jagah
`./gradlew build` chalao — apne system ke Gradle ki jagah project ke apne
pinned version ka use hota hai, aur consistent result milta hai.

## `build.gradle` ka example (is project ka)

```groovy
plugins {
    // application plugin -> CLI Java app banane ke liye support deta hai
    id 'application'
}

repositories {
    // dependencies Maven Central se dhundhega
    mavenCentral()
}

dependencies {
    // implementation matlab: is library ki zaroorat compile aur run dono waqt hai
    implementation libs.guava
}

testing {
    suites {
        test {
            useJUnitJupiter('6.0.1')
        }
    }
}

java {
    toolchain {
        // kis Java version pe compile/run karna hai
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // jar chalane pe kaunsi class ka main() sabse pehle chalega
    mainClass = 'org.example.App'
}
```

- **`plugins`** — extra features "on" karta hai (jaise `application`
  plugin se CLI app bana sakte ho, ya `java` plugin se plain Java
  library).
- **`repositories`** — Maven ki tarah, batata hai dependencies **kahan
  se** download karni hai (`mavenCentral()` sabse common hai).
- **`dependencies`** — konsi libraries chahiye. `implementation` sabse
  common keyword hai — Maven ke `compile` scope jaisa hi hai.
- **`testing`** — konsa test framework use karna hai (JUnit yahan).
- **`java { toolchain { ... } }`** — kis Java version pe build/run karna
  hai, wo fix karta hai.
- **`application { mainClass = ... }`** — jab `gradlew run` chalao, to
  Gradle ko pata chal jaata hai kaunsi class se program start karna hai.

## `build.gradle` ka example — ek Spring Boot project ke liye

Upar wala example ek **plain Java CLI app** ka tha. Ab dekhte hai Gradle
ka use ek **Spring Boot web application** ke liye kaise hota hai — ye
wala style bahut common milega jab tum Spring Boot projects banaoge:

```groovy
plugins {
    id 'org.springframework.boot' version '3.3.2'
    id 'io.spring.dependency-management' version '1.1.6'
    id 'java'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'

java {
    sourceCompatibility = '17'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.test {
    useJUnitPlatform()
}
```

Ab isko line-by-line, achhe se samjhte hai:

- **`plugins { ... }`** — teen plugins "on" kiye gaye hai:
  - `org.springframework.boot` (version `3.3.2`) — ye plugin Spring Boot
    ke special kaam karta hai: sab dependencies ka version khud manage
    karta hai, aur ek **executable "fat jar"** banata hai (jisme tumhara
    code + saari dependencies ek hi jar ke andar pack ho jaati hai, taaki
    seedha `java -jar app.jar` se chala sako).
  - `io.spring.dependency-management` (version `1.1.6`) — ye Maven ke
    "BOM" (Bill of Materials) jaisa kaam karta hai — Spring Boot ke saare
    starters (web, test, data-jpa, etc.) ke versions ko **compatible aur
    sync** rakhta hai, taaki tumhe har library ka version khud select na
    karna pade aur conflict na ho.
  - `java` — batata hai ye ek Java project hai, isliye `compileJava`,
    `test` jaise standard Java tasks available ho jaate hai.

- **`group = 'com.example'`** — Maven ke Group ID jaisa hi hai — batata
  hai project **kis company/organization** ka hai.

- **`version = '0.0.1-SNAPSHOT'`** — project ka version number.
  `SNAPSHOT` ka matlab hai "abhi development chal raha hai, final
  release nahi hua" — bilkul Maven wale concept jaisa hi.

- **`java { sourceCompatibility = '17' }`** — batata hai code kis Java
  version ke syntax/features follow karega, aur `.class` files usi
  version ke liye banengi. Isko Maven ke `maven.compiler.source` jaisa
  socho.

- **`repositories { mavenCentral() }`** — dependencies **kahan se**
  download karni hai, wahi Maven Central repository use ho raha hai
  (lakhon open-source libraries yahan milti hai).

- **`dependencies { ... }`** — is project ko chahiye hone wali libraries:
  - `implementation 'org.springframework.boot:spring-boot-starter-web'`
    — Spring Boot ka **web starter**, jisme Spring MVC, embedded Tomcat
    server, JSON handling waghera sab kuch ek saath aa jaata hai — isse
    tum REST APIs bana sakte ho. `implementation` ka matlab: ye library
    compile time aur run time dono waqt chahiye.
  - `testImplementation 'org.springframework.boot:spring-boot-starter-test'`
    — sirf **tests likhne/chalane** ke liye chahiye hone wala starter
    (JUnit, Mockito, AssertJ waghera bundle karta hai). `testImplementation`
    Maven ke `test` scope jaisa hi hai — final app ke andar ye nahi
    jaata.

- **`tasks.test { useJUnitPlatform() }`** — Gradle ke built-in `test`
  task ko configure kar rahe hai, taaki wo **JUnit 5** (JUnit Platform)
  use karke tests dhundhe aur chalaye. Bina ye line ke, Gradle purane
  JUnit 4 style ko expect karega aur naye JUnit 5 tests miss ho sakte
  hai.

### Ye Spring Boot wala style pehle wale (plain Java) example se kaise alag hai

| | Plain Java app (upar wala pehla example) | Spring Boot app (ye wala) |
|---|---|---|
| Plugin | `application` | `org.springframework.boot` + `io.spring.dependency-management` |
| Dependency version | khud likhna padta hai (jaise `libs.guava`) | Spring Boot plugin khud sahi compatible version choose kar deta hai |
| Final jar | seedha jar, dependencies alag rehti hai | "fat jar" — code + saari dependencies ek hi jar ke andar |
| Chalane ka tarika | `mainClass` set karke `gradlew run` | `gradlew bootRun`, ya jar bana ke `java -jar app.jar` |

## Extra info: Gradle Tasks (kaam ke units)

Maven mein "phases/goals" hote hai, Gradle mein sab kuch **task** kehlata
hai. Ek task ek chota, specific kaam hota hai — jaise `compileJava`,
`test`, `jar`, `build`. Tasks ek doosre pe depend kar sakte hai (jaise
`build` task chalane se pehle `compileJava` aur `test` khud-ba-khud chal
jaate hai).

Kuch common tasks:

| Command | Kya karta hai |
|---|---|
| `./gradlew tasks` | is project mein available saare tasks dikhata hai |
| `./gradlew compileJava` | sirf source code compile karta hai |
| `./gradlew test` | unit tests chalata hai |
| `./gradlew build` | compile + test + package sab kar deta hai |
| `./gradlew run` | `application` plugin wale project ko seedha chala deta hai |
| `./gradlew clean` | `build/` folder delete karta hai (fresh start) |
| `./gradlew dependencies` | saari dependencies ka tree dikhata hai |

## Gradle vs Maven — chota sa farak

| | Maven | Gradle |
|---|---|---|
| Config file | `pom.xml` (XML) | `build.gradle` (Groovy/Kotlin) |
| Speed | thoda slow (har baar poora rebuild) | fast (caching + incremental builds) |
| Likhna | zyada verbose | chota aur flexible |
| Fixed steps | strict, fixed lifecycle | flexible, khud custom tasks bana sakte ho |

Dono ka final goal same hai — dependencies manage karna, code build
karna, aur ek runnable package banana. Bas tarika alag hai.
