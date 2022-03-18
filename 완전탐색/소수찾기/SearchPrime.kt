
fun main() {
    println(solution("011"))
}

fun solution(numbers: String): Int {
    var answer = 0
    val numbersCharArray = numbers.map { it.toString() }
    val combination = mutableListOf<List<String>>()
    for (i in 1..numbersCharArray.size) {
        combination(combination, numbersCharArray, Array<Boolean>(numbersCharArray.size) { false }, 0,  i)
    }
    combination.map { permutation(it) }.map {
        it.map {
            val sb = StringBuffer()
            it.forEach {
                sb.append(it)
            }
            sb.toString().toInt()
        }
    }.flatten().distinct().forEach { if (isPrime(it)) { answer++ } }

    return answer
}

private fun isPrime(n: Int): Boolean {
    if (n == 0 || n == 1) return false
    var i = 2
    while (i * i <= n) { if (n % i++ == 0) return false }
    return true
}

fun <T> permutation(sub: List<T>, fin: List<T> = listOf()): List<List<T>> {
    return if(sub.isEmpty()) listOf(fin)
    else sub.flatMap { permutation(sub - it, fin + it) }
}

fun <T> combination(answer: MutableList<List<T>>, el: List<T>, ck: Array<Boolean>, start: Int, target: Int) {
    if(target == 0) {
        answer.addAll( listOf(el.filterIndexed { index, t -> ck[index] }) )
    } else {
        for(i in start until el.size) {
            ck[i] = true
            combination(answer, el, ck, i + 1, target - 1)
            ck[i] = false
        }
    }
}