private fun main() {
    val str = "XWASSS"
    if (str.contentEquals("WX")) println("isContains!!")
    val case1 = arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH")
    val case2 = arrayOf("ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD")
    val case3 = arrayOf("XYZ", "XWY", "WXA")
    val a = arrayOf(arrayOf(1,2,3), arrayOf(1,2,3))
    MenuRenewal().solution(case1, intArrayOf(2,3,4))
}

class MenuRenewal {
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        var answer: Array<String> = arrayOf()
        //모든 메뉴의 조합 만들기
        val allMenuList = orders.flatMap { it.toList() }.distinct().sorted()
        val menuCombination = mutableListOf<String>()
        val maxCourseSize = orders.maxOf { it.length }
        course.forEach {
            if (it <= maxCourseSize) {
                combination(menuCombination, allMenuList, Array<Boolean>(allMenuList.size) { false }, 0, it)
            }
        }
        val hasMap = hashMapOf<String, Int>()
        //손님들이 얼마나 조합 된 메뉴를 주문 했는지 체크
        menuCombination.forEach { menuCombinationStr ->
            var count = 0
            orders.forEach { order ->
                var isContain = true
                run {
                    menuCombinationStr.forEach { char ->
                        if (!order.contains(char)) {
                            isContain = false
                            return@run
                        }
                    }
                }
                if (isContain) count++
            }
            hasMap[menuCombinationStr] = count
        }
        //필터링
        val filterList = hasMap.filter { it.value >= 2 }
        val tempFilterList = filterList.keys.toMutableList()
        filterList.forEach { firstPair ->
            run {
                filterList.forEach { secondPair ->
                    if (firstPair != secondPair) {
                        var isContain = true
                        firstPair.key.forEach {
                            if (secondPair.key.contains(it)) isContain = false
                        }

                        if (isContain && firstPair.key.length < secondPair.key.length && firstPair.value < secondPair.value
                            || firstPair.key.length == secondPair.key.length && firstPair.value < secondPair.value
                        ) {
                            tempFilterList.remove(firstPair.key)
                            return@run
                        }
                    }
                }
            }
        }

        answer = tempFilterList.sorted().toTypedArray()
        return answer
    }

    fun <T> combination(answer: MutableList<String>, el: List<T>, ck: Array<Boolean>, start: Int, target: Int) {
        if(target == 0) {
            val sb = StringBuffer()
            for (i in el.indices) {
                if (ck[i]) {
                    sb.append(el[i])
                }
            }
            answer.add(sb.toString())
        } else {
            for(i in start until el.size) {
                ck[i] = true
                combination(answer, el, ck, i + 1, target - 1)
                ck[i] = false
            }
        }
    }
}