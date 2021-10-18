package az.siftoshka.junkyconverter.screens.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.siftoshka.junkyconverter.ui.theme.Padding

@ExperimentalMaterialApi
@Composable
fun Tip(
    @StringRes shortText: Int,
    @StringRes longText: Int
) {
    var short by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp,
        onClick = { short = !short },
        modifier = Modifier
            .wrapContentSize()
            .animateContentSize(tween(600))
    ) {
        Text(
            text = if (short) stringResource(id = shortText) else stringResource(id = longText),
            style = LocalTextStyle.current.copy(color = MaterialTheme.colors.onPrimary),
            modifier = Modifier.padding(Padding.Default)
        )
    }
}