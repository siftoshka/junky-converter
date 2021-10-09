package az.siftoshka.junkyconverter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.siftoshka.junkyconverter.data.JunkRepository
import az.siftoshka.junkyconverter.utils.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * General [ViewModel] to share between screens.
 */
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val prefs: SharedPrefManager,
    private val repository: JunkRepository
) : ViewModel() {

    fun isIntroShown() = prefs.isIntroShown()

    fun setIntroShown(value: Boolean) = prefs.setIntroShown(value)

    fun setInitialData() {
        viewModelScope.launch {
            repository.insertInitialJunks()
        }
    }
}