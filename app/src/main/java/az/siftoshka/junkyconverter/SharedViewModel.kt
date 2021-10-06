package az.siftoshka.junkyconverter

import androidx.lifecycle.ViewModel
import az.siftoshka.junkyconverter.utils.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * General ViewModel to share between screens.
 */
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val prefs: SharedPrefManager
) : ViewModel() {

    fun isIntroShown() = prefs.isIntroShown()

    fun setIntroShown(value: Boolean) = prefs.setIntroShown(value)
}