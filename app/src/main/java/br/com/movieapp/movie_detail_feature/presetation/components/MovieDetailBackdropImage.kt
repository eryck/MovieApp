package br.com.movieapp.movie_detail_feature.presetation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.core.presetation.components.common.AsyncImageUrl

@Composable
fun MovieDetailBackdropImage(
    modifier: Modifier = Modifier,
    backdropImageUrl: String
) {
    Box(
        modifier = modifier
    ) {
        AsyncImageUrl(
            modifier = Modifier.fillMaxWidth(),
            imageUrl = backdropImageUrl,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
private fun MovieDetailBackdropImagePreview() {
    MovieDetailBackdropImage(
        backdropImageUrl = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}