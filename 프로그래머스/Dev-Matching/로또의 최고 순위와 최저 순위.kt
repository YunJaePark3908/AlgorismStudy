package programers

private fun main() {
    Test1().solution(intArrayOf(44, 1, 0, 0, 31, 25), intArrayOf(31, 10, 45, 1, 6, 19))
}
class Test1 {
    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        val answer = IntArray(2)
        //key = 일치 개수, value = 순위
        val hashMap = hashMapOf<Int, Int>()
        var rank = 6
        for (i in 0..6) {
            if (i == 0 || i == 1) hashMap[i] = rank
            else hashMap[i] = --rank
        }
        var winNumCount = 0
        lottos.filter { it != 0 }.forEach { if (win_nums.contains(it)) winNumCount++ }
        answer[1] = hashMap[winNumCount]?: 0
        winNumCount += lottos.filter { it == 0 }.size
        answer[0] = hashMap[winNumCount]?: 0
        return answer
    }
}