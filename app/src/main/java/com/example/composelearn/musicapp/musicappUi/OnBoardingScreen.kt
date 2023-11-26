package com.example.composelearn.musicapp.musicappUi

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelearn.musicapp.basemodels.OnBoardingItem
import com.example.composelearn.musicapp.utils.LocalDataUtils
import com.example.composelearn.theme.Orange
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(onNavigate: () -> Unit = {}) {
    val coroutineScope = rememberCoroutineScope()
    val list = LocalDataUtils.getOnBoardingData()
    val pagerState = rememberPagerState(initialPage = 0) {
        list.size
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            // Set the background color of the pager content to transparent
            modifier = Modifier.background(Color.Transparent), userScrollEnabled = false
        ) { page ->
            OnBoardingItem(items = list[page])
        }
        Box(
            modifier = Modifier.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    repeat(list.size) {
                        Indicator(isSelected = it == pagerState.currentPage)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                TextButton(
                    onClick = {
                        if (pagerState.currentPage + 1 < list.size)
                            coroutineScope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            } else onNavigate.invoke()
                    },
                    colors = ButtonDefaults.textButtonColors(backgroundColor = Orange),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}

@Composable
fun OnBoardingItem(items: OnBoardingItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Box {
            Image(
                painter = painterResource(id = items.image),
                contentDescription = "Image1",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = items.desc),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp),
                    letterSpacing = 1.sp,
                )
            }
        }

    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) Orange else Color(0xFFD8CBC7)
            )
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun onBoardingScreenPreview() {
    OnBoardingScreen()
}