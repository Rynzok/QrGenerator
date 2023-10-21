package com.example.qrgenerator.crypt

fun futuramaEncryption(text: String) : String{

    val codeArr = Array(text.length){"text"}
    for ( i in codeArr.indices){
        if (i == 0) codeArr[i] = text[i].toString()
        else {
            var cod = text[i].code + codeArr[i][0].code
            if(cod > 2000) cod -=2000
            codeArr[i] = Character.toChars(cod).joinToString(separator = "")
        }
    }
    return codeArr.joinToString(separator = "")
}

fun futuramaDecryption(text: String) : String{

    val codeArr = Array(text.length){"text"}
    for ( i in codeArr.indices){
        if (i == 0) codeArr[i] = text[i].toString()
        else {
            var cod = text[i].code - codeArr[i][0].code
            if(cod < 1) cod +=2000
            codeArr[i] = Character.toChars(cod).joinToString(separator = "")
        }
    }
    return codeArr.joinToString(separator = "")
}