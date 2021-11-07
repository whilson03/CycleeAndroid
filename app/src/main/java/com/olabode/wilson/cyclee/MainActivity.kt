package com.olabode.wilson.cyclee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.olabode.wilson.cyclee.ui.navigation.AppNavigation
import com.olabode.wilson.cyclee.ui.theme.CycleeTheme
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : ComponentActivity() {

    @InternalCoroutinesApi
    @ExperimentalComposeUiApi
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
