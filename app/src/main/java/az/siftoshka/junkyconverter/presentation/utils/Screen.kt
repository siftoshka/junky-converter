package az.siftoshka.junkyconverter.presentation.utils

/**
 * The sealed class for the navigation between [Screen]-s.
 */
sealed class Screen(val route: String) {
    object IntroScreen : Screen("intro_screen")
    object MainScreen : Screen("main_screen")
    object SettingsScreen : Screen("settings_screen")
    object JunksTuning : Screen("junks_tuning_screen")
}