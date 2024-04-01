package com.fjr619.pizzaslidecompose.domain

data class Pizza(
    val id: Int,
    val name: String,
    val price: Double,
    val breadRes: Int,
    val ingredients: List<Ingredient> = emptyList(),
)

