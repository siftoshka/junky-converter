package az.siftoshka.junkyconverter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import az.siftoshka.junkyconverter.screens.intro.IntroScreen
import az.siftoshka.junkyconverter.screens.junksetting.JunkSettingsScreen
import az.siftoshka.junkyconverter.screens.main.MainScreen
import az.siftoshka.junkyconverter.screens.settings.SettingsScreen
import az.siftoshka.junkyconverter.ui.theme.JunkyConverterTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

/**
 * The MainActivity of the app. There is only single activity.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: SharedViewModel by viewModels()

    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JunkyConverterTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screen.IntroScreen.route
                    ) {
                        composable(
                            route = Screen.IntroScreen.route,
                            exitTransition = { _, _ ->
                                slideOutVertically(
                                    { -300 }, tween(300, easing = FastOutLinearInEasing)
                                ) + fadeOut(animationSpec = tween(600))
                            },
                            popEnterTransition = { _, _ ->
                                slideInVertically(
                                    { -300 }, tween(300, easing = FastOutLinearInEasing)
                                ) + fadeIn(animationSpec = tween(600))
                            }
                        ) {
                            if (viewModel.isIntroShown()) MainScreen(navController)
                            else IntroScreen(navController)
                        }
                        composable(
                            route = Screen.MainScreen.route,
                            enterTransition = { _, _ ->
                                slideInVertically(
                                    { 300 }, tween(300, easing = FastOutLinearInEasing)
                                ) + fadeIn(animationSpec = tween(600))
                            },
                            popExitTransition = { _, _ ->
                                slideOutVertically(
                                    { 300 }, tween(300, easing = FastOutLinearInEasing)
                                ) + fadeOut(animationSpec = tween(600))
                            }
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
                            JunkSettingsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}