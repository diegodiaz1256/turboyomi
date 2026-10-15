include(":core")

// The shared modules under /lib are not used by Turboyomi and still target the old
// Kotlin and extensions-lib toolchain, so they are no longer part of the build.

if (System.getenv("CI") == null) {
    // Local development (full project build)

    /**
     * Add or remove modules to load as needed for local development here.
     */
    loadAllIndividualExtensions()
    // loadIndividualExtension("all", "komga")
} else {
    // Running in CI (GitHub Actions)

    loadAllIndividualExtensions()
}

fun loadAllIndividualExtensions() {
    File(rootDir, "src").eachDir { dir ->
        dir.eachDir { subdir ->
            val name = ":extensions:individual:${dir.name}:${subdir.name}"
            include(name)
            project(name).projectDir = File("src/${dir.name}/${subdir.name}")
        }
    }
}
fun loadIndividualExtension(lang: String, name: String) {
    val projectName = ":extensions:individual:$lang:$name"
    include(projectName)
    project(projectName).projectDir = File("src/${lang}/${name}")
}

fun File.eachDir(block: (File) -> Unit) {
    listFiles()?.filter { it.isDirectory }?.forEach { block(it) }
}
