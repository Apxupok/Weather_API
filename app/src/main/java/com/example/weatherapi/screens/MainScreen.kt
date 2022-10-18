package com.example.weatherapi.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapi.R
import com.example.weatherapi.ui.theme.BlueLight

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    AsyncImage(
        model = "https://i.pinimg.com/736x/3a/d0/cb/3ad0cb9d982ead6241451a47e3553f41.jpg",
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = BlueLight,
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                        text = "20 june 2022 13:00",
                        style = TextStyle(fontSize = 15.sp),//Размер текста
                        color = Color.White
                    )
                    AsyncImage(
                        model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )

                }

            }

        }

    }
}

