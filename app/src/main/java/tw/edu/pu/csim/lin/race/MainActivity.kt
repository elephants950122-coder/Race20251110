package tw.edu.pu.csim.lin.race

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import tw.edu.pu.csim.lin.race.ui.theme.RaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //強迫橫式螢幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        // 隱藏狀態列：獲取 WindowInsetsController，再隱藏statusBars
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())

        //隱藏下方巡覽列
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())

        setContent {
            RaceTheme {
                GameScreen(message = "橫式螢幕，隱藏狀態列")
            }
        }
    }
}
