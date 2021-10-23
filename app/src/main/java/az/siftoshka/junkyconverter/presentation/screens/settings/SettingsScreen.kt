package az.siftoshka.junkyconverter.presentation.screens.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.presentation.SharedViewModel
import az.siftoshka.junkyconverter.presentation.components.JunkyTopAppBar
import az.siftoshka.junkyconverter.presentation.theme.JunkyConverterTheme
import az.siftoshka.junkyconverter.presentation.utils.Padding

/**
 * Composable function of Settings screen.
 */
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SharedViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    JunkyConverterTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                JunkyTopAppBar(
                    title = R.string.settings_title,
                    icon = R.drawable.ic_back,
                    contentDescription = R.string.ic_desc_back
                ) {
                    keyboardController?.hide()
                    navController.popBackStack()
                }
                Column(modifier = Modifier.padding(Padding.Default)) {
                    TitleText(text = R.string.text_settings_general)
                    SwitchItem(
                        text = R.string.text_settings_show_tip_title,
                        description = R.string.text_settings_show_tip_description,
                        isChecked = mutableStateOf(viewModel.isTipVisible())
                    )
                }
            }
        }
    }
}

@Composable
fun TitleText(@StringRes text: Int) {
    Text(
        text = stringResource(id = text),
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(bottom = Padding.Small)
    )
}

@ExperimentalMaterialApi
@Composable
fun SwitchItem(
    @StringRes text: Int,
    @StringRes description: Int,
    isChecked: MutableState<Boolean>,
    viewModel: SharedViewModel = hiltViewModel()
) {
    Card(
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ) {
        ListItem(
            text = {
                Text(
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.onSurface,
                )
            },
            secondaryText = {
                Text(
                    text = stringResource(id = description),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(vertical = Padding.Small)
                )
            },
            trailing = {
                Switch(
                    checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = it
                        viewModel.setTipVisibility(it)
                    },
                    colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colors.primary)
                )
            }
        )
    }
}