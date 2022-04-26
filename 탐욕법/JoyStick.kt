private fun main() {
    JoyStick().solution("ABAAAAAAAAABB")
}

class JoyStick {
    private val alphabetList = arrayListOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "A")
    private val reverseAlphabetList = alphabetList.reversed()
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
                answer += getConvertMoveCount(c.toString())
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
            answer += getConvertMoveCount(name.first().toString())
            val reverseList = name.toList().subList(1, name.length).reversed()
            reverseList.forEachIndexed { index, c ->
                answer += getConvertMoveCount(c.toString())
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

    private fun getConvertMoveCount(alphabet: String): Int {
        val isAlphabet = alphabet.length == 1 && alphabet >= "A" && alphabet <= "Z"
        if (!isAlphabet) {
            return 0
        }
        val alphabetIndex = alphabetList.indexOf(alphabet)
        val reverseAlphabet = reverseAlphabetList.indexOf(alphabet)
        return kotlin.math.min(alphabetIndex, reverseAlphabet)
    }
}