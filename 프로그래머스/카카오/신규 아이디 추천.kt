package programers

private fun main() {
    Test1().solution("...!@BaT#*..y.abcdefghijklm.")
}

class Test1 {
    fun solution(new_id: String): String {
        var answer = ""
        //1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
        //2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
        new_id.lowercase().forEach {
            if (!isDeleteChar(it.code)) answer += it
        }
        //3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
        while (answer.contains("..")) {
            answer = answer.replace("..", ".")
        }
        //4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        if (answer.firstOrNull() == '.') answer = answer.substring(1, answer.length)
        if (answer.lastOrNull() == '.') answer = answer.substring(0, answer.length - 1)
        //5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
        if (answer.isEmpty()) answer = "a"
        //6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
        if (answer.length >= 16) answer = answer.substring(0, 15)
        //만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
        if (answer.lastOrNull() == '.') answer = answer.substring(0, answer.length - 1)
        //7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        if (answer.length <= 2) {
            val lastStr = answer.lastOrNull()?: ""
            while (answer.length != 3) {
                answer += lastStr
            }
        }
        return answer
    }
    fun isDeleteChar(charNum: Int): Boolean {
        return !(charNum == 95
                || charNum in 97..122
                || charNum == 45
                || charNum == 46
                || charNum in 48..57
                )
    }
}