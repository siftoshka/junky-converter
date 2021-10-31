package az.siftoshka.junkyconverter.presentation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Surface
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.di.AppModule
import az.siftoshka.junkyconverter.domain.util.TestTag
import az.siftoshka.junkyconverter.presentation.screens.main.MainScreen
import az.siftoshka.junkyconverter.presentation.theme.JunkyConverterTheme
import az.siftoshka.junkyconverter.presentation.util.Screen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * TODO add class description
 */
@HiltAndroidTest
@UninstallModules(AppModule::class)
class TestMainComponents {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    @Before
    fun setUpTesting() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            JunkyConverterTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        composable(route = Screen.MainScreen.route) {
                            MainScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }

    @Test
    fun bottomSheet_isVisible() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        /** Due to issue with [ModalBottomSheetLayout] the minimum height is 1dp **/
        composeRule.onNodeWithTag(TestTag.MODAL_BOTTOM_SHEET).assertHeightIsAtLeast(1.dp)
        composeRule.onNodeWithText(context.getString(R.string.text_change_junk)).performClick()
        composeRule.onNodeWithTag(TestTag.MODAL_BOTTOM_SHEET).assertHeightIsAtLeast(3.dp)
    }
}