package az.siftoshka.junkyconverter.screens.settings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import az.siftoshka.junkyconverter.SharedViewModel

/**
 * Composable function of Settings screen.
 */
@Composable
fun SettingsScreen(
    viewModel: SharedViewModel = hiltViewModel()
) {

    Text(text = "SETTINGS")
}