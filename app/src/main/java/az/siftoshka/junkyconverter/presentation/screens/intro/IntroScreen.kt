package az.siftoshka.junkyconverter.presentation.screens.intro

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.domain.util.Constants
import az.siftoshka.junkyconverter.presentation.SharedViewModel
import az.siftoshka.junkyconverter.presentation.components.AnimationLoader
import az.siftoshka.junkyconverter.presentation.theme.JunkyConverterTheme
import az.siftoshka.junkyconverter.presentation.util.Padding
import az.siftoshka.junkyconverter.presentation.util.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Composable function of Introduction screen.
 */
@Composable
fun IntroScreen(
    navController: NavController,
    viewModel: SharedViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.surface)
    val context = LocalContext.current

    Constants.junkNameRes.forEach { viewModel.junkNames.add(context.getString(it)) }
    viewModel.initialData()

    JunkyConverterTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.offset(y = (-32).dp)
            ) {
                Button(
                    onClick = {
                        viewModel.setIntroShown(true)
                        navController.navigate(Screen.MainScreen.route) {
                            popUpTo(Screen.IntroScreen.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .padding(Padding.Default)
                        .width(186.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(50.dp),
                    elevation = ButtonDefaults.elevation()
                ) {
                    Text(
                        text = stringResource(id = R.string.btn_intro),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onPrimary,
                    )
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
            .height(600.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(50.dp).copy(topStart = ZeroCornerSize, topEnd = ZeroCornerSize)
    ) {
        Column(
            modifier = Modifier
                .padding(Padding.Default), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            AnimationLoader(value = R.raw.burger, modifier = Modifier.size(200.dp))
            Text(
                text = stringResource(id = R.string.app_name_introduction),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center,
                fontSize = 46.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.app_description),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = Padding.Smallest)
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "App in the development\n" + stringResource(id = R.string.version_name),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
            )
        }
    }
}