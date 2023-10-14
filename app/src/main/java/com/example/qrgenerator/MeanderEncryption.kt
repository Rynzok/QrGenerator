package com.example.qrgenerator

fun meanderEncryption(text: String) : String {
    return getFromArrayEncryptMeander(fillingArrayEncrypt(text, findSize(text.length)), findSize(text.length),text.length).joinToString(separator = "")
}

fun meanderDecryption(text : String) : String{
    return getFromArrayDecrypt(meanderWritingToArray(text, findSize(text.length)), findSize(text.length), text.length).joinToString(separator = "")
}

fun getFromArrayEncryptMeander(arr: Array<Array<String?>>, size: Pair<Int, Int>, length: Int): Array<String?> {
    val resultArray = arrayOfNulls<String>(length)
    var n = 0

    for (j in 0..<size.first) {
        if (j % 2 == 0){
            for (i in 0..<size.second) {
                resultArray[n] = arr[i][j]
                n++
                if (n == length) break
            }
        }
        else{
            for (i in size.second - 1 downTo 0) {
                resultArray[n] = arr[i][j]
                n++
                if (n == length) break
            }

        }

        if (n == length) {
            break
        }
    }
    return resultArray
}

fun meanderWritingToArray(text: String, size: Pair<Int, Int>): Array<Array<String?>>{
    val arr = Array(size.second) { arrayOfNulls<String>(size.first)}
    var n = 0
    for (j in 0..<size.first) {
        if (j % 2 == 0){
            for (i in 0..<size.second) {
                arr[i][j] = text[n].toString()
                n++
                if (n == text.length) break
            }
        }
        else{
            for (i in size.second - 1 downTo 0) {
                arr[i][j] = text[n].toString()
                n++
                if (n == text.length) break
            }

        }

        if (n == text.length) break

    }
    return arr

}
