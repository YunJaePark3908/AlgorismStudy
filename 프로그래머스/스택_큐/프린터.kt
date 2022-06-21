import java.util.*
import kotlin.collections.HashMap

fun main() {
    println("Test")
    val array = intArrayOf(2, 1, 3, 2)
    solution(array, 2)
}

fun solution(priorities: IntArray, location: Int): Int {
    var answer = 0
    val queue = LinkedList<Printer>()
    priorities.forEachIndexed { index, i ->
        queue.offer(Printer(index, i))
    }

    while (!queue.isEmpty()) {
        var isBigNumbExist = false
        for (i in 1 until queue.size) {
            if (queue.peek().prior < queue[i].prior) {
                isBigNumbExist = true
                break
            }
        }

        if (isBigNumbExist) {
            queue.offer(queue.poll())
        }  else if (queue.poll().location == location) {
            answer = priorities.size - queue.size
        }
    }
    println("answer = $answer")
    return answer
}

data class Printer (
    var location: Int,
    var prior: Int
)