package tw.edu.pu.csim.lin.race

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun GameScreen(message: String) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)
    ){
        Text(text = message)
    }
}