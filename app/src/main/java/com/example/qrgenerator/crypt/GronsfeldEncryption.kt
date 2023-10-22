package com.example.qrgenerator.crypt

import kotlin.random.Random

fun gronsfeldEncryption(text: String, key: Int) : String{
    val codeArr = Array(text.length){"text"}
    val rand = Random(key)
    val keyArray = IntArray((3..9).random(rand)){ (1..9).random(rand) }
    var i = 0

    while (i < text.length){
        for (j in keyArray.indices){
            codeArr[i] = Character.toChars(text[i].code + keyArray[j]).joinToString(separator = "")
            i++
            if (i >= text.length) break
        }
    }
    return codeArr.joinToString(separator = "")
}

fun gronsfeldDecryption(text: String, key: Int) : String{
    val codeArr = Array(text.length){"text"}
    val rand = Random(key)
    val keyArray = IntArray((3..9).random(rand)){ (1..9).random(rand) }
    var i = 0

    while (i < text.length){
        for (j in keyArray.indices){
            codeArr[i] = Character.toChars(text[i].code - keyArray[j]).joinToString(separator = "")
            i++
            if (i >= text.length) break
        }
    }
    return codeArr.joinToString(separator = "")
}