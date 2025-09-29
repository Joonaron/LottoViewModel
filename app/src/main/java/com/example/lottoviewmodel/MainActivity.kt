package com.example.lottoviewmodel



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.lottoviewmodel.ui.Screen
import com.example.lottoviewmodel.ui.theme.LottoViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            LottoViewModelTheme {
                Screen()
            }
        }
    }
}