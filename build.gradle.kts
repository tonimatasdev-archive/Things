plugins {
    id("java")
}

group = "dev.tonimatas"
version = "1.0.0"

base.archivesName.set("Things")

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
}

repositories {

}

dependencies {

}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}