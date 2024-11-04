plugins {
    java
}

group = "dev.tonimatas"
version = "1.0.0"

base.archivesName.set("Things")

java {
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "dev.tonimatas.lombok.Main")
    }
}