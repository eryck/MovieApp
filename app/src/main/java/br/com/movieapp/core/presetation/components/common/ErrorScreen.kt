package br.com.movieapp.core.presetation.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.movieapp.ui.theme.white
import br.com.movieapp.ui.theme.yellow

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    retry: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            maxLines = 3,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            fontStyle = FontStyle.Italic,
            fontSize = 14.sp,
            color = white
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = yellow,
                contentColor = Color.White
            ),
            onClick = retry
        ) {
            Text(text = "Recarregar")
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        message = "Error",
        retry = {}
    )
}