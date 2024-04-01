package com.fjr619.pizzaslidecompose.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ToppingItem(
    modifier: Modifier = Modifier,
    imageRes: Int,
) {
    Image(
        painterResource(imageRes), contentDescription = null,
        modifier = modifier.size(30.dp)
    )
}