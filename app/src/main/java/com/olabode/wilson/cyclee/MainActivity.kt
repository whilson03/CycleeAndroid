package com.olabode.wilson.cyclee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import com.olabode.wilson.cyclee.core.utils.snackbar.SnackbarManager
import com.olabode.wilson.cyclee.ui.CycleeApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var snackbarManager: SnackbarManager

    @InternalCoroutinesApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CycleeApp(snackbarManager)
        }
    }
}
