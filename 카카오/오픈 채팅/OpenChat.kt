fun main() {
    val record = arrayOf("Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan")
    OpenChat().solution(record)
}

class OpenChat {
    fun solution(record: Array<String>): Array<String> {
        val answer = mutableListOf<String>()
        val member = HashMap<String, String>()
        record.forEach {
            val split = it.split(" ")
            if (split.size == 3) {
                member[split[1]] = split[2]
            }
        }
        record.forEach {
            val split = it.split(" ")
            if (split.size < 2) return@forEach
            when (split[0]) {
                "Enter" -> answer.add("${member[split[1]]}님이 들어왔습니다.")
                "Leave" -> answer.add("${member[split[1]]}님이 나갔습니다.")
            }
        }

        return answer.toTypedArray()
    }

}
