package az.siftoshka.junkyconverter.screens.main

import az.siftoshka.junkyconverter.Screen
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.data.model.Junk
import az.siftoshka.junkyconverter.ui.theme.JunkyConverterTheme
import az.siftoshka.junkyconverter.utils.Constants
import az.siftoshka.junkyconverter.utils.moneyFormat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Composable function of Main screen.
 */
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
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
                            JunkBottomItem(junk, viewModel, scope, sheetState)
                        }
                    }
                }
            }
        ) {
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
                state.junk?.let {
                    Converter(it, viewModel)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ChangeJunkButton(scope, sheetState)
                    Options(navController)
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(3),
                        modifier = Modifier.padding(12.dp)
                    ) {
                        items(Constants.numPadNumbers.count()) { index ->
                            val pad = Constants.numPadNumbers[index]
                            NumPadItem(pad, viewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Converter(junk: Junk, viewModel: MainViewModel) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .offset(y = 256.dp),
        horizontalArrangement = Arrangement.Center,
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
                text = viewModel.yourMoney.moneyFormat(),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary,
                fontSize = 36.sp,
                maxLines = 1
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
                text = viewModel.junkMoney.moneyFormat(),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface,
                fontSize = 36.sp,
                maxLines = 1
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ChangeJunkButton(scope: CoroutineScope, state: ModalBottomSheetState) {
    OutlinedButton(
        onClick = { scope.launch { state.show() } },
        modifier = Modifier
            .padding(16.dp)
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
fun Options(navController: NavController) {
    Divider(color = MaterialTheme.colors.onBackground, thickness = 1.dp)
    Row(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate(Screen.JunksTuning.route) },
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp
        ) {
            Text(
                text = stringResource(id = R.string.menu_text_junks_tuning),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate(Screen.SettingsScreen.route) },
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp
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

@Composable
fun NumPadItem(data: String, viewModel: MainViewModel) {
    OutlinedButton(
        onClick = { viewModel.computeYourMoney(data) },
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(10.dp),
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
            modifier = Modifier.padding(8.dp)
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun JunkBottomItem(data: Junk, viewModel: MainViewModel, scope: CoroutineScope, sheetState: ModalBottomSheetState) {
    Card(
        shape = RoundedCornerShape(10.dp),
        onClick = {
            viewModel.setJunk(data.id)

            scope.launch { sheetState.hide() }
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        modifier = Modifier.padding(4.dp)
    ) {
        ListItem(
            text = {
                Text(
                    text = stringResource(id = data.name),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                )
            },
            icon = {
                Icon(
                    painter = painterResource(id = data.icon),
                    contentDescription = "Localized description",
                    modifier = Modifier.size(64.dp)
                )
            }
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    MainScreen(rememberNavController())
}