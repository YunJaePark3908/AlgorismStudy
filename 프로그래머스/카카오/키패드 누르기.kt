package programers

import kotlin.math.abs

fun main() {
    Test().solution(intArrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5), "right")
}

class Test{
    fun solution(numbers: IntArray, hand: String): String {
        val answer = StringBuilder()
        var number = "1"
        var currentLeft = "*"
        var currentRight = "#"
        val keyPad = hashMapOf<String, IntArray>()
        for (i in 0..3) {
            for (j in 0..2) {
                if (i == 3) {
                    when (j) {
                        0 -> keyPad["*"] = intArrayOf(i, j)
                        1 -> keyPad["0"] = intArrayOf(i, j)
                        2 -> keyPad["#"] = intArrayOf(i, j)
                    }
                }
                else {
                    keyPad[number] = intArrayOf(i , j)
                    number = (number.toInt() + 1).toString()
                }
            }
        }
        numbers.forEach { num ->
            when (num) {
                1, 4, 7 -> {
                    answer.append("L")
                    currentLeft = num.toString()
                }
                3, 6, 9 -> {
                    answer.append("R")
                    currentRight = num.toString()
                }
                else -> {
                    val leftMoveCount = abs(keyPad[currentLeft]!![0] - keyPad[num.toString()]!![0]) + abs(keyPad[currentLeft]!![1] - keyPad[num.toString()]!![1])
                    val rightMoveCount = abs(keyPad[currentRight]!![0] - keyPad[num.toString()]!![0]) + abs(keyPad[currentRight]!![1] - keyPad[num.toString()]!![1])
                    if (leftMoveCount < rightMoveCount) {
                        answer.append("L")
                        currentLeft = num.toString()
                    } else if (leftMoveCount > rightMoveCount) {
                        answer.append("R")
                        currentRight = num.toString()
                    } else if (leftMoveCount == rightMoveCount) {
                        if (hand == "right") {
                            answer.append("R")
                            currentRight = num.toString()
                        } else {
                            answer.append("L")
                            currentLeft = num.toString()
                        }
                    }
                }
            }
        }
        return answer.toString()
    }
}