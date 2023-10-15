package com.example.qrgenerator

import com.example.qrgenerator.crypt.meanderDecryption
import com.example.qrgenerator.crypt.meanderEncryption
import com.example.qrgenerator.crypt.spiralDecryption
import com.example.qrgenerator.crypt.spiralEncryption
import com.example.qrgenerator.crypt.verticalDecryption
import com.example.qrgenerator.crypt.verticalEncryption

class EncryptionMachine (
    private var text: String,
    private var method: String,
    private var way: Boolean){


    fun encryption() : String{
        return if(way){
            encryptionMethod()
        }else{
            decryptionMethod()
        }
    }

    private fun encryptionMethod():String{
        return when(method){
            "Вертикальное шифрование" -> verticalEncryption(text.replace(" ", ""))
            "Меандровое шифрование" -> meanderEncryption(text.replace(" ", ""))
            "Спиральное шифрование" -> spiralEncryption(text.replace(" ", ""))
            else -> "Ты ебанат?"
        }
    }

    private fun decryptionMethod():String{
        return when(method){
            "Вертикальное шифрование" -> verticalDecryption(text.replace(" ", ""))
            "Меандровое шифрование" -> meanderDecryption(text.replace(" ", ""))
            "Спиральное шифрование" -> spiralDecryption(text.replace(" ", ""))
            else -> "Ты ебанат?"
        }
    }

}