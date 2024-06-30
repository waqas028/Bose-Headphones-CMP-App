package navigation

sealed class Screen(val route: String){

    data object HomeScreen : Screen("HomeScreen")
    data object DetailsScreen : Screen("DetailsScreen")
}