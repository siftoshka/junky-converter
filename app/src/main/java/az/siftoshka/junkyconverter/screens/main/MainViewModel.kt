package az.siftoshka.junkyconverter.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.siftoshka.junkyconverter.data.model.Junk
import az.siftoshka.junkyconverter.screens.main.usecase.GetJunkUseCase
import az.siftoshka.junkyconverter.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * The [ViewModel] of the [MainScreen].
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getJunkUseCase: GetJunkUseCase
) : ViewModel() {

    private val _junkState = mutableStateOf(SelectedJunkState())
    val junkState: State<SelectedJunkState> = _junkState

    var yourMoney: String by mutableStateOf("0")
        private set

    var junkMoney: String by mutableStateOf("0")
        private set

    private val moneyBuilder = StringBuilder()

    init {
        getSelectedJunk()
    }

    private fun getSelectedJunk() {
        getJunkUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _junkState.value = SelectedJunkState(junk = result.data)
                }
                is Resource.Error -> {
                    _junkState.value = SelectedJunkState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _junkState.value = SelectedJunkState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun computeYourMoney(value: String) {
        when (value) {
            "←" -> {
                if (moneyBuilder.isNotEmpty()) {
                    moneyBuilder.deleteCharAt(moneyBuilder.lastIndex)
                    yourMoney = moneyBuilder.toString()
                }
                if (moneyBuilder.isEmpty()) {
                    moneyBuilder.clear()
                    yourMoney = "0"
                }
            }
            else -> {
                if (moneyBuilder.length <= 4 && !moneyBuilder.contains(".##")) {
                    moneyBuilder.append(value)
                    yourMoney = moneyBuilder.toString()
                }
            }
        }
        computeJunkMoney()
    }

    private fun computeJunkMoney() {
        junkMoney = if (yourMoney != "0") {
            yourMoney.toFloat().times(_junkState.value.junk?.value ?: 1f).toString()
        } else "0"
    }
}

data class SelectedJunkState(
    val isLoading: Boolean = false,
    val junk: Junk? = null,
    val error: String = ""
)