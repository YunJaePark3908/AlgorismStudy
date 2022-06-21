import kotlin.math.abs

fun main() {
    println(CalculationMax().solution("100-200*300-500+20"))
}

class CalculationMax {
    fun solution(expression: String): Long {
        val allCalculationList = expression.filter { c -> c == '-' || c == '+' || c == '*' }.map { it }
        val allNumberList = expression.split("-", "+", "*")
        val allList = arrayListOf<String>()
        allNumberList.forEachIndexed { index, s ->
            allList.add(s)
            if (index != allNumberList.size - 1) {
                allList.add(allCalculationList[index].toString())
            }
        }
        val calculatePermutation = permutation(allCalculationList.toList().distinct())
        val resultValue = arrayListOf<Long>()
        calculatePermutation.forEach { list ->
            var expressionList = arrayListOf<String>().apply { addAll(allList) }
            list.forEach { cal ->
                expressionList = calculate(expressionList, cal.toString())
            }
            val result = expressionList.joinToString("").toLongOrNull()?: 0
            resultValue.add(abs(result))
        }
        return resultValue.maxOrNull()?:0
    }

    fun <T> permutation(el: List<T>, fin: List<T> = listOf(), sub: List<T> = el ): List<List<T>> {
        return if(sub.isEmpty()) listOf(fin)
        else sub.flatMap { permutation(el, fin + it, sub - it) }
    }

    private fun calculate(expression: ArrayList<String>, cal: String): ArrayList<String> {
        val tempList = arrayListOf<String>().apply { addAll(expression) }
        val index = expression.indexOfFirst { it == cal }
        var result = 0L
        when (cal) {
            "+" -> result = expression[index - 1].toLong() + expression[index + 1].toLong()
            "-" -> result = expression[index - 1].toLong() - expression[index + 1].toLong()
            "*" -> result = expression[index - 1].toLong() * expression[index + 1].toLong()
        }
        tempList.add(index - 1, result.toString())
        for (i in 0..2) {
            tempList.removeAt(index)
        }

        return if (tempList.contains(cal)) {
            calculate(tempList, cal)
        } else {
            tempList
        }
    }
}