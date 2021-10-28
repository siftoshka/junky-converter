package az.siftoshka.junkyconverter.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import az.siftoshka.junkyconverter.presentation.screens.intro.IntroScreen
import az.siftoshka.junkyconverter.presentation.screens.junksetting.JunkSettingsScreen
import az.siftoshka.junkyconverter.presentation.screens.main.MainScreen
import az.siftoshka.junkyconverter.presentation.screens.settings.language.LanguageScreen
import az.siftoshka.junkyconverter.presentation.screens.settings.SettingsScreen
import az.siftoshka.junkyconverter.presentation.theme.JunkyConverterTheme
import az.siftoshka.junkyconverter.presentation.util.Screen
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

    @ExperimentalComposeUiApi
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
                                slideOutHorizontally(
                                    targetOffsetX = { -300 },
                                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                                ) + fadeOut(animationSpec = tween(400))
                            },
                            popEnterTransition = { _, _ ->
                                slideInHorizontally(
                                    initialOffsetX = { -300 },
                                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                                ) + fadeIn(animationSpec = tween(400))
                            }
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
                            route = Screen.SettingsScreen.route,
                            enterTransition = { _, _ ->
                                slideInHorizontally(
                                    initialOffsetX = { 300 },
                                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                                ) + fadeIn(animationSpec = tween(400))
                            },
                            popExitTransition = { _, _ ->
                                slideOutHorizontally(
                                    targetOffsetX = { 300 },
                                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                                ) + fadeOut(animationSpec = tween(400))
                            },
                            exitTransition = { _, _ ->
                                slideOutHorizontally(
                                    targetOffsetX = { -300 },
                                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                                ) + fadeOut(animationSpec = tween(400))
                            },
                            popEnterTransition = { _, _ ->
                                slideInHorizontally(
                                    initialOffsetX = { -300 },
                                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                                ) + fadeIn(animationSpec = tween(400))
                            }
                        ) {
                            SettingsScreen(navController)
                        }
                        composable(
                            route = Screen.LanguageScreen.route,
                            enterTransition = { _, _ ->
                                slideInHorizontally(
                                    initialOffsetX = { 300 },
                                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                                ) + fadeIn(animationSpec = tween(400))
                            },
                            popExitTransition = { _, _ ->
                                slideOutHorizontally(
                                    targetOffsetX = { 300 },
                                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                                ) + fadeOut(animationSpec = tween(400))
                            }
                        ) {
                            LanguageScreen(navController)
                        }
                        composable(
                            route = Screen.JunksTuning.route,
                            enterTransition = { _, _ ->
                                slideInHorizontally(
                                    initialOffsetX = { 300 },
                                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                                ) + fadeIn(animationSpec = tween(400))
                            },
                            popExitTransition = { _, _ ->
                                slideOutHorizontally(
                                    targetOffsetX = { 300 },
                                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                                ) + fadeOut(animationSpec = tween(400))
                            }
                        ) {
                            JunkSettingsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}