package baekjoon

import java.util.*

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    println(factorial(n))
}

tailrec fun factorial(n: Int, acc: Int = 1): Int =
    if (n <= 0) acc else factorial(n-1, n*acc)