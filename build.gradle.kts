import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val javaVersion = "25"
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
tasks.withType<KotlinCompile>().all { compilerOptions { jvmTarget.set(JvmTarget.fromTarget(javaVersion)) } }

val dockerRegistry = "goafabric"
plugins {
	jacoco
	id("io.quarkus") version "3.37.2"
	id("net.researchgate.release") version "3.1.0"

	kotlin("jvm") version "2.4.10"
}

repositories {
	mavenCentral()
}

dependencies {
	constraints {
		testImplementation("org.assertj:assertj-core:3.27.7")
		testImplementation("com.tngtech.archunit:archunit-junit5:1.4.2")
	}

	implementation(enforcedPlatform("io.quarkus:quarkus-bom:3.37.2"))
	implementation("io.quarkiverse.mcp:quarkus-mcp-server-http:1.13.1")
}

dependencies {
	//web
	implementation("io.quarkus:quarkus-arc")
	implementation("io.quarkus:quarkus-resteasy-jackson")
	implementation("org.jboss.logmanager:log4j2-jboss-logmanager")

	//monitoring
	implementation("io.quarkus:quarkus-smallrye-health")
	implementation("io.quarkus:quarkus-smallrye-openapi")
	implementation("io.quarkus:quarkus-opentelemetry")
	implementation("io.quarkus:quarkus-micrometer-registry-prometheus")

	//jib
	implementation("io.quarkus:quarkus-container-image-jib")
	
	//mcp
	implementation("io.quarkiverse.mcp:quarkus-mcp-server-http") //https://docs.quarkiverse.io/quarkus-mcp-server/dev/guides-implementing-tools.html

	//kotlin
	implementation("io.quarkus:quarkus-kotlin")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	//test
	testImplementation("io.quarkus:quarkus-junit5")
	testImplementation("io.rest-assured:rest-assured")
	testImplementation("io.quarkus:quarkus-resteasy-client-jackson")
	testImplementation("io.quarkus:quarkus-jacoco")
	testImplementation("com.tngtech.archunit:archunit-junit5")

}

tasks.withType<Test> {
	useJUnitPlatform()
	exclude("**/*NRIT*")
	systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
	finalizedBy("jacocoTestReport")
}

tasks.jacocoTestReport {
	executionData.setFrom(
		fileTree(layout.buildDirectory.get()).include("jacoco/test.exec", "jacoco-quarkus.exec")
	)
	reports { xml.required.set(true); csv.required.set(true); html.required.set(true) }
}

tasks.register<Exec>("dockerImageNative") { group = "build" ; dependsOn("quarkusBuild", "testNative")
	if (gradle.startParameter.taskNames.contains("dockerImageNative")) {
		if (System.getProperty("os.arch").equals("aarch64")) {
			System.setProperty("quarkus.jib.platforms", "linux/arm64/v8")
		}

		System.setProperty("quarkus.native.builder-image", "quay.io/quarkus/ubi-quarkus-mandrel-builder-image:jdk-25")
		System.setProperty("quarkus.package.jar.enabled", "false")

		System.setProperty("quarkus.native.enabled", "true")
		System.setProperty("quarkus.native.container-build", "true")
		System.setProperty("quarkus.container-image.build", "true")

		System.setProperty("quarkus.native.native-image-xmx", "4096m")
		System.setProperty("quarkus.container-image.image", "${dockerRegistry}/${project.name}:${project.version}")

		commandLine("/bin/sh", "-c", "docker push ${dockerRegistry}/${project.name}:${project.version}")
	}
}

configure<net.researchgate.release.ReleaseExtension> {
	buildTasks.set(listOf("build", "test", "dockerImageNative"))
	tagTemplate.set("v${version}".replace("-SNAPSHOT", ""))
}

tasks.matching { it.name == "checkSnapshotDependencies" }.configureEach {
	enabled = false
}

