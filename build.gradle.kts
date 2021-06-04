import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"
    kotlin("plugin.jpa") version "1.4.32"
}

group = "me.shuang"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    maven {
        url = uri("https://maven.aliyun.com/nexus/content/groups/public/")
    }
    maven {
        url = uri("https://maven.aliyun.com/nexus/content/repositories/jcenter")
    }
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    // custom

    // implementation group: ['"](.+)['"], name: ['"](.+)['"], version: ['"](.+)['"]
    // implementation(group = "$1", name = "$2", version = "$3")

    implementation(group = "redis.clients", name = "jedis", version = "3.6.0")
    implementation(group = "com.google.code.gson", name = "gson", version = "2.8.6")
    // https://mvnrepository.com/artifact/com.auth0/java-jwt
    implementation(group = "com.auth0", name = "java-jwt", version = "3.15.0")
    // https://mvnrepository.com/artifact/joda-time/joda-time
    implementation(group = "joda-time", name = "joda-time", version = "2.10.10")
    // https://mvnrepository.com/artifact/org.json/json
    implementation(group = "org.json", name = "json", version = "20210307")
    // https://mvnrepository.com/artifact/com.qiniu/qiniu-java-sdk
    implementation(group = "com.qiniu", name = "qiniu-java-sdk", version = "7.7.0")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib
    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib", version = "1.4.32")


}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
