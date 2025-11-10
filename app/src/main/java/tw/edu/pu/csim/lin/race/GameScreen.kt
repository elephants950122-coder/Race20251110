package tw.edu.pu.csim.lin.race

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(message: String, gameViewModel: GameViewModel) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)
    ){
        Text(
            text = "分數：${gameViewModel.score}",
            fontSize = 28.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )

        Canvas (modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume() // 告訴系統已經處理了這個事件
                    gameViewModel.MoveCircle( dragAmount.x, dragAmount.y)
                }
            }
        ) {
            // 繪製圓形
            drawCircle(
                color = Color.Red,
                radius = 100f,
                center = Offset(gameViewModel.circleX, gameViewModel.circleY)
            )
        }

        Text(text = message + gameViewModel.screenWidthPx.toString() + "*"
                + gameViewModel.screenHeightPx.toString())

        Button(onClick = {gameViewModel.gameRunning = true
            gameViewModel.StartGame()
        }, modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 32.dp)
        ){
            Text(text = "遊戲開始")
        }
    }
}