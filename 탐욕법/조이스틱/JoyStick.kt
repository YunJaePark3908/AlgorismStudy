private fun main() {
    JoyStick().solution("GFDY")
}

class JoyStick {
    fun solution(name: String): Int {
        var answer = 0
        var isRight = true
        if (name.length >= 2) {
            var cursor = 1
            var reverseCursor = name.length - 1
            while (true) {
                if (name[cursor] == 'A' && name[reverseCursor] != 'A') {
                    //왼쪽 진행
                    isRight = false
                    break
                } else if (name[cursor] != 'A' && name[reverseCursor] == 'A') {
                    //오른쪽 진행
                    isRight = true
                    break
                } else {
                    if (reverseCursor - cursor == 0 || reverseCursor - cursor == 1) {
                        isRight = true
                        break
                    }
                    cursor++
                    reverseCursor--
                }
            }
        }
        if (isRight) {
            name.forEachIndexed { index, c ->
                answer += getJoyStickUpDownCount(c)
                var isEnd = true
                if (index != name.length - 1) {
                    for (i in index + 1 until name.length) {
                        if (name[i] != 'A') {
                            isEnd = false
                        }
                    }
                }
                if (!isEnd) {
                    answer++
                }
            }
        } else {
            answer += getJoyStickUpDownCount(name.first())
            val reverseList = name.toList().subList(1, name.length).reversed()
            reverseList.forEachIndexed { index, c ->
                answer += getJoyStickUpDownCount(c)
                var isEnd = true
                if (index != reverseList.size - 1) {
                    for (i in index until reverseList.size) {
                        if (reverseList[i] != 'A') {
                            isEnd = false
                        }
                    }
                }
                if (!isEnd) {
                    answer++
                }
            }
        }
        return answer
    }

    private fun getJoyStickUpDownCount(alphabet: Char): Int {
        val isAlphabet = alphabet in 'A'..'Z'
        if (!isAlphabet) {
            return 0
        }
        val alphabetIndex = kotlin.math.abs('A' - alphabet)
        val reverseAlphabetIndex = kotlin.math.abs('[' - alphabet)
        return kotlin.math.min(alphabetIndex, reverseAlphabetIndex)
    }
}