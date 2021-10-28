package az.siftoshka.junkyconverter.domain.repository

import android.content.SharedPreferences

/**
 * The repository class for [SharedPreferences].
 */
interface LocalRepository {

    fun isIntroShown(): Boolean

    fun setIntroShown(value: Boolean)

    fun getSelectedJunk(): Int

    fun selectJunk(value: Int)

    fun setVersionName()

    fun isUpdateShown(): Boolean

    fun isTipVisible(): Boolean

    fun setTipVisibility(value: Boolean)
}