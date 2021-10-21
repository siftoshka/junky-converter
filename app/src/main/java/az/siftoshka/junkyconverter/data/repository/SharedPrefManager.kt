package az.siftoshka.junkyconverter.data.repository

import android.app.Application
import android.content.SharedPreferences
import androidx.core.content.edit
import az.siftoshka.junkyconverter.R
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Singleton class to manage [SharedPreferences].
 */
@Singleton
class SharedPrefManager @Inject constructor(
    private val app: Application,
    private val preferences: SharedPreferences
) {

    companion object {

        private const val KEY_SHOWN_INTRO = "key_shown_intro"
        private const val KEY_JUNK_ID = "key_junk_id"
        private const val KEY_VERSION_NAME = "key_version_name"
    }

    fun isIntroShown() = preferences.getBoolean(KEY_SHOWN_INTRO, false)

    fun setIntroShown(value: Boolean) {
        preferences.edit(commit = true) {
            putBoolean(KEY_SHOWN_INTRO, value)
        }
    }

    fun getSelectedJunk() = preferences.getInt(KEY_JUNK_ID, 1)

    fun selectJunk(value: Int) {
        preferences.edit(commit = true) {
            putInt(KEY_JUNK_ID, value)
        }
    }

    private fun getVersionName() = preferences.getString(KEY_VERSION_NAME, "")

    fun setVersionName() {
        preferences.edit(commit = true) {
            putString(KEY_VERSION_NAME, app.getString(R.string.version_name))
        }
    }

    fun isUpdateShown(): Boolean {
        return app.getString(R.string.version_name) == getVersionName()
    }
}