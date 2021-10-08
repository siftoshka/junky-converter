package az.siftoshka.junkyconverter.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import az.siftoshka.junkyconverter.ui.theme.JunkyConverterTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Composable function of Main screen.
 */
@Preview(showBackground = true)
@Composable
fun MainScreen() {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.background)

    JunkyConverterTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            Text(text = "MAIN")
        }
    }
}