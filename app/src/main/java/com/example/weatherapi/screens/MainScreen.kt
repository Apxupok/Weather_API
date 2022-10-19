package com.example.weatherapi.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapi.R
import com.example.weatherapi.data.WeatherModel
import com.example.weatherapi.ui.theme.BlueLight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun MainCard(currentDay: MutableState<WeatherModel>) {

    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = BlueLight,
            elevation = 0.dp
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
                        modifier = Modifier.padding(
                            top = 8.dp,
                            start = 8.dp
                        ),
                        text = currentDay.value.time,
                        style = TextStyle(fontSize = 15.sp),//Размер текста
                        color = Color.White
                    )
                    AsyncImage(
                        model = "https:" + currentDay.value.icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(35.dp)
                            .padding(
                                top = 8.dp,
                                end = 8.dp
                            )
                            .size(35.dp)
                    )

                }
                Text(
                    text = currentDay.value.city,
                    style = TextStyle(fontSize = 24.sp),//Размер текста
                    color = Color.White
                )
                Text(
                    text = currentDay.value.currentTemp.toFloat().toInt().toString() + "°C",
                    style = TextStyle(fontSize = 65.sp),//Размер текста
                    color = Color.White
                )
                Text(
                    text = currentDay.value.condition,
                    style = TextStyle(fontSize = 16.sp),//Размер текста
                    color = Color.White
                )
                Text(
                    text = "${ currentDay.value.maxTemp }°C/${ currentDay.value.minTemp }°C",
                    style = TextStyle(fontSize = 16.sp),//Размер текста
                    color = Color.White
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    //максимальный отступ между элемемнтами
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_search_24),
                            contentDescription = null,
                            tint = Color.White //цвет иконки
                        )
                    }

                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.sync),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                }
            }

        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TableLayout(daysList: MutableState<List<WeatherModel>>) {
    val tabList = listOf("HOURS", "DAYS")
    //сохраняем 3 разных состояния
    val pagerState = rememberPagerState()
    //берем текущее состояние(страницы)
    val tabIndex = pagerState.currentPage
    //для анимации
    val couroitineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(
                start = 5.dp,
                end = 5.dp
            )
            .clip(RoundedCornerShape(5.dp))
    ) {
        TabRow(
            // рисуем для текущего состояния страницы
            selectedTabIndex = tabIndex,
            //рисуем для каждой позиции свой индикатор
            indicator = { pos ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, pos)
                )
            },
            backgroundColor = BlueLight,
            contentColor = Color.White
        ) {
            tabList.forEachIndexed { index, text ->
                Tab(
                    selected = false,
                    onClick = {
                        //запускаем корутинскоуп
                        couroitineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }

                    },
                    text = {
                        Text(text = text)
                    }
                )
            }
        }

        HorizontalPager(
            //горизонтальный переключатель страниц
            count = tabList.size,//сколько элементов
            state = pagerState,
            modifier = Modifier.weight(1.0f)
        ) { index ->
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(
                    daysList.value
                ) { _, item ->
                    ListItem(item)
                }

            }
        }


    }
}
