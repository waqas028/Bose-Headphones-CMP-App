package ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TransformOrigin
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
import boseheadphonesapp.composeapp.generated.resources.ic_color_selection
import boseheadphonesapp.composeapp.generated.resources.ic_logo
import boseheadphonesapp.composeapp.generated.resources.ic_product_1
import boseheadphonesapp.composeapp.generated.resources.ic_product_2
import boseheadphonesapp.composeapp.generated.resources.ic_product_3
import boseheadphonesapp.composeapp.generated.resources.ic_product_4
import boseheadphonesapp.composeapp.generated.resources.limited_edition
import boseheadphonesapp.composeapp.generated.resources.poppins_bold
import boseheadphonesapp.composeapp.generated.resources.poppins_semiBold
import boseheadphonesapp.composeapp.generated.resources.quick_comfort_35_wireless
import boseheadphonesapp.composeapp.generated.resources.quick_comfort_price
import boseheadphonesapp.composeapp.generated.resources.sound_link_around
import boseheadphonesapp.composeapp.generated.resources.sound_link_around_price
import detailsScreenFillColor
import graniteGray
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.model.HeadPhone

@Composable
fun DetailsScreen(navController: NavController, index: Int) {
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
    DetailsScreen(
        headPhone = listOfHeadPhone[index],
        onBackPress = { navController.popBackStack() })
}

@Composable
fun DetailsScreen(modifier: Modifier = Modifier, headPhone: HeadPhone, onBackPress: () -> Unit) {
    var isVisible by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        isVisible = 1
        delay(500)
        isVisible = 2
        delay(700)
        isVisible = 3
    }
    val slideInAnimation by animateFloatAsState(
        targetValue = if (isVisible == 1) 1.3f else if (isVisible == 2) 0.9f else 1.1f,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    Column(
        modifier = modifier.fillMaxSize()
            .background(color = detailsScreenFillColor)
            .padding(horizontal = 15.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth().padding(top = 34.dp).weight(1f)) {
            Image(
                painter = painterResource(Res.drawable.ic_back),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(25.dp).clickable { onBackPress() }
            )
            Image(
                painter = painterResource(Res.drawable.ic_logo),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .padding(end = 20.dp)
                    .height(150.dp)
                    .align(Alignment.TopEnd)
            )
            Image(
                painter = headPhone.image,
                contentDescription = "",
                modifier = Modifier.padding(top = 0.dp)
                    .align(Alignment.Center)
                    .aspectRatio(3f / 3f)
                    .graphicsLayer {
                        scaleX = slideInAnimation
                        scaleY = slideInAnimation
                        transformOrigin = TransformOrigin.Center
                    }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.background(color = graniteGray)) {
                Text(
                    text = stringResource(Res.string.limited_edition).uppercase(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(Res.font.poppins_bold)),
                    color = Color.White,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        .align(Alignment.BottomStart)
                )
            }
            Image(
                painter = painterResource(Res.drawable.ic_color_selection),
                contentDescription = "",
                modifier = Modifier.width(70.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = headPhone.name,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(Res.font.poppins_semiBold)),
            color = Color.White,
            fontSize = 20.sp,
            lineHeight = 30.sp,
            modifier = Modifier.padding(end = 50.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.background(color = Color.White, shape = RoundedCornerShape(10.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.ShoppingCart,
                contentDescription = "",
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = headPhone.price,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(Res.font.poppins_bold)),
                color = Color.Black,
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 5.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}