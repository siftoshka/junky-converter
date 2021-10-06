package az.siftoshka.junkyconverter.screens.junksettings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import az.siftoshka.junkyconverter.SharedViewModel

/**
 * Composable function of Junk Settings screen.
 */
@Composable
fun JunkSettingsScreen(
    viewModel: SharedViewModel = hiltViewModel()
) {

    Text(text = "JUNK SETTINGS")
}