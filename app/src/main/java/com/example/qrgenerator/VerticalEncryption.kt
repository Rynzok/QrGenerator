package com.example.qrgenerator

import kotlin.math.sqrt

fun verticalEncryption(text: String):String{
    return getFromArrayEncrypt(fillingArrayEncrypt(text, findSize(text.length)), findSize(text.length), text.length).joinToString(separator = "")
}

fun fillingArrayEncrypt(text: String, size: Pair<Int, Int>): Array<Array<String?>> {
    val arr = Array(size.second) { arrayOfNulls<String>(size.first)}
    var n = 0
    for (i in 0..<size.second) {
        for (j in 0..<size.first) {
            arr[i][j] = text[n].toString()
            n++
            if (n == text.length) {
                return arr
            }
        }
    }
    return arr
}

fun getFromArrayEncrypt(arr: Array<Array<String?>>, size: Pair<Int, Int>, length: Int): Array<String?> {
    val fullMassiv = arrayOfNulls<String>(length)
    var n = 0

    for (j in 0..<size.first) {
        for (i in 0..<size.second) {
                fullMassiv[n] = arr[i][j]
                n++
            if (n == length) {
                break
            }
        }
        if (n == length) {
            break
        }
    }
    return fullMassiv
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
