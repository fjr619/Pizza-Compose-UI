package com.fjr619.pizzaslidecompose.presenter.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fjr619.pizzaslidecompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaTopBar(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onFavoriteClicked: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.order_screen_title))
        },
        navigationIcon = {
            IconButton(onClick = onFavoriteClicked) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = onFavoriteClicked) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.favorite)
                )
            }
        }
    )
}