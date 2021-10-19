package az.siftoshka.junkyconverter.presentation.screens.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.domain.utils.Constants
import az.siftoshka.junkyconverter.domain.utils.moneyFormat
import az.siftoshka.junkyconverter.presentation.theme.JunkyConverterTheme
import az.siftoshka.junkyconverter.presentation.utils.Padding
import az.siftoshka.junkyconverter.presentation.utils.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

/**
 * Composable function of Main screen.
 */
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {

    val state = viewModel.junkState.value
    val listState = viewModel.junksState.value

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.background)

    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    JunkyConverterTheme {
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetBackgroundColor = MaterialTheme.colors.surface,
            sheetShape = MaterialTheme.shapes.large,
            scrimColor = Color.Transparent,
            sheetContent = {
                LazyColumn(modifier = Modifier.defaultMinSize(minHeight = 1.dp)) {
                    listState.junks?.let { junks ->
                        items(junks.size) {
                            val junk = junks[it]
                            JunkBottomItem(junk.name, junk.icon, junk.iconDescription, junk.id == viewModel.selectedJunk?.id) {
                                viewModel.setJunk(junks[it])
                                scope.launch { sheetState.hide() }
                            }
                        }
                    }
                }
            }
        ) {
            Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.padding(Padding.Default), horizontalAlignment = Alignment.Start
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
                state.junk?.let { Converter(it.name) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ChangeJunkButton { scope.launch { sheetState.show() } }
                    Options(navController)
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(3),
                        modifier = Modifier.padding(Padding.Smaller)
                    ) {
                        items(Constants.numPadNumbers.count()) { index ->
                            NumPadItem(Constants.numPadNumbers[index]) {
                                viewModel.computeYourMoney(Constants.numPadNumbers[index])
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Converter(
    @StringRes name: Int,
    viewModel: MainViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier
            .padding(Padding.Default)
            .offset(y = 256.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Padding.Smallest), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.text_your_money),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = viewModel.yourMoney.moneyFormat(),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary,
                fontSize = 32.sp,
                maxLines = 1
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Padding.Smallest), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = name),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = viewModel.junkMoney.moneyFormat(),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground,
                fontSize = 32.sp,
                maxLines = 1
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ChangeJunkButton(onPerformClick: () -> Unit) {
    OutlinedButton(
        onClick = onPerformClick,
        modifier = Modifier
            .padding(Padding.Default)
            .width(164.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.primary
        )
    ) {
        Text(
            text = stringResource(id = R.string.text_change_junk),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun Options(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    Divider(color = MaterialTheme.colors.onBackground, thickness = 1.dp)
    Row(
        modifier = Modifier.padding(Padding.Smallest),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Padding.Smallest),
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp,
            onClick = {
                viewModel.clearData()
                navController.navigate(Screen.JunksTuning.route)
            }
        ) {
            Text(
                text = stringResource(id = R.string.menu_text_junks_tuning),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(Padding.Default),
                textAlign = TextAlign.Center
            )
        }
        Card(
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Padding.Smallest),
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp,
            onClick = {
                viewModel.clearData()
                navController.navigate(Screen.SettingsScreen.route)
            },
        ) {
            Text(
                text = stringResource(id = R.string.menu_text_settings),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(Padding.Default),
                textAlign = TextAlign.Center
            )
        }
    }
    Divider(color = MaterialTheme.colors.onBackground, thickness = 1.dp)
}

@Composable
fun NumPadItem(
    data: String,
    performClick: () -> Unit
) {
    OutlinedButton(
        onClick = performClick,
        modifier = Modifier.padding(Padding.Smallest),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(1.dp, Color.Transparent),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.Transparent
        )
    ) {
        Text(
            text = data,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(Padding.Small)
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun JunkBottomItem(
    @StringRes name: Int,
    @DrawableRes icon: Int,
    @StringRes contentDescription: Int,
    isSelected: Boolean,
    performClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.large,
        onClick = performClick,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        modifier = Modifier.padding(Padding.Smallest)
    ) {
        ListItem(
            text = {
                Text(
                    text = stringResource(id = name),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                )
            },
            icon = {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = stringResource(id = contentDescription),
                    modifier = Modifier.size(40.dp)
                )
            },
            trailing = {
                if (isSelected) {
                    OutlinedButton(
                        onClick = performClick,
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = Color.Transparent,
                            contentColor = MaterialTheme.colors.primary
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.btn_selected),
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        )
    }
}