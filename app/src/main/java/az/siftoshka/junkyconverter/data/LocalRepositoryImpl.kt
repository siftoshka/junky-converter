package az.siftoshka.junkyconverter.data

import android.app.Application
import android.content.SharedPreferences
import androidx.core.content.edit
import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.domain.repository.LocalRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The implementation of repository class for [SharedPreferences].
 */
@Singleton
class LocalRepositoryImpl @Inject constructor(
    private val app: Application,
    private val preferences: SharedPreferences
): LocalRepository {

    companion object {

        private const val KEY_SHOWN_INTRO = "key_shown_intro"
        private const val KEY_JUNK_ID = "key_junk_id"
        private const val KEY_VERSION_NAME = "key_version_name"
        private const val KEY_SETTINGS_TIP = "key_settings_tip"
    }

    override fun isIntroShown() = preferences.getBoolean(KEY_SHOWN_INTRO, false)

    override fun setIntroShown(value: Boolean) {
        preferences.edit(commit = true) {
            putBoolean(KEY_SHOWN_INTRO, value)
        }
    }

    override fun getSelectedJunk() = preferences.getInt(KEY_JUNK_ID, 0)

    override fun selectJunk(value: Int) {
        preferences.edit(commit = true) {
            putInt(KEY_JUNK_ID, value)
        }
    }

    override fun setVersionName() {
        preferences.edit(commit = true) {
            putString(KEY_VERSION_NAME, app.getString(R.string.version_name))
        }
    }

    override fun isUpdateShown(): Boolean {
        return app.getString(R.string.version_name) == preferences.getString(KEY_VERSION_NAME, "")
    }

    override fun isTipVisible() = preferences.getBoolean(KEY_SETTINGS_TIP, true)

    override fun setTipVisibility(value: Boolean) {
        preferences.edit(commit = true) {
            putBoolean(KEY_SETTINGS_TIP, value)
        }
    }
}