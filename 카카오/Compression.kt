
private fun main() {
    Compression().solution("xababcdcdababcdcd")

}

class Compression {
    fun solution(s: String): Int {
        val compressCount = findCompressCount(s)
        if (s.isEmpty() || s.length == 1) return s.length
        val compressList = s.chunked(compressCount).map {
            val sb = StringBuffer()
            it.forEach { str ->
                sb.append(str)
            }
            sb.toString()
        }
        var compressNum = 1 //연속 압축 번호
        val result = StringBuffer()
        for (i in 0 until compressList.size - 1) {
            if (compressList[i] == compressList[i+1]) {
                compressNum++
            }
            if (compressList[i] != compressList[i+1]) {
                if (compressNum != 1) {
                    result.append(compressNum).append(compressList[i])
                    compressNum = 1
                } else {
                    result.append(compressList[i])
                }
            }
            if (i == compressList.size - 2) {
                if (compressList[i] == compressList[i+1]) {
                    result.append(compressNum).append(compressList[i])
                } else {
                    result.append(compressList[i+1])
                }
            }
        }
        return result.toString().length
    }

    private fun findCompressCount(s: String): Int {
        var compressCount = 1 //적정 압축 개수
        val compressStrCountList = mutableListOf<Int>() //압축 한 문자열 개수

        while (compressCount != s.length) {
            val compressList = s.chunked(compressCount).map {
                val sb = StringBuffer()
                it.forEach { str ->
                    sb.append(str)
                }
                sb.toString()
            }
            var temp = ""
            var sameStrCount = 0
            val continuityCountList = mutableListOf<Int>()
            compressList.forEachIndexed { index, str ->
                if (str == temp) {
                    sameStrCount++
                } else if (sameStrCount != 0) {
                    continuityCountList.add(sameStrCount)
                    sameStrCount = 0
                }

                if (index == compressList.size - 1 && sameStrCount != 0) {
                    continuityCountList.add(sameStrCount)
                }
                temp = str
            }
            var compressStrCount = 0
            continuityCountList.forEach {
                compressStrCount += (compressCount * it) - 1
            }
            compressStrCountList.add(compressStrCount)
            compressCount++
        }
        return compressStrCountList.indexOf(compressStrCountList.maxOrNull()) + 1
    }

}
