package com.acmpo6ou.shop.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.acmpo6ou.shop.R
import com.acmpo6ou.shop.ui.screen.CartScreen
import com.acmpo6ou.shop.ui.screen.ProductListScreen
import com.acmpo6ou.shop.ui.screen.ProductsViewModel

enum class Screen(
    val route: String,
    @StringRes val labelId: Int,
    @DrawableRes val iconId: Int,
) {
    PRODUCT_LIST("product_list", R.string.product_list_label, R.drawable.search),
    CART("cart", R.string.cart_label, R.drawable.shopping_cart),
}

@Composable
fun MainContent(viewModel: ProductsViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController, viewModel)
        },
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.PRODUCT_LIST.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.PRODUCT_LIST.route) {
                ProductListScreen(viewModel)
            }
            composable(Screen.CART.route) {
                CartScreen(viewModel)
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    viewModel: ProductsViewModel,
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        Screen.values().forEach { screen ->
            BottomNavigationItem(
                icon = {
                    val icon = @Composable {
                        Icon(
                            painterResource(screen.iconId),
                            contentDescription = null,
                        )
                    }
                    if (screen == Screen.CART) {
                        BadgedBox(badge = {
                            Badge {
                                val cartIds = remember { viewModel.cartIds }
                                Text(cartIds.size.toString())
                            }
                        }) {
                            icon()
                        }
                    } else {
                        icon()
                    }
                },
                label = { Text(stringResource(screen.labelId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    viewModel.navigate(screen)
                },
            )
        }
    }
}
