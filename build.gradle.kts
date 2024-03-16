plugins {
    id("java")
}

tasks.withType<Jar>{
    manifest{
        attributes["Main-Class"] = "org.shipment.Main"
    }
}

group = "org.shipment"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}