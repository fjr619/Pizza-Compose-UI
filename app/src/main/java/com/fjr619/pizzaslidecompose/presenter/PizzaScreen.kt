package com.fjr619.pizzaslidecompose.presenter

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fjr619.pizzaslidecompose.presenter.components.PizzaTopBar

@Composable
fun PizzaScreen(viewModel: PizzaViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            PizzaTopBar(isFavorite = state.isFavorite) {
                viewModel.onFavoriteClicked()
            }
        }
    ) { paddingValues ->
        PizzaContent(
            state,
            paddingValues
        )
    }
}

@Composable
private fun PizzaContent(
    state: PizzaUiState,
    paddingValues: PaddingValues
) {

}