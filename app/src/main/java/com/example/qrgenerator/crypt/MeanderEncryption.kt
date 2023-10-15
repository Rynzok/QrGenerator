package com.example.qrgenerator.crypt

fun meanderEncryption(text: String) : String {
    return getFromArrayEncryptMeander(fillingArrayEncrypt(text, findSize(text.length)), findSize(text.length),text.length).joinToString(separator = "").replace(" ", "")
}

fun meanderDecryption(text : String) : String{
    return getFromArrayDecrypt(meanderWritingToArray(text, findSize(text.length)), findSize(text.length), text.length).joinToString(separator = "")
}

fun getFromArrayEncryptMeander(arr: Array<CharArray>, size: Pair<Int, Int>, length: Int): Array<Char?> {
    val resultArray = arrayOfNulls<Char>(length)
    var n = 0

    for (j in 0..<size.first) {
        if (j % 2 == 0){
            for (i in 0..<size.second) {
                if (arr[i][j] != ' ')
                {
                    resultArray[n] = arr[i][j]
                    n++
                }
                if (n == length) break
            }
        }
        else{
            for (i in size.second - 1 downTo 0) {
                if (arr[i][j] != ' '){
                    resultArray[n] = arr[i][j]
                    n++
                }
                if (n == length) break
            }
        }
        if (n == length) {
            break
        }
    }
    return resultArray
}

fun meanderWritingToArray(text: String, size: Pair<Int, Int>): Array<CharArray>{
    val arr = Array(size.second) { CharArray(size.first){' '} }
    var n = 0
    for (j in 0..<size.first) {
        if (j % 2 == 0){
            for (i in 0..<size.second) {
                arr[i][j] = text[n]
                n++
                if (n == text.length) break
            }
        }
        else{
            for (i in size.second - 1 downTo 0) {
                arr[i][j] = text[n]
                n++
                if (n == text.length) break
            }

        }

        if (n == text.length) break

    }
    return arr

}
