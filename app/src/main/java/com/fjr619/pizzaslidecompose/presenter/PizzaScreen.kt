package com.fjr619.pizzaslidecompose.presenter

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fjr619.pizzaslidecompose.R
import com.fjr619.pizzaslidecompose.domain.Ingredient
import com.fjr619.pizzaslidecompose.presenter.components.Pizza
import com.fjr619.pizzaslidecompose.presenter.components.PizzaIngredients
import com.fjr619.pizzaslidecompose.presenter.components.PizzaSizeSelection
import com.fjr619.pizzaslidecompose.presenter.components.PizzaTopBar
import com.fjr619.pizzaslidecompose.presenter.components.RoundedButton
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaScreen(viewModel: PizzaViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        state.pizzaList.size
    }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.settledPage }
            .collect {
                viewModel.setSelectedPizza(pagerState.settledPage)
            }
    }

    Scaffold(
        topBar = {
            PizzaTopBar(isFavorite = state.isFavorite) {
                viewModel.onFavoriteClicked()
            }
        }
    ) { paddingValues ->
        PizzaContent(
            state = state,
            pagerState = pagerState,
            paddingValues = paddingValues,
            onPizzaSizeClicked = viewModel::onPizzaSizeClicked,
            onIngredientClicked = viewModel::onIngredientClicked
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PizzaContent(
    modifier: Modifier = Modifier,
    state: PizzaUiState,
    pagerState: PagerState,
    paddingValues: PaddingValues,
    onPizzaSizeClicked: (PizzaSize) -> Unit,
    onIngredientClicked: (Ingredient) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Pizza(
            pagerState = pagerState,
            pizzaList = state.pizzaList, pizzaSize = state.selectedSize)

        PizzaPrice(price = state.basePrice + state.toppingPrice)
        PizzaSizeSelection(selectedSize = state.selectedSize, onClick = onPizzaSizeClicked)
        Spacer(modifier = Modifier.weight(1f))
        PizzaIngredients(
            pizza = state.selectedPizza,
            onIngredientClicked = onIngredientClicked,
        )
        RoundedButton(
            label = stringResource(R.string.add_to_cart),
            icon = Icons.Filled.ShoppingCart,
            onClick = { })
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun PizzaPrice(price: Double) {
    Text(
        text = "$$price",
        style = MaterialTheme.typography.headlineMedium,
    )
}