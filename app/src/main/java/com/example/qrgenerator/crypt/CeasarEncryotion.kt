package com.example.qrgenerator.crypt


fun ceasarEncryption(text: String, key: Int) : String{

    val codeArr = Array(text.length){"text"}
    for ( i in codeArr.indices){
        codeArr[i] = Character.toChars(text[i].code + key).joinToString(separator = "")
    }
    return codeArr.joinToString(separator = "")
}

fun ceasarDecryption(text: String, key: Int) : String{

    val codeArr = Array(text.length){"text"}
    for ( i in codeArr.indices){
        codeArr[i] = Character.toChars(text[i].code - key).joinToString(separator = "")
    }
    return codeArr.joinToString(separator = "")
}




