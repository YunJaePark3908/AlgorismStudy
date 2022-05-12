fun main() {
    Carpet().solution(12,3).forEach {
        println(it)
    }
}

class Carpet {
    fun solution(brown: Int, yellow: Int): IntArray {
        var answer = intArrayOf()
        if (yellow % 2 != 0) return intArrayOf(3, yellow + 2)
        return answer
    }
}