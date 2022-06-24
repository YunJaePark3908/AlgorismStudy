package baekjoon

import java.util.*

var N:Int = 5
var S:Int = 0
var ans = 0

fun main() {
    val array = listOf("-7", "-3", "-2", "5", "8")
    dfs(0, 0, array)
    if(S==0) ans--
    println(ans)
}

fun dfs(idx: Int, sum: Int, arr:List<String>) {
    if (idx == N) {
        if (sum == S) ans++
        return
    }
    dfs(idx+1, sum, arr)
    dfs(idx+1, sum+arr[idx].toInt(), arr)
}