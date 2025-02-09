package com.soop.githubapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.soop.designsystem.theme.GithubappTheme
import com.soop.githubapp.ui.SoopApp
import com.soop.githubapp.ui.rememberSoopAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberSoopAppState()
            GithubappTheme {
                SoopApp(appState)
            }
        }
    }
}