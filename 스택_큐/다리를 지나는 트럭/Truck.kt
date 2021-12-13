import java.util.*
import kotlin.collections.ArrayList

fun main() {
    println("Test")
    val array = intArrayOf(10,10,10,10,10,10,10,10,10,10)
    solution(100, 100, array)
}

fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
    var answer = 0
    val readyTruckQueue = LinkedList<Truck>().apply { //대기 트럭
        truck_weights.forEach {
            offer(Truck(
                it, bridge_length
            ))
        }
    }
    val runningTruckQueue = LinkedList<Truck>() //다리를 건너는 트럭
    val completeTruckLit = ArrayList<Truck>()
    var totalBridgeWeight = 0
    while (completeTruckLit.size != truck_weights.size) {
        //트럭이 새로 진입
        //트럭이 움직임
        //트럭이 다리를 다 건넘
        if (readyTruckQueue.isNotEmpty()) {
            if (totalBridgeWeight + readyTruckQueue.peek().weight <= weight) {
                runningTruckQueue.offer(readyTruckQueue.poll().apply {
                    totalBridgeWeight += this.weight
                })
            }
        }
        runningTruckQueue.forEach {
            it.length--
        }
        answer++
        if (runningTruckQueue.isNotEmpty()) {
            if (runningTruckQueue.peek().length == 0) {
                completeTruckLit.add(runningTruckQueue.poll().apply {
                    totalBridgeWeight -= this.weight
                })
            }
        }
    }
    if (answer != 0) answer++
//    println("answer = $answer")
    return answer
}

data class Truck(
    var weight: Int,
    var length: Int
)