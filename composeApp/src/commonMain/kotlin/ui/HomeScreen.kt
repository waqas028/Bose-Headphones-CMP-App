package ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import boseheadphonesapp.composeapp.generated.resources.Res
import boseheadphonesapp.composeapp.generated.resources.boss_noise_cancelling
import boseheadphonesapp.composeapp.generated.resources.boss_on_ear
import boseheadphonesapp.composeapp.generated.resources.boss_on_ear_price
import boseheadphonesapp.composeapp.generated.resources.coming_soon
import boseheadphonesapp.composeapp.generated.resources.ic_back
import boseheadphonesapp.composeapp.generated.resources.ic_logo
import boseheadphonesapp.composeapp.generated.resources.ic_product_1
import boseheadphonesapp.composeapp.generated.resources.ic_product_2
import boseheadphonesapp.composeapp.generated.resources.ic_product_3
import boseheadphonesapp.composeapp.generated.resources.ic_product_4
import boseheadphonesapp.composeapp.generated.resources.ic_search
import boseheadphonesapp.composeapp.generated.resources.poppins_medium
import boseheadphonesapp.composeapp.generated.resources.poppins_semiBold
import boseheadphonesapp.composeapp.generated.resources.quick_comfort_35_wireless
import boseheadphonesapp.composeapp.generated.resources.quick_comfort_price
import boseheadphonesapp.composeapp.generated.resources.sound_link_around
import boseheadphonesapp.composeapp.generated.resources.sound_link_around_price
import boseheadphonesapp.composeapp.generated.resources.wireless_headphones
import fillColor
import kotlinx.coroutines.delay
import navigation.Screen
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import silver
import ui.model.HeadPhone

@Composable
fun HomeScreen(navController: NavController) {
    HomeScreen(
        onItemClick = { navController.navigate(Screen.DetailsScreen.route + "/$it") },
        onBackPress = { navController.popBackStack() }
    )
}

@Composable
private fun HomeScreen(onItemClick: (Int) -> Unit, onBackPress: () -> Unit) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isVisible = true
    }
    val slideInAnimation by animateFloatAsState(
        targetValue = if (isVisible) 0f else 800f,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = fillColor)
            .padding(horizontal = 15.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 34.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_back),
                contentDescription = "",
                modifier = Modifier.size(25.dp).clickable { onBackPress() }
            )
            Image(
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = "",
                modifier = Modifier.padding(end = 10.dp).size(25.dp)
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth().padding(top = 58.5.dp)
        ) {
            Text(
                text = stringResource(Res.string.wireless_headphones),
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(Res.font.poppins_semiBold)),
                color = Color.Black,
                fontSize = 35.5.sp,
                lineHeight = 40.sp,
                modifier = Modifier.padding(start = 10.dp, end = 100.dp)
                    .align(Alignment.BottomStart)
                    .graphicsLayer { translationX = -slideInAnimation }
            )
            Image(
                painter = painterResource(Res.drawable.ic_logo),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .height(150.dp)
                    .align(Alignment.TopEnd)
                    .graphicsLayer { translationX = slideInAnimation }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        val listOfHeadPhone = listOf(
            HeadPhone(
                name = stringResource(Res.string.quick_comfort_35_wireless),
                image = painterResource(Res.drawable.ic_product_1),
                stringResource(Res.string.quick_comfort_price)
            ),
            HeadPhone(
                name = stringResource(Res.string.sound_link_around),
                image = painterResource(Res.drawable.ic_product_2),
                stringResource(Res.string.sound_link_around_price)
            ),
            HeadPhone(
                name = stringResource(Res.string.boss_on_ear),
                image = painterResource(Res.drawable.ic_product_3),
                stringResource(Res.string.boss_on_ear_price)
            ),
            HeadPhone(
                name = stringResource(Res.string.boss_noise_cancelling),
                image = painterResource(Res.drawable.ic_product_4),
                stringResource(Res.string.coming_soon)
            )
        )
        LazyVerticalGrid(
            modifier = Modifier.heightIn(max = 1000.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(listOfHeadPhone) { index, item ->
                var isCardVisible by remember { mutableStateOf(false) }
                LaunchedEffect(item) {
                    if (index % 2 == 0) delay(200L) else delay(400L)
                    isCardVisible = true
                }
                val slideInAnimationCard by animateFloatAsState(
                    targetValue = if (isCardVisible) 0f else 1000f,
                    animationSpec = tween(durationMillis = 1000),
                    label = ""
                )
                HeadPhoneCard(
                    modifier = Modifier.clickable { onItemClick(index) }.graphicsLayer {
                        translationX = if (index % 2 == 0) -slideInAnimationCard else slideInAnimationCard
                    },
                    headPhone = item
                )
            }
        }
    }
}

@Composable
private fun HeadPhoneCard(modifier: Modifier = Modifier, headPhone: HeadPhone) {
    Column(modifier = modifier.background(color = Color.White, shape = RoundedCornerShape(34.dp))) {
        Image(
            painter = headPhone.image,
            contentDescription = "",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 20.dp)
            //.aspectRatio(2f/1.5f)
            //.padding(horizontal = 40.dp, vertical = 25.dp)
        )
        Text(
            text = headPhone.name,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(Res.font.poppins_semiBold)),
            color = Color.Black,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            modifier = Modifier.padding(start = 20.dp, end = 10.dp)
        )
        Text(
            text = headPhone.price,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily(Font(Res.font.poppins_medium)),
            color = silver,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
        )
    }
}