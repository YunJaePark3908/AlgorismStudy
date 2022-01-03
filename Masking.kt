import java.util.*
import java.util.regex.Pattern

private fun main() {
    print(getPhoneNumberMasking("0100-1243-5678"))
}

/**
 * 카드 번호 마스킹
 * 1234-1234-1234-****
 */
fun getCardNumMasking(cardNum: String): String {
    val regex = "(\\d{4})-?(\\d{4})-?(\\d{4})-?(\\d{3,4})$"
    val matcher = Pattern.compile(regex).matcher(cardNum)
    return if (matcher.find()) {
        val maskingText = StringBuffer()
        val target = matcher.group(4)
        val length = target.length
        for (i in 0 until length) {
            maskingText.append("*")
        }
        cardNum.replace(target, maskingText.toString())
    } else {
        cardNum
    }
}

/** 이메일 끝 3자리 *** 표시
 * ex) abc@email.com -> ***@email.com
 */
fun getEmailMasking(email: String): String {
    val result = StringBuffer()
    val REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    val pattern = Pattern.compile(REGEX_EMAIL, Pattern.CASE_INSENSITIVE)
    if (!pattern.matcher(email).matches()) {
        //이메일 형식이 맞지 않았을 때
        return email
    }
    val nickNameSplit = email.split("@")
    if (nickNameSplit.size == 2) {
        if (nickNameSplit[0].length > 3) {
            result.append(nickNameSplit[0].substring(0, 3))
        } else {
            result.append(nickNameSplit[0])
        }
        result.append("***")
            .append("@")
            .append(nickNameSplit[1])
    }

    return result.toString()
}

/**
 * 휴대폰 번호 마스킹
 * ex) 010-1234-5678 -> 010-****-5678
 */
fun getPhoneNumberMasking(phoneNum: String): String {
    val regex = "(\\d{2,3})-?(\\d{3,4})-?(\\d{4})$"
    val matcher = Pattern.compile(regex).matcher(phoneNum)
    return if (matcher.find()) {
        val maskingText = StringBuffer()
        val target = matcher.group(2)?: ""
        val length = target.length
        for (i in 0 until length) {
            maskingText.append("*")
        }
        phoneNum.replace(target, maskingText.toString())
    } else {
        phoneNum
    }
}