package az.siftoshka.junkyconverter.screens.junksetting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.data.model.Junk
import az.siftoshka.junkyconverter.screens.components.JunkyTopAppBar
import az.siftoshka.junkyconverter.ui.theme.JunkyConverterTheme
import az.siftoshka.junkyconverter.ui.theme.Padding

/**
 * Composable function of Junk Settings screen.
 */
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun JunkSettingsScreen(
    navController: NavController,
    viewModel: JunkSettingsViewModel = hiltViewModel()
) {

    val listState = viewModel.junksState.value
    val keyboardController = LocalSoftwareKeyboardController.current

    JunkyConverterTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                JunkyTopAppBar(
                    title = R.string.junk_settings_title,
                    icon = R.drawable.ic_back,
                    contentDescription = R.string.ic_desc_back
                ) {
                    keyboardController?.hide()
                    navController.popBackStack()
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = Padding.Default)
                        .fillMaxWidth()
                ) {
                    LazyColumn(modifier = Modifier.defaultMinSize(minHeight = 1.dp)) {
                        listState.junks?.let { junks ->
                            items(junks.size) {
                                val junk = junks[it]
                                JunkItem(junk) {
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun JunkItem(
    junk: Junk,
    viewModel: JunkSettingsViewModel = hiltViewModel(),
    performClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.large,
        onClick = performClick,
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
    ) {
        var value by remember { mutableStateOf(junk.value.toString()) }

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = junk.icon),
                contentDescription = stringResource(id = junk.iconDescription),
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = stringResource(id = junk.name),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = value,
                modifier = Modifier.width(100.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(10.dp),
                onValueChange = {
                    value = it
                    viewModel.updateJunk(junk, it.toFloatOrNull())
                }
            )
        }
    }
}