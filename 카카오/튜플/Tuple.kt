private fun main() {
    Tuple().solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")
}
class Tuple {
    fun solution(s: String): IntArray {
        val answer = arrayListOf<Int>()
        val split = s.split("{", "}")
        val tupleList = arrayListOf<List<String>>()
        split.forEach { str ->
            if (str != "" && str != ",") {
                tupleList.add(str.split(","))
            }
        }
        tupleList.forEachIndexed { index, strings ->
            tupleList.find { it.size == index + 1 }?.forEach {
                if (it.toIntOrNull() != null) {
                    if (!answer.contains(it.toInt())) {
                        answer.add(it.toInt())
                    }
                }
            }
        }
        return answer.toIntArray()
    }
}