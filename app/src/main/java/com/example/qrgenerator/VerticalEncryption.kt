package com.example.qrgenerator

import kotlin.math.sqrt

fun verticalEncryption(text: String):String{
    return getFromArrayEncrypt(fillingArrayEncrypt(text, findSize(text.length)), findSize(text.length), text.length).joinToString(separator = "")
}

fun verticalDecryption(text: String):String{
    return getFromArrayDecrypt(fillingArrayDecrypt(text, findSize(text.length)), findSize(text.length), text.length).joinToString(separator = "")
}

fun fillingArrayEncrypt(text: String, size: Pair<Int, Int>): Array<CharArray> {
    val arr = Array(size.second) { CharArray(size.first){' '}}
    var n = 0
    for (i in 0..<size.second) {
        for (j in 0..<size.first) {
            arr[i][j] = text[n]
            n++
            if (n == text.length) {
                return arr
            }
        }
    }
    return arr
}

fun getFromArrayEncrypt(arr: Array<CharArray>, size: Pair<Int, Int>, length: Int): Array<Char?> {
    val resultArray = arrayOfNulls<Char>(length)
    var n = 0

    for (j in 0..<size.first) {
        for (i in 0..<size.second) {
                resultArray[n] = arr[i][j]
                n++
            if (n == length) {
                break
            }
        }
        if (n == length) {
            break
        }
    }
    return resultArray
}

fun fillingArrayDecrypt(text: String, size: Pair<Int, Int>): Array<CharArray> {
    val arr = Array(size.second) { CharArray(size.first){' '} }
    var n = 0
    for (j in 0..<size.first) {
        for (i in 0..<size.second) {
            arr[i][j] = text[n]
            n++
            if (n == text.length) {
                return arr
            }
        }
    }
    return arr
}

fun getFromArrayDecrypt(arr: Array<CharArray>, size: Pair<Int, Int>, length: Int): Array<Char?> {
    val resultArray = arrayOfNulls<Char>(length)
    var n = 0

    for (i in 0..<size.second) {
        for (j in 0..<size.first) {
            if (arr[i][j] != ' ') {
                resultArray[n] = arr[i][j]
                n++

            }
        }
    }
    return resultArray
}

fun findSize(length: Int): Pair<Int,Int> {
    var size = Pair(0,0)
    var minimalDelta = 1000
    for (i in 1..sqrt(length.toDouble()).toInt()) {
        if (length % i == 0 && length / i - i < minimalDelta) {
            minimalDelta = length / i - i
            size = Pair(i, length / i)
        }
    }
    if (size.second == length) {
        size = findSize(length + 1)
    }
    return size
}
