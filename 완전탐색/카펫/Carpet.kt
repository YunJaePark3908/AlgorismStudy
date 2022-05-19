fun main() {
    Carpet().solution(24,24).forEach {
        println(it)
    }
}

class Carpet {
    fun solution(brown: Int, yellow: Int): IntArray {
        val maxIdx = kotlin.math.max(brown, yellow)
        for (height in 3 until maxIdx) {
            for (with in height until maxIdx) {
                val isYellow = (with - 2) * (height - 2) == yellow
                val isBrown = (with * 2) + ((height - 2) * 2) == brown
                if (isYellow && isBrown && with >= height) {
                    return intArrayOf(with, height)
                }
            }
        }
        return intArrayOf()
    }
}