package baekjoon

import java.util.*

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()

    println(fibonacci(n))
}

fun fibonacci(n: Int, first: Int = 0, second: Int = 1): Int =
    if (n <= 0) first
    else fibonacci(n - 1, second, first + second)