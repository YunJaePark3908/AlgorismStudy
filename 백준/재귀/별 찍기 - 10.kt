package baekjoon

import java.util.*

private val sb = StringBuffer()

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    for (i in 0 until n) {
        for (j in 0 until n) {
            recursion(i, j, n)
        }
        sb.append("\n")
    }
    println(sb.toString())
}

private fun recursion(i: Int, j: Int, n: Int) {
    if ((i/n)% 3 == 1 && (j/n)%3 == 1) sb.append(" ")
    else {
        if (n / 3 == 0) sb.append("*")
        else recursion(i, j, n / 3)
    }
}