package com.example.moodyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moodyapp.navigation.NavGraph
import com.example.moodyapp.ui.theme.MoodyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoodyAppTheme {
                Scaffold { innerPadding ->
                    MoodyApp(modifier = Modifier.padding(innerPadding).fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun MoodyApp(modifier: Modifier = Modifier) {
    NavGraph()
}

@Preview(showBackground = true)
@Composable
fun MoodyAppPreview() {
    MoodyAppTheme {
        MoodyApp()
    }
}


