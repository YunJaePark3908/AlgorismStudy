private fun main() {
    println(solution(intArrayOf(10, 9, 4, 1, 1)))

}

private fun solution(citations: IntArray): Int {
    if (citations.maxOf { it } == 0) {
        return 0
    }
    var h = 0
    for (n in 1..citations.size) {
        val citationsUseArray = citations.filter { it >= n }
        if (citationsUseArray.size >= n) {
            h++
        }
    }
    return h
}