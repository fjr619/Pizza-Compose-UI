package com.fjr619.pizzaslidecompose.presenter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fjr619.pizzaslidecompose.R
import com.fjr619.pizzaslidecompose.domain.Ingredient
import com.fjr619.pizzaslidecompose.domain.Pizza

@Composable
fun PizzaIngredients(
    modifier: Modifier = Modifier,
    pizza: Pizza,
    onIngredientClicked: (Ingredient) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            stringResource(R.string.customize_your_pizza),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                count = pizza.ingredients.size,
                key = { pizza.ingredients[it].id}) { index ->
                IngredientRoundedButton(
                    imageRes = pizza.ingredients[index].imageRes,
                    onClick = { onIngredientClicked(pizza.ingredients[index]) },
                    selected = pizza.ingredients[index].selected
                )
            }
        }
    }
}