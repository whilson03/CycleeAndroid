package com.olabode.wilson.cyclee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.olabode.wilson.cyclee.ui.screens.splash.SplashScreen
import com.olabode.wilson.cyclee.ui.theme.CycleeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CycleeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color =
                    MaterialTheme.colors.background
                ) {
                    SplashScreen() {
                    }
                }
            }
        }
    }
}
