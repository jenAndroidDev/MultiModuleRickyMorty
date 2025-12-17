package com.rmworld.feature.detail.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.rmworld.feature.detail.domain.model.Character
import com.rmworld.feature.detail.domain.model.Location
import com.rmworld.feature.detail.domain.model.Origin
import theme.RickAndMortyTheme

@Composable
internal fun DetailRoute(id: Int) {

    DetailScreen(characterId = id)
}

@Composable
 fun DetailScreen(
    characterId: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getCharacterById(characterId)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val character = uiState.data
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RickAndMortyTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = character?.image,
            contentDescription = character?.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = character?.name?:"No Name Provided",
            fontWeight = FontWeight.Bold,
            color = RickAndMortyTheme.colors.textPrimary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            val status = character?.status?:"No Status Available"
            val statusColor = when (status) {
                "Alive" -> Color.Green
                "Dead" -> Color.Red
                else -> Color.Gray
            }
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(statusColor, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "${status} - ${character?.species}",
                color = RickAndMortyTheme.colors.textSecondary
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        InfoCard("Last known location:", character?.location?.name?:"No Location Provided")
        Spacer(modifier = Modifier.height(12.dp))
        InfoCard("First seen in:", character?.origin?.name?:"No Origin Available")
    }
}

@Composable
fun InfoCard(title: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = RickAndMortyTheme.colors.brand
                .copy(alpha = 0.3f),
            )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                color = RickAndMortyTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                color = RickAndMortyTheme.colors.textSecondary
            )
        }
    }
}

