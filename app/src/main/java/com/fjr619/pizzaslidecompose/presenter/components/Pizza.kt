package com.fjr619.pizzaslidecompose.presenter.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fjr619.pizzaslidecompose.R
import com.fjr619.pizzaslidecompose.domain.Pizza
import com.fjr619.pizzaslidecompose.presenter.PizzaSize

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pizza(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pizzaList: List<Pizza>,
    pizzaSize: PizzaSize,
) {
    Box(
        modifier = modifier.padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(300.dp),
            painter = painterResource(id = R.drawable.plate),
            contentDescription = null
        )

        PizzaPager(
            pagerState = pagerState,
            pizzaList = pizzaList,
            pizzaSize = pizzaSize
        )
    }
}