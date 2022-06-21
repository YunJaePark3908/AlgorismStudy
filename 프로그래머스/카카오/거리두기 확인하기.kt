fun main() {
    DistanceCheck().solution(
        arrayOf(
            arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
            arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
            arrayOf(
                "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"
            ),
            arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
            arrayOf(
                "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"
            )
        )
    )
}

class DistanceCheck {
    private val map = Array(5) { CharArray(5) }
    fun solution(places: Array<Array<String>>): IntArray {
        val answer = arrayListOf<Int>()
        //전체 대기실
        places.forEachIndexed { _, strings ->
            //대기실
            strings.forEachIndexed { i, s ->
                //한 테이블
                s.forEachIndexed { j, char ->
                    map[i][j] = char
                }
            }
            run {
                for (c in 0..4) {
                    for (r in 0..4) {
                        val up = c - 1
                        val down = c + 1
                        val left = r - 1
                        val right = r + 1

                        if (checkPerson(c, r, up, r)) {
                            answer.add(0)
                            return@run
                        }
                        if (checkPerson(c, r, down, r)) {
                            answer.add(0)
                            return@run
                        }
                        if (checkPerson(c, r, c, left)) {
                            answer.add(0)
                            return@run
                        }
                        if (checkPerson(c, r, c, right)) {
                            answer.add(0)
                            return@run
                        }
                        var personCount = 0
                        if (checkDesk(c, r, up, r)) {
                            personCount++
                        }
                        if (checkDesk(c, r, down, r)) {
                            personCount++
                        }
                        if (checkDesk(c, r, c, left)) {
                            personCount++
                        }
                        if (checkDesk(c, r, c, right)) {
                            personCount++
                        }
                        if (personCount >= 2) {
                            answer.add(0)
                            return@run
                        }
                    }
                }
                answer.add(1)
            }
        }

        return answer.toIntArray()
    }

    private fun isPerson(c: Int, r: Int): Boolean = map[c][r] == 'P'
    private fun checkPerson(startColumn: Int, startRow: Int, c: Int, r: Int): Boolean {
        if (c < 0 || r < 0 || c >= map.size || r >= map.size) return false
        if (isPerson(startColumn, startRow) && isPerson(c, r)) return true
        return false
    }

    private fun isDesk(c: Int, r: Int): Boolean = map[c][r] == 'O'
    private fun checkDesk(startColumn: Int, startRow: Int, c: Int, r: Int): Boolean {
        if (c < 0 || r < 0 || c >= map.size || r >= map.size) return false
        if (isDesk(startColumn, startRow) && isPerson(c, r)) return true
        return false
    }
}