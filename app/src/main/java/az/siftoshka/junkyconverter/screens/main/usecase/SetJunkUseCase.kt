package az.siftoshka.junkyconverter.screens.main.usecase

import az.siftoshka.junkyconverter.data.SharedPrefManager
import javax.inject.Inject

/**
 * Use-case to set a junk.
 */
class SetJunkUseCase @Inject constructor(
    private val prefs: SharedPrefManager
) {

    operator fun invoke(value: Int) = prefs.selectJunk(value)
}