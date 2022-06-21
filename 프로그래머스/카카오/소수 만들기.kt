package programers

import kotlin.math.pow

private fun main() {

    Test1().solution(intArrayOf(1,2,7,6,4))
}

class Test1 {
    fun solution(nums: IntArray): Int {
        var answer = 0
        for (i in 0 until nums.size - 2) {
            for (j in i+1 until nums.size - 1) {
                for (k in j+1 until nums.size) {
                    val n = nums[i] + nums[j] + nums[k]
                    if (isPrime(n)) answer++
                }
            }
        }
        return answer
    }
    private fun isPrime(n: Int): Boolean {
        if (n == 0 || n == 1) return false
        var i = 2
        while (i * i <= n) { if (n % i++ == 0) return false }
        return true
    }
}