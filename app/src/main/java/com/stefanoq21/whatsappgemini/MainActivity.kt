package com.stefanoq21.whatsappgemini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.stefanoq21.whatsappgemini.presentation.component.navigation.MainNavHost
import com.stefanoq21.whatsappgemini.presentation.navigation.NavigationEvent
import com.stefanoq21.whatsappgemini.presentation.navigation.NavigationViewModel
import com.stefanoq21.whatsappgemini.presentation.theme.WhatsAppGeminiTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            WhatsAppGeminiTheme {

                val navigationViewModel = koinViewModel<NavigationViewModel>()

                val navController = rememberNavController()
                val backstackEntry = navController.currentBackStackEntryAsState()

                navigationViewModel.onEvent(
                    NavigationEvent.OnSetContent(
                        activityNavController = navController,
                        //backstackEntry.value?.toRoute<Screen?>() ?: Screen.Home
                    ) { onBackPressedDispatcher.onBackPressed() })

                ChangeSystemBarsTheme(
                    lightTheme = !isSystemInDarkTheme(),
                )

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavHost(
                        modifier = Modifier.padding(/*innerPadding*/),
                    )
                    /*Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = navigationViewModel.snackBarHostState)
                    }
                ) { innerPadding ->

                }*/
                }
            }

        }
    }


    @Composable
    private fun ChangeSystemBarsTheme(
        lightTheme: Boolean,
    ) {
        val statusBarColor = MaterialTheme.colorScheme.background.toArgb()
        val navigationBarColor = MaterialTheme.colorScheme.background.toArgb()


        LaunchedEffect(lightTheme) {
            if (lightTheme) {

                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.light(
                        statusBarColor, statusBarColor,
                    ),
                    navigationBarStyle = SystemBarStyle.light(
                        navigationBarColor, navigationBarColor,
                    ),
                )
            } else {

                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        statusBarColor,
                    ),
                    navigationBarStyle = SystemBarStyle.dark(
                        navigationBarColor,
                    ),
                )
            }
        }
    }

}

