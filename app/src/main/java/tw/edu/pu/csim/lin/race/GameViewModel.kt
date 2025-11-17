package tw.edu.pu.csim.lin.race

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {
    var screenWidthPx by mutableStateOf(0f)
        private set

    var screenHeightPx by mutableStateOf(0f)
        private set

    var gameRunning by mutableStateOf(true)

    var circleX by mutableStateOf(0f)

    var circleY by mutableStateOf(0f)

    var score by mutableStateOf(0)
        private set

    //val horse = Horse()
    val horses = mutableListOf<Horse>()

    // 新增：勝利馬匹 & 終點線
    var winner by mutableStateOf(0)
    var finishLineX by mutableStateOf(0f)


    // 設定螢幕寬度與高度
    fun SetGameSize(w: Float, h: Float) {
        screenWidthPx = w
        screenHeightPx = h
        finishLineX = w - 300f

        for(i in 0..2){
            horses.add(Horse(n = i))
        }
    }

    fun StartGame() {
        //回到初使位置
        circleX = 100f
        circleY = screenHeightPx - 100f
        score = 0
        winner = 0

        horses.forEach { it.Reset() }
        gameRunning = true

        viewModelScope.launch {
            while (true) { // 每0.1秒循環
                delay(100)
                circleX += 10

                if (circleX >= screenWidthPx - 100) {
                    circleX = 100f
                    score += 1      //加一分
                }
                if (gameRunning) {
                    circleX += 10
                    if (circleX >= screenWidthPx - 100) {
                        circleX = 100f
                        score += 1
                    }

                    for(i in 0..2){
                        horses[i].Run()
                        if (horses[i].HorseX >= finishLineX) {
                            //horses[i].HorseX = 0
                            winner = i + 1
                            gameRunning = false
                            launch {
                                delay(1500)
                                horses.forEach { it.Reset() }  // 馬回到起點
                                gameRunning = true             // 下一輪自動開始
                                delay(1500)                     // 勝利文字顯示時間
                                winner = 0
                            }
                        }
                    }
                }
            }
        }
    }

    private fun resetRace() {
        horses.forEach { it.Reset() }
        winner = 0
        //gameRunning = true
    }

    fun MoveCircle(x: Float, y: Float) {
        circleX += x
        circleY += y
    }


}