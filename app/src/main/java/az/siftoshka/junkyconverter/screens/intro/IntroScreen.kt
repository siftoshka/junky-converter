package az.siftoshka.junkyconverter.screens.intro

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.siftoshka.junkyconverter.SharedViewModel

/**
 * Composable function of Introduction screen.
 */
@Composable
fun IntroScreen(
    navController: NavController,
    viewModel: SharedViewModel = hiltViewModel()
) {

    Text(text = "INTRO")
}