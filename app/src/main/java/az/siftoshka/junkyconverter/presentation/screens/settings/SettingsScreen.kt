package az.siftoshka.junkyconverter.presentation.screens.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.domain.utils.Social
import az.siftoshka.junkyconverter.domain.utils.getCurrentLanguage
import az.siftoshka.junkyconverter.domain.utils.getGithubIntent
import az.siftoshka.junkyconverter.domain.utils.getInstagramIntent
import az.siftoshka.junkyconverter.domain.utils.getTelegramIntent
import az.siftoshka.junkyconverter.presentation.SharedViewModel
import az.siftoshka.junkyconverter.presentation.components.AnimationLoader
import az.siftoshka.junkyconverter.presentation.components.FoldableText
import az.siftoshka.junkyconverter.presentation.components.JunkyTopAppBar
import az.siftoshka.junkyconverter.presentation.theme.JunkyConverterTheme
import az.siftoshka.junkyconverter.presentation.utils.Padding
import az.siftoshka.junkyconverter.presentation.utils.Screen
import az.siftoshka.junkyconverter.presentation.utils.SocialColors

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
    val tipState = remember { mutableStateOf(viewModel.isTipVisible()) }
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    JunkyConverterTheme {
        Surface(
            color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                JunkyTopAppBar(
                    title = R.string.settings_title,
                    icon = R.drawable.ic_back,
                    contentDescription = R.string.ic_desc_back
                ) {
                    keyboardController?.hide()
                    navController.popBackStack()
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Padding.Default)
                        .weight(1f)
                        .verticalScroll(scrollState)
                ) {
                    TitleText(text = R.string.text_settings_general)
                    ButtonItem(
                        text = R.string.text_settings_language,
                        getCurrentLanguage()
                    ) { navController.navigate(Screen.LanguageScreen.route) }
                    SwitchItem(
                        text = R.string.text_settings_show_tip_title,
                        description = R.string.text_settings_show_tip_description,
                        isChecked = tipState
                    ) {
                        tipState.value = it
                        viewModel.setTipVisibility(it)
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                    TitleText(text = R.string.text_settings_about)
                    TextItem(text = R.string.text_settings_app_version, secondaryText = R.string.version_name)
                    ContactMeField(text = R.string.text_settings_contact_me) {
                        when (it) {
                            Social.TELEGRAM -> getTelegramIntent(context)
                            Social.GITHUB -> getGithubIntent(context)
                            Social.INSTAGRAM -> getInstagramIntent(context)
                        }
                    }
                    FoldableText(shortText = R.string.text_settings_credits, longText = R.string.text_settings_credits_full)
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        AnimationLoader(value = R.raw.ingredients, modifier = Modifier.size(200.dp))
                    }
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
        modifier = Modifier.padding(bottom = Padding.Smallest)
    )
}

@ExperimentalMaterialApi
@Composable
fun ButtonItem(
    @StringRes text: Int,
    language: String,
    onPerformClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        onClick = { onPerformClick() },
        modifier = Modifier.padding(vertical = Padding.Smallest)
    ) {
        ListItem(
            text = {
                Text(
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                )
            },
            trailing = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = language,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondaryVariant,
                    )
                    IconButton(onClick = onPerformClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_forward),
                            contentDescription = stringResource(id = R.string.ic_desc_forward),
                        )
                    }
                }
            }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun SwitchItem(
    @StringRes text: Int,
    @StringRes description: Int,
    isChecked: MutableState<Boolean>,
    onPerformClick: (Boolean) -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        onClick = { onPerformClick(!isChecked.value) },
        modifier = Modifier.padding(vertical = Padding.Smallest)
    ) {
        ListItem(
            text = {
                Text(
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                )
            },
            secondaryText = {
                Text(
                    text = stringResource(id = description),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier.padding(vertical = Padding.Small)
                )
            },
            trailing = {
                Switch(
                    checked = isChecked.value,
                    onCheckedChange = { onPerformClick(it) },
                    colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colors.primary)
                )
            }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun TextItem(
    @StringRes text: Int,
    @StringRes secondaryText: Int
) {
    Card(
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        modifier = Modifier.padding(vertical = Padding.Smallest)
    ) {
        ListItem(
            text = {
                Text(
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                )
            },
            trailing = {
                Text(
                    text = stringResource(id = secondaryText),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier.padding(vertical = Padding.Small)
                )
            }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun ContactMeField(
    @StringRes text: Int,
    onPerformClick: (Social) -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        modifier = Modifier.padding(vertical = Padding.Smallest)
    ) {
        Column(modifier = Modifier.padding(Padding.Default)) {
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(bottom = Padding.Small)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Card(
                    shape = MaterialTheme.shapes.large,
                    backgroundColor = SocialColors.Telegram,
                    contentColor = MaterialTheme.colors.onPrimary,
                    onClick = { onPerformClick(Social.TELEGRAM) },
                    modifier = Modifier.width(100.dp),
                    elevation = 0.dp
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_telegram),
                        contentDescription = stringResource(id = R.string.ic_desc_telegram),
                        modifier = Modifier.padding(Padding.Small)
                    )
                }
                Card(
                    shape = MaterialTheme.shapes.large,
                    backgroundColor = SocialColors.Github,
                    contentColor = MaterialTheme.colors.onPrimary,
                    onClick = { onPerformClick(Social.GITHUB) },
                    modifier = Modifier.width(100.dp),
                    elevation = 0.dp
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_github),
                        contentDescription = stringResource(id = R.string.ic_desc_github),
                        modifier = Modifier.padding(Padding.Small)
                    )
                }
                Card(
                    shape = MaterialTheme.shapes.large,
                    backgroundColor = SocialColors.Instagram,
                    contentColor = MaterialTheme.colors.onPrimary,
                    onClick = { onPerformClick(Social.INSTAGRAM) },
                    modifier = Modifier.width(100.dp),
                    elevation = 0.dp
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_instagram),
                        contentDescription = stringResource(id = R.string.ic_desc_instagram),
                        modifier = Modifier.padding(Padding.Small)
                    )
                }
            }
        }
    }
}