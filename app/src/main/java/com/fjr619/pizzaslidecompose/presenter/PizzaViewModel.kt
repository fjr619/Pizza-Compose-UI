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
                    pizzaList = DataSource.pizzaList
                )
            }
        }
    }

    fun onFavoriteClicked() {
        _state.update { it.copy(isFavorite = !state.value.isFavorite) }
    }

    fun onPizzaSizeClicked(size: PizzaSize) {
        _state.update { it.copy(selectedSize = size) }
    }

    fun setSelectedPizza(page: Int) {
        _state.update { it.copy(selectedPizza = state.value.pizzaList[page]) }
    }

    fun onIngredientClicked(ingredient: Ingredient) {
        val updateSelectedPizza = state.value.selectedPizza.copy(
            ingredients = state.value.selectedPizza.ingredients.map { item ->
                if (item.id == ingredient.id) item.copy(selected = !item.selected) else item
            }
        )


        _state.update { state ->
            val updateIndex = state.pizzaList.indexOfFirst { pizza ->
                pizza.id == updateSelectedPizza.id
            }

            state.pizzaList.toMutableList()[updateIndex] = updateSelectedPizza

            state.copy(
                pizzaList = state.pizzaList,
                selectedPizza = updateSelectedPizza
            )
//            val updatedPizzaList = state.pizzaList.map { pizza ->
//                if (pizza.id == state.selectedPizza.id) {
//                    pizza.copy(
//                        ingredients = pizza.ingredients.map { item ->
//                            if (item.id == ingredient.id) item.copy(selected = !item.selected) else item
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