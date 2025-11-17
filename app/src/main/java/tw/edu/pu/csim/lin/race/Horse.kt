package tw.edu.pu.csim.lin.race

class Horse(){
    var HorseX = 0
    var HorseY = 100

    var HorseNo = 0

    fun Run(){
        HorseNo ++
        if (HorseNo > 3){
            HorseNo = 0
        }

        HorseX += (10..30).random()
    }

}