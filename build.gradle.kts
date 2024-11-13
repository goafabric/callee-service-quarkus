val group: String by project
val version: String by project
java.sourceCompatibility = JavaVersion.VERSION_21

val dockerRegistry = "goafabric"

plugins {
	java
	jacoco
	id("io.quarkus") version "3.15.0"
	id("net.researchgate.release") version "3.0.2"
}

repositories {
	mavenCentral()
}

dependencies {
	constraints {
		implementation("org.mapstruct:mapstruct:1.6.2")
		annotationProcessor("org.mapstruct:mapstruct-processor:1.6.2")
		testImplementation("org.assertj:assertj-core:3.5.0")
	}

	implementation(enforcedPlatform("io.quarkus:quarkus-bom:3.15.0"))
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

	//code generation
	implementation("org.mapstruct:mapstruct")
	annotationProcessor("org.mapstruct:mapstruct-processor")

	//pdf
	implementation("io.quarkiverse.openpdf:quarkus-openpdf:3.1.2")

	//test
	testImplementation("io.quarkus:quarkus-junit5")
	testImplementation("io.rest-assured:rest-assured")
	testImplementation("io.quarkus:quarkus-resteasy-client-jackson")
	testImplementation("io.quarkus:quarkus-jacoco")
}

tasks.withType<Test> {
	dependsOn("quarkusBuild")
	useJUnitPlatform()
	exclude("**/*NRIT*")
	systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}

tasks.register<Copy>("copyAwtLibs") {
	from("src/main/jib/work/" + if (System.getProperty("os.arch").equals("aarch64")) "arm64" else "amd64")
	into("src/main/jib/work")
}

tasks.register<Exec>("dockerImageNative") { group = "build" ; dependsOn("copyAwtLibs", "quarkusBuild", "testNative")

	if (gradle.startParameter.taskNames.contains("dockerImageNative")) {
		val archSuffix = if (System.getProperty("os.arch").equals("aarch64")) "-arm64v8" else ""

		if (System.getProperty("os.arch").equals("aarch64")) {
			System.setProperty("quarkus.jib.platforms", "linux/arm64/v8")
		}

		System.setProperty("quarkus.native.builder-image", "quay.io/quarkus/ubi-quarkus-mandrel-builder-image:jdk-21")
		System.setProperty("quarkus.package.jar.enabled", "false")

		System.setProperty("quarkus.native.enabled", "true")
		System.setProperty("quarkus.native.container-build", "true")
		System.setProperty("quarkus.container-image.build", "true")

		System.setProperty("quarkus.native.native-image-xmx", "4096m")
		System.setProperty("quarkus.native.additional-build-args", "-march=compatibility")
		System.setProperty("quarkus.jib.base-native-image", "goafabric/quarkus-ubi-awt:8.10")
		System.setProperty("quarkus.container-image.image", "${dockerRegistry}/${project.name}${archSuffix}:${project.version}")

		commandLine("/bin/sh", "-c", "docker push ${dockerRegistry}/${project.name}${archSuffix}:${project.version}")
	}
}

configure<net.researchgate.release.ReleaseExtension> {
	buildTasks.set(listOf("build", "test", "dockerImageNative"))
	tagTemplate.set("v${version}".replace("-SNAPSHOT", ""))
}
