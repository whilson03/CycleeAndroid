package com.olabode.wilson.cyclee.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.olabode.wilson.cyclee.ui.BottomNavigationItem

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 04/02/2022
 * EMAIL: whilson03@gmail.com
 */
@Composable
fun CycleeBottomNavigationBar(
    modifier: Modifier = Modifier,
    items: Array<BottomNavigationItem>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit,
) {
    BottomNavigation(modifier = modifier) {
        items.forEach { item ->
            BottomNavigationItem(
                selected = item.route == currentRoute,
                onClick = { navigateToRoute(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(id = item.title)
                    )
                }
            )
        }
    }
}
