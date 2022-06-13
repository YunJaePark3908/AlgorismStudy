import kotlin.collections.ArrayList

private fun main() {
    val case1 = arrayOf("java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50")
    val case1Query = arrayOf("java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150")
    RankSearch().solution(case1, case1Query)
}

class RankSearch {
    enum class Applicant(val subject: Array<String>) {
        DevLanguage(arrayOf("cpp", "java", "python")),
        JobGroup(arrayOf("backend", "frontend")),
        Career(arrayOf("junior", "senior")),
        SoulFood(arrayOf("chicken", "pizza")),
        Score(arrayOf())
    }
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val answer = ArrayList<Int>()
        val applicantHashMaps = info.map { infoStr->
            val hashMap = hashMapOf<String, String>()
            infoStr.split(" ").forEachIndexed { index, s ->
                when (index) {
                    0 -> hashMap[Applicant.DevLanguage.name] = s
                    1 -> hashMap[Applicant.JobGroup.name] = s
                    2 -> hashMap[Applicant.Career.name] = s
                    3 -> hashMap[Applicant.SoulFood.name] = s
                    4 -> hashMap[Applicant.Score.name] = s
                }
            }
            hashMap
        }
        val queryList = query.map { it.split(" and ", " ") }
        val queryHashMaps = queryList.map { list->
            val hashMap = hashMapOf<String, String>()
            list.forEachIndexed { index, s ->
                if (s != "-") {
                    when (index) {
                        0 -> hashMap[Applicant.DevLanguage.name] = s
                        1 -> hashMap[Applicant.JobGroup.name] = s
                        2 -> hashMap[Applicant.Career.name] = s
                        3 -> hashMap[Applicant.SoulFood.name] = s
                        4 -> hashMap[Applicant.Score.name] = s
                    }
                }
            }
            hashMap
        }

        queryHashMaps.forEach { queryHashMap ->
            var count = 0
            applicantHashMaps.forEach { applicantHashMap ->
                val isContains = ArrayList<Boolean>()
                queryHashMap[Applicant.DevLanguage.name]?.let {
                    isContains.add(it == applicantHashMap[Applicant.DevLanguage.name])
                }
                queryHashMap[Applicant.JobGroup.name]?.let {
                    isContains.add(it == applicantHashMap[Applicant.JobGroup.name])
                }
                queryHashMap[Applicant.Career.name]?.let {
                    isContains.add(it == applicantHashMap[Applicant.Career.name])
                }
                queryHashMap[Applicant.SoulFood.name]?.let {
                    isContains.add(it == applicantHashMap[Applicant.SoulFood.name])
                }
                queryHashMap[Applicant.Score.name]?.let {
                    isContains.add((it.toIntOrNull() ?: 0) <= (applicantHashMap[Applicant.Score.name]?.toIntOrNull() ?: 0))
                }

                if (!isContains.any { !it }) count++
            }
            answer.add(count)
        }
        return answer.toIntArray()
    }
}