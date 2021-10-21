package az.siftoshka.junkyconverter.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.siftoshka.junkyconverter.data.repository.SharedPrefManager
import az.siftoshka.junkyconverter.domain.repository.JunkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    private val _updateState = mutableStateOf(false)
    val updateState: State<Boolean> = _updateState

    fun isIntroShown() = prefs.isIntroShown()

    fun setIntroShown(value: Boolean) = prefs.setIntroShown(value)

    fun setInitialData() {
        viewModelScope.launch {
            repository.insertInitialJunks()
        }
    }

    fun isUpdateShown() {
        viewModelScope.launch {
            delay(2000)
            _updateState.value = !prefs.isUpdateShown()
        }
    }

    fun setUpdateShown() {
        prefs.setVersionName().also {
            _updateState.value = !prefs.isUpdateShown()
        }
    }
}