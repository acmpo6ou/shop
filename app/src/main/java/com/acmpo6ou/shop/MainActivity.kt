package com.acmpo6ou.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.acmpo6ou.shop.model.ProductRepo
import com.acmpo6ou.shop.model.ProductRepo.Companion.CART_IDS
import com.acmpo6ou.shop.ui.screen.cart.CartScreen
import com.acmpo6ou.shop.ui.screen.productlist.ProductListScreen
import com.acmpo6ou.shop.ui.screen.productlist.ProductsViewModel
import com.acmpo6ou.shop.ui.theme.ShopTheme

class MainActivity : ComponentActivity() {
    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = ProductRepo(
            assets,
            getSharedPreferences(CART_IDS, MODE_PRIVATE),
        )
        productsViewModel.initialize(repo)

        setContent {
            ShopTheme {
                MainContent(productsViewModel)
            }
        }
    }
}

enum class Screen(
    val route: String,
    @StringRes val labelId: Int,
    @DrawableRes val iconId: Int,
) {
    PRODUCT_LIST(
        "product_list",
        R.string.product_list_label,
        R.drawable.search,
    ),
    CART(
        "cart",
        R.string.cart_label,
        R.drawable.shopping_cart,
    ),
}

@Composable
fun MainContent(productsViewModel: ProductsViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController) },
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.PRODUCT_LIST.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.PRODUCT_LIST.route) {
                ProductListScreen(productsViewModel)
            }
            composable(Screen.CART.route) {
                CartScreen()
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        Screen.values().forEach { screen ->
            val label = stringResource(screen.labelId)
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(screen.iconId),
                        contentDescription = label,
                    )
                },
                label = { Text(label) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}
