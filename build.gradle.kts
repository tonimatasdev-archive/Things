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

}

dependencies {

}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}