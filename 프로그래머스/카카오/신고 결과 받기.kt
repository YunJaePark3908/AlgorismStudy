package programers

import com.sun.xml.internal.fastinfoset.util.StringArray
import kotlin.math.pow

private fun main() {

    Test1().solution(arrayOf("con", "ryan"), arrayOf("ryan con", "ryan con", "ryan con", "ryan con"), 3)
}

class Test1 {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val answer = IntArray(id_list.size)
        //value = 유저가 신고한 ID
        val hashMap = hashMapOf<String, ArrayList<String>>()
        val receivedHashMap = hashMapOf<String, Int>()
        //초기화
        id_list.forEach {
            hashMap[it] = arrayListOf()
            receivedHashMap[it] = 0
        }
        report.distinct().forEach {
            val split = it.split(" ")
            hashMap[split[0]]?.add(split[1])
            val count = receivedHashMap[split[1]]?: 0
            receivedHashMap[split[1]] = count + 1
        }
        //정지 된 Id List
        val stopUserList = arrayListOf<String>()
        receivedHashMap.filter { it.value >= k }.keys.forEach {
            stopUserList.add(it)
        }
        hashMap.forEach { key, value ->
            var count = 0
            stopUserList.forEach {
                if (value.contains(it)) { count++ }
            }
            answer[id_list.indexOf(key)] = count
        }

        return answer
    }
}