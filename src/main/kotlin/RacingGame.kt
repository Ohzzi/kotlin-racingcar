import domain.Cars
import domain.NumberGenerator
import view.*

class RacingGame(
    private val numberGenerator: NumberGenerator
) {
    fun play() {
        val cars = createCars()
        val totalRound = decideTotalRound()
        var currentRound = 0
        while (currentRound++ < totalRound) {
            cars.move(numberGenerator)
            printCarsPosition(cars.items)
        }
        printWinners(cars.winners)
    }

    private fun createCars(): Cars {
        return try {
            Cars.fromNames(inputNames())
        } catch (e: IllegalArgumentException) {
            printErrorMessage(e.message ?: "오류가 발생했습니다.")
            createCars()
        }
    }

    private fun decideTotalRound(): Int {
        return try {
            val input = inputTotalRound()
            require(isNumeric(input)) { "라운드 수는 숫자만 입력해야 합니다." }
            input.toInt()
        } catch (e: IllegalArgumentException) {
            printErrorMessage(e.message ?: "오류가 발생했습니다.")
            decideTotalRound()
        }
    }

    private fun isNumeric(str: String): Boolean {
        return str.chars()
            .allMatch { Character.isDigit(it) }
    }
}
