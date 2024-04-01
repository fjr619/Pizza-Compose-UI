package com.fjr619.pizzaslidecompose.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fjr619.pizzaslidecompose.data.DataSource
import com.fjr619.pizzaslidecompose.domain.Ingredient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PizzaViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(PizzaUiState())
    val state = _state.asStateFlow()

    init {
        getPizzaList()
    }

    private fun getPizzaList() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    pizzaList = DataSource.pizzaList.toMutableList()
                )
            }
        }
    }

    fun onFavoriteClicked() {
        _state.update { it.copy(isFavorite = !state.value.isFavorite) }
    }

    fun onPizzaSizeClicked(size: PizzaSize) {
        _state.update {
            it.copy(
                selectedSize = size,
                basePrice = size.price
            )
        }
    }

    fun setSelectedPizza(page: Int) {
        _state.update { state ->
            val newSelectedPizza = state.pizzaList[page]
            var newToppingPrice = 0.0
            newSelectedPizza.ingredients.filter {
                it.selected
            }.forEach {
                newToppingPrice += it.price
            }

            state.copy(selectedPizza = newSelectedPizza, toppingPrice = newToppingPrice)
        }
    }

    fun onIngredientClicked(ingredient: Ingredient) {

//
//

        _state.update { state ->
            var newToppingPrice = state.toppingPrice
            val updateSelectedPizza = state.selectedPizza.copy(
                ingredients = state.selectedPizza.ingredients.map { item ->
                    if (item.id == ingredient.id) {
                        val newSelected = !item.selected
                        newToppingPrice = if (newSelected) {
                            newToppingPrice + ingredient.price
                            } else {
                            newToppingPrice - ingredient.price
                            }
                        item.copy(selected = newSelected)
                    } else item
                }
            )

            val updateIndex = state.pizzaList.indexOfFirst { pizza ->
                pizza.id == updateSelectedPizza.id
            }

            val list = state.pizzaList.toMutableList()
            list[updateIndex] = updateSelectedPizza

            state.copy(
                pizzaList = list,
                toppingPrice = newToppingPrice,
                selectedPizza = updateSelectedPizza
            )


//            val updatedPizzaList = state.pizzaList.map { pizza ->
//                if (pizza.id == state.selectedPizza.id) {
//                    pizza.copy(
//                        ingredients = pizza.ingredients.map { item ->
//                            if (item.id == ingredient.id) {
//                                val newSelected = !item.selected
//
//                                val newToppingPrice = if (newSelected) {
//                                    state.toppingPrice + ingredient.price
//                                } else {
//                                    state.toppingPrice - ingredient.price
//                                }
//
//                                item.copy(selected = newSelected)
//                            }  else item
//                        }
//                    )
//                } else pizza
//            }
//            state.copy(
//                pizzaList = updatedPizzaList,
//                selectedPizza = updatedPizzaList.first { it.id == state.selectedPizza.id }
//            )

        }
    }
}