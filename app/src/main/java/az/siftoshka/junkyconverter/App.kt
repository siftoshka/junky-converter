package az.siftoshka.junkyconverter

import android.app.Application
import az.siftoshka.junkyconverter.domain.utils.getDeviceLanguage
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.HiltAndroidApp

/**
 * The [Application] class of the entire app.
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Lingver.init(this, getDeviceLanguage())
    }
}