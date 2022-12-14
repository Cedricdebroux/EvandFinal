package com.technipixl.evand.evandfinal

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp


@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    selected: String,
    navigationChanged: (String) -> Unit
) {
    BottomNavigation(modifier) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            label = {
                Text("Search")
            },
            selected = selected == NavRoutes.SearchMovie.name,
            onClick = { navigationChanged(NavRoutes.SearchMovie.name) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.TrendingUp,
                    contentDescription = null
                )
            },
            label = {
                Text("Movies")
            },
            selected = selected == NavRoutes.PopularMovie.name,
            onClick = { navigationChanged(NavRoutes.PopularMovie.name) }
        )
    }
}