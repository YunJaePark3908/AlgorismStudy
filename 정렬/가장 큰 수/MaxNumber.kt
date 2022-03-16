
private fun main() {
    val array = intArrayOf(6, 10, 2)
    solution(array)
}

private fun solution(numbers: IntArray): String {
    val temp = arrayListOf<String>()
    numbers.forEach {
        temp.add(it.toString())
    }
    temp.sortWith(Comparator { o1, o2 ->
        when {
            o1.length == o2.length -> o2.compareTo(o1)
            else -> (o2 + o1).compareTo(o1 + o2)
        }
    })
    val result = StringBuffer()
    temp.forEachIndexed { index, s ->
        if (index == 0 && s == "0") {
            return "0"
        }
        result.append(s)
    }

    return result.toString()
}
