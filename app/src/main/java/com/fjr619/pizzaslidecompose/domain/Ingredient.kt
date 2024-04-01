package com.fjr619.pizzaslidecompose.domain

data class Ingredient(
    val id: Int,
    val name: String,
    val price: Double,
    val imageRes: Int,
    val images: List<Int> = emptyList(),
    val selected: Boolean = false,
)