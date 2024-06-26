package com.fjr619.pizzaslidecompose.presenter.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fjr619.pizzaslidecompose.domain.Pizza
import com.fjr619.pizzaslidecompose.presenter.PizzaSize

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pizzaList: List<Pizza>,
    pizzaSize: PizzaSize,
) {
    val scale = animateFloatAsState(
        targetValue = when (pizzaSize) {
            PizzaSize.SMALL -> 0.7f
            PizzaSize.MEDIUM -> 0.8f
            PizzaSize.LARGE -> 0.9f
        }, label = "pizzaScale"
    )

    HorizontalPager(
        state = pagerState,
        key = {
            pizzaList[it].id
        },
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically

    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .scale(scale.value),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(300.dp),
                    painter = painterResource(id = pizzaList[page].breadRes),
                    contentDescription = null,
                )
                pizzaList[page].ingredients.filter {
                    it.selected
                }.forEach { ingredient ->
                    when (ingredient.id) {
                        1 -> PizzaToppings(images = ingredient.images)
                        2 -> PizzaToppings(images = ingredient.images)
                        3 -> PizzaToppings(images = ingredient.images)
                        4 -> PizzaToppings(images = ingredient.images)
                        5 -> PizzaToppings(images = ingredient.images)
                    }
                }
            }
        }

    }
}