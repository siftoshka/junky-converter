package az.siftoshka.junkyconverter.screens.intro

import Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.SharedViewModel
import az.siftoshka.junkyconverter.ui.theme.JunkyConverterTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Composable function of Introduction screen.
 */
@Preview(showBackground = true)
@Composable
fun IntroScreen(
    navController: NavController,
    viewModel: SharedViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.surface)

    viewModel.setInitialData()

    JunkyConverterTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.offset(y = (-64).dp)
            ) {
                Button(
                    onClick = {
                        //viewModel.setIntroShown(true)
                        navController.navigate(Screen.MainScreen.route) {
                            popUpTo(Screen.IntroScreen.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(164.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(50.dp),
                    elevation = ButtonDefaults.elevation()
                ) {
                    Text(text = stringResource(id = R.string.btn_intro))
                }
            }
        }
        IntroductionCard()
    }
}

@Composable
fun IntroductionCard() {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier
            .height(700.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(60.dp).copy(topStart = ZeroCornerSize, topEnd = ZeroCornerSize)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.img_desc_logo),
                Modifier.size(300.dp)
            )
            Text(
                text = stringResource(id = R.string.app_name_introduction),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.app_description),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "App in the development",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
            )
        }
    }
}