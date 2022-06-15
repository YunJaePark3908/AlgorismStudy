import kotlin.collections.ArrayList

private fun main() {
    val case1 = arrayOf("java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50")
    val case1Query = arrayOf("java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150")
    RankSearch().solution(case1, case1Query)
}

class RankSearch {
    val map = hashMapOf<String, ArrayList<Int>>()
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val answer = IntArray(query.size)
        info.forEach { infoStr ->
            dfs(0, "", infoStr.split(" "))
        }
        val iterator = map.keys.iterator()
        while (iterator.hasNext()) {
            val key = iterator.next()
            val list = map[key] ?: ArrayList()
            list.sort()
        }
        query.forEachIndexed { index, queryStr ->
            val keyValue = queryStr.replace(" and ".toRegex(), "").split(" ").toTypedArray()
            val key = keyValue[0]
            val value = keyValue[1]

            if (!map.containsKey(key)) {
                answer[index] = 0
            } else {
                answer[index] = bs(map[key]!!, value.toInt())
            }
        }
        return answer
    }
    private fun dfs(depth: Int, key: String, info: List<String>) {
        if (depth >= 4) {
            if (map.containsKey(key)) {
                map[key]!!.add(info[4].toInt())
            } else {
                val list = ArrayList<Int>()
                list.add(info[4].toInt())
                map[key] = list
            }
            return
        }

        dfs(depth + 1, "${key}-", info)
        dfs(depth + 1, key + info[depth], info)
    }

    fun bs(list: List<Int>, target: Int): Int {
        var start = 0
        var end = list.size - 1

        while (start <= end) {
            val mid = (start + end) / 2
            if (list[mid] >= target) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }

        return list.size - start
    }
}
