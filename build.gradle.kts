plugins {
    id("java")
}

group = "dev.tonimatas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<Jar> {
    manifest {
        attributes(
        "Main-Class" to "dev.tonimatas.EditedDiscordMsg",
        "Multi-Release" to true
        )
    }
}

dependencies {

}