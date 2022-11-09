plugins {
    id("java")
}

group = "test.amon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.7")
    implementation("com.codeborne:selenide:6.9.0")
    implementation("junit:junit:4.13.2")
    implementation("org.hamcrest:hamcrest-core:2.2")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}