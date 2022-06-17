import java.util.*
import kotlin.collections.ArrayList

fun main() {
    println("Test")
    val array = intArrayOf(10,10,10,10,10,10,10,10,10,10)
    solution(100, 100, array)
}

fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
    var answer = 0
    val readyTruckQueue = LinkedList<Truck>().apply { //다리 건너는것을 대기하는 트럭
        truck_weights.forEach {
            offer(Truck(
                it, bridge_length
            ))
        }
    }
    val runningTruckQueue = LinkedList<Truck>() //다리를 건너는 트럭
    val completeTruckLit = ArrayList<Truck>() //다리를 다 건넌 트럭
    var totalBridgeWeight = 0 //현재 올라와 있는 다리 무게
    while (completeTruckLit.size != truck_weights.size) {
        //1초 후 이벤트 상황들
        //다리 무게가 견딜 수 있다면 새로운 트럭이 진입
        //다리 위에 있던 트럭이 한칸 움직임
        //다리 위에 있던 트럭이 다 건넘
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