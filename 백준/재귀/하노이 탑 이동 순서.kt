package baekjoon

import java.util.*

fun main() = with(Scanner(System.`in`)) {
    val sb = StringBuilder()
    val n = nextInt()
    var cnt = 0

    fun hanoi(num: Int, start: Int, sub: Int, to: Int){
        if (num == 0) return
        cnt += 1
        hanoi (num - 1, start, to, sub) // N - 1 개 원반을 시작점 -> sub
        sb.append("$start $to\n") // n번 째 원반 -> 목표 기둥
        hanoi(num - 1, sub, start, to) // N-1 개 원반 sub -> to
    }
    hanoi(n, 1,2,3)
    println(cnt)
    print(sb)
}