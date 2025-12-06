package com.feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import coil.compose.AsyncImage
import com.feature.home.domain.model.Character
import com.feature.home.domain.model.Location
import com.feature.home.domain.model.Origin
import com.rmworld.core.common.paging.LoadState
import components.shimmer
import theme.RickAndMortyTheme

@Composable
internal fun HomeRoute(
    backStackEntry: NavBackStackEntry,
    onCharacterClicked:(Int)->Unit
){
    HomeScreen(
        viewModel = hiltViewModel(backStackEntry)
    ){characterId->
      onCharacterClicked.invoke(characterId)
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    action: (HomeUiAction)-> Unit = viewModel.action,
    onCharacterClicked:(Int)-> Unit
){
    val feedState by viewModel.uiState.collectAsStateWithLifecycle()
    if (feedState.shouldNavToDetailScreen){
        onCharacterClicked.invoke(feedState.selectedId)
        action.invoke(HomeUiAction.ResetNav)
    }
    val isLoading = feedState.loadState is LoadState.Loading
    RickyAndMortyCharacterContent(uiState = feedState, isLoading = isLoading, action = action)
}
@Composable
private fun RickyAndMortyCharacterContent(
    uiState: HomeUiState,
    isLoading: Boolean,
    action: (HomeUiAction)-> Unit = {}
){

    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,) {
        LazyColumn {
            if (isLoading && uiState.data.isEmpty()) {
                items(10) {
                    RickAndMortyCharacterCard(
                        character = Character(
                            name = "Calypso",
                            status = "Dead",
                            species = "Human",
                            location = Location(name = "unknown", url = ""),
                            origin = Origin(name = "Vindicators 3: The Return of Worldender", url = ""),
                            image = "",
                        ),
                        action = action,
                        isLoading = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            } else {
                items(count = uiState.data.size) { index ->
                    RickAndMortyCharacterCard(
                        character = uiState.data[index],
                        action = action,
                        isLoading = false
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun RickAndMortyCharacterCard(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    character: Character,
    action: (HomeUiAction)-> Unit = {}
) {
       if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .shimmer(isLoading = true, cornerRadius = 12.dp) // isLoading is always true here
            )
        } else {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        action(HomeUiAction.NavigateToDetailScreen(character.id))
                    }
                    .height(160.dp)
                    .background(Color(0xFF3C3E44), shape = RoundedCornerShape(12.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                )

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = character.name,
                        fontWeight = FontWeight.Bold,
                        color = RickAndMortyTheme.colors.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val statusColor = when (character.status) {
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
                            text = "${character.status} - ${character.species}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "Last known location:",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.LightGray
                    )
                    Text(
                        text = character.location.name,
                        color = RickAndMortyTheme.colors.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "First seen in:",
                        style = MaterialTheme.typography.labelMedium,
                        color = RickAndMortyTheme.colors.textSecondary
                    )
                    Text(
                        text = character.origin.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = RickAndMortyTheme.colors.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }

@Preview
@Composable
private fun RickAndMortyCharacterCardPreview() {
    RickAndMortyTheme {
        RickAndMortyCharacterCard(
            character = Character(
                name = "Calypso",
                status = "Dead",
                species = "Human",
                location = Location(name = "unknown", url = ""),
                origin = Origin(name = "Vindicators 3: The Return of Worldender", url = ""),
                image = "",
            ),
            modifier = TODO(),
            isLoading = TODO(),
            action = TODO()
        )
    }
}
