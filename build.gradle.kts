plugins {
    id("java")
}

group = "dev.accelerated"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val jqwikVersion = "1.9.2"
val junitJupiterVersion = "5.7.0"

tasks.compileTestJava {
    options.compilerArgs.add("-parameters")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))

    testImplementation("net.jqwik:jqwik:${jqwikVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter:${junitJupiterVersion}")
    testImplementation("org.assertj:assertj-core:3.23.1")
    compileOnly("org.jetbrains:annotations:23.0.0")
}

tasks.test {
    useJUnitPlatform {
        includeEngines("jqwik")

        // Or include several Junit engines if you use them
        // includeEngines 'jqwik', 'junit-jupiter', 'junit-vintage'

        // includeTags 'fast', 'medium'
        // excludeTags 'slow'
    }

    include("**/*Properties.class")
    include("**/*Test.class")
    include("**/*Tests.class")
}