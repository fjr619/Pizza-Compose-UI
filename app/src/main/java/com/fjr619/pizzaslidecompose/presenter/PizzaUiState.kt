package com.fjr619.pizzaslidecompose.presenter

import com.fjr619.pizzaslidecompose.data.DataSource
import com.fjr619.pizzaslidecompose.domain.Pizza

data class PizzaUiState(
    val pizzaList: List<Pizza> = emptyList(),
    val selectedSize: PizzaSize = PizzaSize.MEDIUM,
    val totalPrice: Double = 10.0,
    val isFavorite: Boolean = false,
    val selectedPizza: Pizza = DataSource.pizzaList.first(),
)
enum class PizzaSize(val price: Double) {
    SMALL(10.0),
    MEDIUM(15.0),
    LARGE(20.0),
}