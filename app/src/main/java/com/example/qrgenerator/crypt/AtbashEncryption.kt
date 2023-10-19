package com.example.qrgenerator.crypt

import kotlin.math.abs

fun atbashEncryption(text: String) : String {
    val codeArr = Array(text.length){"text"}
    for ( i in codeArr.indices){
        codeArr[i] = Character.toChars(2000 - text[i].code).joinToString(separator = "")
    }
    return codeArr.joinToString(separator = "")
}

fun atbashDecryption(text: String) : String {
    val codeArr = Array(text.length){"text"}
    for ( i in codeArr.indices){
        codeArr[i] = Character.toChars(abs(text[i].code - 2000)).joinToString(separator = "")
    }
    return codeArr.joinToString(separator = "")
}