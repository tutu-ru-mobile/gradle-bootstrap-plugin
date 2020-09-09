import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("java-gradle-plugin")
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.12.0"
}

val PLUGIN_NAME = "bootstrap-plugin"//todo
val PLUGIN_ID = "ru-tutu-github-package"
val VERSION = "0.1.0"//todo
group = "ru.tutu"
version = VERSION

repositories {
    jcenter()
    maven { setUrl("https://dl.bintray.com/kotlin/ktor") }//todo remove?
}

configure<JavaPluginConvention> {//todo redundant?
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"//todo simplify in root build.gralde.kts
}

gradlePlugin {
    plugins {
        create(PLUGIN_NAME) {
            id = PLUGIN_ID
            implementationClass = "ru.tutu.gradle.TutuGradlePlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/tutu-ru-mobile/gradle-bootstrap-plugin"
    vcsUrl = "https://github.com/tutu-ru-mobile/gradle-bootstrap-plugin.git"
    description = "Tutu.ru gradle bootstrap-plugin"
    tags = listOf("configuration", "settings")//some string tags

//    plugins.create("gradleFileEncryptPlugin") {
//        id = "ru.tutu"
//        displayName = "Gradle unicorn plugin"
//    }
    (plugins) {
        PLUGIN_NAME {
            // id is captured from java-gradle-plugin configuration
            displayName = "tutu bootstrap-plugin"
            tags = listOf("configuration", "settings")
            version = VERSION
        }
    }
}

dependencies {
    implementation(project(":local-server"))

    compileOnly(gradleApi())
    implementation("org.eclipse.jgit", "org.eclipse.jgit", "5.4.0.201906121030-r")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")//todo kotlin version
//    implementation(kotlin("stdlib-jdk8", "1.3.70"))//todo delete
//    implementation(project(":old-server-localhost"))//todo delete
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
    testImplementation("org.slf4j:slf4j-jdk14:1.7.25")
    testImplementation("junit:junit:4.12")
    testImplementation(gradleTestKit())
}
