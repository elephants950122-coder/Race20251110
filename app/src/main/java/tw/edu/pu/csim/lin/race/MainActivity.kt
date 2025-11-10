package tw.edu.pu.csim.lin.race

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.layout.WindowMetricsCalculator
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

        // 確保內容延伸到至邊緣
        WindowCompat.setDecorFitsSystemWindows(
            window, false)

        // 步驟 1: 獲取 WindowMetricsCalculator 實例
        val windowMetricsCalculator =
            WindowMetricsCalculator.getOrCreate()

        // 步驟 2: 計算當前視窗的 WindowMetrics
        val currentWindowMetrics=
            windowMetricsCalculator.computeCurrentWindowMetrics(this)

        // 步驟 3: 從 bounds 獲取像素尺寸
        val bounds = currentWindowMetrics.bounds

        val screenWidthPx = bounds.width().toFloat()
        val screenHeightPx = bounds.height().toFloat()

        // 實例化 ViewModel
        val gameViewModel: GameViewModel by viewModels()
        gameViewModel.SetGameSize(screenWidthPx,screenHeightPx)

        setContent {
            RaceTheme {
                GameScreen(message = "資管二B 411303114 林建宇\n橫式螢幕，隱藏狀態列.",
                    gameViewModel)
            }
        }
    }
}
