fun main() {
    solution("0112")
}

fun solution(numbers: String): Int {

    val allNumberArray = permutation(numbers.map { it })
    var answer = 0
    return answer
}

private fun isPrime(n: Int): Boolean {
    var i = 2
    while (i * i <= n) {
        if (n % i++ == 0) return false
    }
    return true
}

fun <T> permutation(sub: List<T>, fin: List<T> = listOf()): List<List<T>> {
    return if(sub.isEmpty()) listOf(fin)
    else sub.flatMap { permutation(sub - it, fin + it) }
}