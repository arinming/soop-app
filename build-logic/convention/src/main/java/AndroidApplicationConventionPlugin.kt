import com.android.build.api.dsl.ApplicationExtension
import com.soop.convention.configureKotlinAndroid
import com.soop.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.android")

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                defaultConfig {
                    applicationId = libs.findVersion("applicationId").get().toString()
                    targetSdk = libs.findVersion("targetSdkVersion").get().toString().toInt()
                    versionCode = libs.findVersion("versionCode").get().toString().toInt()
                    versionName = libs.findVersion("versionName").get().toString()
                }
            }
        }
    }
}