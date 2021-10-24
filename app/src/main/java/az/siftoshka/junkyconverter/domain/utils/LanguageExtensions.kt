package az.siftoshka.junkyconverter.domain.utils

import android.content.Context
import android.content.res.Resources
import android.os.Build
import com.yariksoffice.lingver.Lingver
import java.util.Locale

/**
 *  Language extension file.
 */
@Suppress("Deprecation")
fun getDeviceLanguage(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Resources.getSystem().configuration.locales[0].language
    } else {
        Resources.getSystem().configuration.locale.language
    }
}

@Suppress("Deprecation")
fun getDeviceCountry(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Resources.getSystem().configuration.locales[0].country
    } else {
        Resources.getSystem().configuration.locale.country
    }
}

fun getCurrentLanguage(): String {
    val language = Lingver.getInstance().getLanguage()
    if (!Language.isLanguageSupported(language)) {
        return "English"
    }
    val loc = Lingver.getInstance().getLocale()
    val langName = loc.displayLanguage
    return langName.substring(0, 1).uppercase(Locale.getDefault()) + langName.substring(1, langName.length)
}

fun getCurrentLanguageCode(): String {
    val language = Lingver.getInstance().getLanguage()
    if (!Language.isLanguageSupported(language)) {
        return "en"
    }
    return language
}

fun Context.updateLanguage(language: String, country: String) {
    Lingver.getInstance().setLocale(
        this,
        language,
        country
    )
}
