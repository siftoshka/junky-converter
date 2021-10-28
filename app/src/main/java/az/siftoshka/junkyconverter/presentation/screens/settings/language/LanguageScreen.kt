package az.siftoshka.junkyconverter.presentation.screens.settings.language

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.domain.util.Constants
import az.siftoshka.junkyconverter.domain.util.Language
import az.siftoshka.junkyconverter.domain.util.getCurrentLanguageCode
import az.siftoshka.junkyconverter.domain.util.getDeviceCountry
import az.siftoshka.junkyconverter.domain.util.getDeviceLanguage
import az.siftoshka.junkyconverter.domain.util.updateLanguage
import az.siftoshka.junkyconverter.presentation.SharedViewModel
import az.siftoshka.junkyconverter.presentation.components.JunkyTopAppBar
import az.siftoshka.junkyconverter.presentation.theme.JunkyConverterTheme
import az.siftoshka.junkyconverter.presentation.util.Padding

/**
 * Composable function of Language screen.
 */
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun LanguageScreen(
    navController: NavController,
    viewModel: SharedViewModel = hiltViewModel()
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    JunkyConverterTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                JunkyTopAppBar(
                    title = R.string.text_settings_language,
                    icon = R.drawable.ic_back,
                    contentDescription = R.string.ic_desc_back
                ) {
                    keyboardController?.hide()
                    navController.popBackStack()
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 1.dp)
                        .padding(horizontal = Padding.Default)
                        .fillMaxWidth()
                ) {
                    items(languages.size) {
                        val language = languages[it]
                        LanguageRowItem(language) { category ->
                            when (category) {
                                LanguageCategory.AUTO -> {
                                    context.updateLanguage(getDeviceLanguage(), getDeviceCountry())
                                }
                                LanguageCategory.ENGLISH -> {
                                    context.updateLanguage(Language.ENGLISH.language, Language.ENGLISH.country)
                                }
                                LanguageCategory.AZERBAIJANI -> {
                                    context.updateLanguage(Language.AZERBAIJANI.language, Language.AZERBAIJANI.country)
                                }
                                LanguageCategory.FRENCH -> {
                                    context.updateLanguage(Language.FRENCH.language, Language.FRENCH.country)
                                }
                                LanguageCategory.SPANISH -> {
                                    context.updateLanguage(Language.SPANISH.language, Language.SPANISH.country)
                                }
                                LanguageCategory.RUSSIAN -> {
                                    context.updateLanguage(Language.RUSSIAN.language, Language.RUSSIAN.country)
                                }
                            }
                            Constants.junkNameRes.forEach { viewModel.junkNames.add(context.getString(it)) }
                            navController.popBackStack()
                            viewModel.initialData()
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun LanguageRowItem(
    language: LanguageItem,
    onPerformClick: (LanguageCategory) -> Unit
) {

    Card(
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        onClick = { onPerformClick(language.category) },
        modifier = Modifier.padding(vertical = Padding.Smallest)
    ) {
        ListItem(
            text = {
                Text(
                    text = stringResource(id = language.text),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                )
            },
            trailing = {
                if (getCurrentLanguageCode() == language.code) {
                    OutlinedButton(
                        onClick = { onPerformClick(language.category) },
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