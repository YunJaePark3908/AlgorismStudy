import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.MutableList

fun main() {
    val progresses = intArrayOf(99,99,99,99,99)
    val speeds = intArrayOf(3, 3, 3, 3, 3)
    solution(progresses, speeds)
}

private fun solution(progresses: IntArray, speeds: IntArray): IntArray {
    val answer = mutableListOf<Int>()
    var count = 0 //배포 개수
    var workingDay = 0 //맨 앞의 우선순위를 100%가 넘어 갈 때 까지의 워킹 데이
    val progressesQueue = LinkedList<Int>().apply {
        progresses.forEach { offer(it) }
    }
    val speedsQueue = LinkedList<Int>().apply {
        speeds.forEach { offer(it) }
    }
    while (progressesQueue.isNotEmpty() && speedsQueue.isNotEmpty()) {
        //우선순위의 prgress를 처리할 때 까지돌림
        while (progressesQueue.peek() + (workingDay * speedsQueue.peek()) < 100) {
            workingDay++
        }
        progressesQueue.poll()
        speedsQueue.poll()
        count++
        if (progressesQueue.isNotEmpty()) {
            while (progressesQueue.peek() + (workingDay * speedsQueue.peek()) >= 100) {
                progressesQueue.poll()
                speedsQueue.poll()
                count++
                if (progressesQueue.isEmpty()) {
                    break
                }
            }
        }
        answer.add(count)
        count = 0
    }
    answer.forEach {
        print("$it ")
    }
    return answer.toIntArray()
}