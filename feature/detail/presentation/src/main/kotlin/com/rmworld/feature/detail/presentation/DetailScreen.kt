@file:OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationApi::class)
package com.rmworld.feature.detail.presentation

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.rmworld.core.common.paging.LoadState
import com.rmworld.feature.detail.domain.model.Character
import components.shimmer
import extensions.sharedElement
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
    val isLoading = uiState.loadState is LoadState.Loading
    UiTextErrorEffect(uiState = uiState)
    CharacterDetailContent(
        sharedId = characterId,
        character = character,
        isLoading = isLoading,
    )
}

@Composable
fun CharacterDetailContent(
    sharedId: Int,
    character: Character?,
    isLoading: Boolean,
) {
    val shouldLoadImage = !isLoading
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RickAndMortyTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageModifier = Modifier
            .sharedElement("photo_".plus(sharedId))
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))

        if (shouldLoadImage) {
            AsyncImage(
                model = character?.image,
                contentDescription = "Character Image",
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = imageModifier
                    .shimmer(isLoading = true)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character?.name ?: "No Name Provided",
            fontWeight = FontWeight.Bold,
            color = RickAndMortyTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        RickyMortyCharacterStatus(
            status = character?.status,
            species = character?.species
        )
        Spacer(modifier = Modifier.height(16.dp))
        RickyMortyCharacterInfoCard("Last known location:", character?.location?.name ?: "No Location Provided", isLoading)
        Spacer(modifier = Modifier.height(12.dp))
        RickyMortyCharacterInfoCard("First seen in:", character?.origin?.name ?: "No Origin Available", isLoading)
    }
}

@Composable
fun RickyMortyCharacterStatus(
    status: String?,
    species: String?
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val displayStatus = status ?: "No Status Available"
        val statusColor = when (displayStatus) {
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
            text = "${displayStatus} - ${species}",
            color = RickAndMortyTheme.colors.textSecondary
        )
    }
}

@Composable
fun RickyMortyCharacterInfoCard(
    title: String,
    value: String,
    isLoading: Boolean
) {
    val infoCardModifier = if (!isLoading) {
        Modifier.fillMaxWidth()
    } else {
        Modifier
            .fillMaxWidth()
            .shimmer(
                isLoading = isLoading,
                cornerRadius = 12.dp
            )
    }

    Card(
        modifier = infoCardModifier,
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
@Composable
fun UiTextErrorEffect(uiState: DetailUiState){
    val context = LocalContext.current
    LaunchedEffect(uiState.uiText) {
        uiState.uiText?.let { uiText ->
            Toast.makeText(context, uiText.asString(context), Toast.LENGTH_SHORT).show()
        }
    }
}
