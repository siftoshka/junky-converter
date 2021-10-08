package az.siftoshka.junkyconverter

import Screen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import az.siftoshka.junkyconverter.screens.intro.IntroScreen
import az.siftoshka.junkyconverter.screens.junkstuning.JunkSettingsScreen
import az.siftoshka.junkyconverter.screens.main.MainScreen
import az.siftoshka.junkyconverter.screens.settings.SettingsScreen
import az.siftoshka.junkyconverter.ui.theme.JunkyConverterTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * The MainActivity of the app. There is only single activity.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: SharedViewModel by viewModels()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JunkyConverterTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.IntroScreen.route
                    ) {
                        composable(
                            route = Screen.IntroScreen.route
                        ) {
                            if (viewModel.isIntroShown()) MainScreen(navController)
                            else IntroScreen(navController)
                        }
                        composable(
                            route = Screen.MainScreen.route
                        ) {
                            MainScreen(navController)
                        }
                        composable(
                            route = Screen.SettingsScreen.route
                        ) {
                            SettingsScreen()
                        }
                        composable(
                            route = Screen.JunksTuning.route
                        ) {
                            JunkSettingsScreen()
                        }
                    }
                }
            }
        }
    }
}