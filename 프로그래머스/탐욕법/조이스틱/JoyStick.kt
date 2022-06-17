package solve

private fun main() {
//    println(JoyStick().solution("JAN"))
    val intArray = intArrayOf(1,2,3,4,5,6,7,8,9,10)
    val mapArray = intArray.map { "${it}번 인덱스" }
    mapArray.forEach {
        println(it)
    }
}

class JoyStick {
    fun solution(name: String): Int {
        var answer = 0
        var move = name.length - 1 //오른쪽으로만 간 횟수
        name.forEachIndexed { index, c ->
            answer += getJoyStickUpDownCount(c)
            if (index < name.length - 1 && name[index+1] == 'A') {
                var endA = index + 1
                while (endA < name.length && name[endA] == 'A') { endA++ }
                move = Math.min(move, index * 2 + (name.length - endA)) // 오른쪽으로 갔다 다시 왼쪽으로 가는 경우
                move = Math.min(move, index + (name.length - endA) * 2) // 왼쪽으로 갔다 다시 오른쪽으로 가는 경우
            }
        }

        return answer + move
    }

    private fun getJoyStickUpDownCount(alphabet: Char): Int {
        val isAlphabet = alphabet in 'A'..'Z'
        if (!isAlphabet) return 0
        val alphabetIndex = kotlin.math.abs('A' - alphabet)
        val reverseAlphabetIndex = kotlin.math.abs('[' - alphabet)
        return kotlin.math.min(alphabetIndex, reverseAlphabetIndex)
    }
}