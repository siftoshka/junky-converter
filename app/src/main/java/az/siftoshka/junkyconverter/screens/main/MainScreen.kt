package az.siftoshka.junkyconverter.screens.main

import Screen
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.data.model.Junk
import az.siftoshka.junkyconverter.ui.theme.JunkyConverterTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Composable function of Main screen.
 */
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.background)

    JunkyConverterTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(id = R.string.text_junky),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 42.sp
                )
                Text(
                    text = stringResource(id = R.string.text_converter),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground,
                )
            }
            println(state.junk.toString())
            state.junk?.let {
                Converter(it)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                ChangeJunkButton()
                Options(navController)
            }
        }
    }
}

@Composable
fun Converter(junk: Junk) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.text_your_money),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "0",
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary,
                fontSize = 36.sp
            )
        }
        Column(
            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = junk.name),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "0",
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface,
                fontSize = 36.sp
            )
        }
    }
}

@Composable
fun ChangeJunkButton() {
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(8.dp)
            .width(164.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.primary
        )
    ) {
        Text(text = stringResource(id = R.string.text_change_junk))
    }
}

@ExperimentalMaterialApi
@Composable
fun Options(navController: NavController) {
    Divider(color = MaterialTheme.colors.onBackground, thickness = 1.dp)
    Row(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate(Screen.JunksTuning.route) },
            backgroundColor = MaterialTheme.colors.background
        ) {
            Text(
                text = stringResource(id = R.string.menu_text_junks_tuning),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
        Card(shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate(Screen.SettingsScreen.route) },
            backgroundColor = MaterialTheme.colors.background
        ) {
            Text(
                text = stringResource(id = R.string.menu_text_settings),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
    Divider(color = MaterialTheme.colors.onBackground, thickness = 1.dp)
}