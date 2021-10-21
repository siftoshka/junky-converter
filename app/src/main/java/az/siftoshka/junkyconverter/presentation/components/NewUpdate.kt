package az.siftoshka.junkyconverter.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NewUpdate(onPerformClick: () -> Unit) {
    OutlinedButton(
        onClick = onPerformClick,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .height(24.dp)
            .width(32.dp),
        border = BorderStroke(1.dp, MaterialTheme.colors.onBackground),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.primary
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = "•",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground
        )
    }
}