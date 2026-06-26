tasks.register<Exec>("dockerImageJvm") { group = "build" ; dependsOn("quarkusBuild", "test")
    if (gradle.startParameter.taskNames.contains("dockerImageJvm")) {
        if (System.getProperty("os.arch").equals("aarch64")) {
            System.setProperty("quarkus.jib.platforms", "linux/arm64/v8")
        }

        System.setProperty("quarkus.container-image.build", "true")
        System.setProperty("quarkus.container-image.image", "${dockerRegistry}/${project.name}:${project.version}")
        System.setProperty("quarkus.native.enabled", "false")
        System.setProperty("quarkus.package.jar.aot.enabled", "true")
        commandLine("/bin/sh", "-c", "docker push ${dockerRegistry}/${project.name}:${project.version}")
    }
}

configure<net.researchgate.release.ReleaseExtension> {
    buildTasks.set(listOf("build", "test"))
    tagTemplate.set("v${version}".replace("-SNAPSHOT", ""))
}