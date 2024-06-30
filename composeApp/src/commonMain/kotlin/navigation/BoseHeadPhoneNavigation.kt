package navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ui.DetailsScreen
import ui.HomeScreen

@Composable
fun BoseHeadPhoneNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(
            route = Screen.HomeScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            arguments = listOf(navArgument("index") { type = NavType.IntType }),
            route = Screen.DetailsScreen.route + "/{index}",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            val index = it.arguments?.getInt("index")
            DetailsScreen(navController = navController, index = index ?: 0)
        }
    }
}