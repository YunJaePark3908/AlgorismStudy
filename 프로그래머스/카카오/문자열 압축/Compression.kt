
private fun main() {
    Compression().solution("abababab")

}

class Compression {
    fun solution(s: String): Int = (1..s.length/2 + 1).map { r ->
        val chunk = s.chunked(r)
        var count = 1

        (chunk.subList(1, chunk.size) + "").mapIndexed { index, str ->
            when (str) {
                chunk[index] -> {
                    count++
                    ""
                }
                else -> {
                    val value = "${if (count == 1) "" else count.toString()}${chunk[index]}"
                    count = 1

                    value
                }
            }
        }.joinToString("").length
    }.minOrNull()!!
}
