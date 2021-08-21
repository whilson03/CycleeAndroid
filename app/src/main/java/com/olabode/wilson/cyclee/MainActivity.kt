package com.olabode.wilson.cyclee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.olabode.wilson.cyclee.ui.navigation.AppNavigation
import com.olabode.wilson.cyclee.ui.theme.CycleeTheme

class MainActivity : ComponentActivity() {

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CycleeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppNavigation(navController = rememberNavController())
                }
            }
        }
    }
}
