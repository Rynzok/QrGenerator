package com.example.qrgenerator

class EncryptionMachine (
    var text: String,
    var method: String,
    var way: Boolean){

    var result: String? = null

    fun encryption() : String{
        return if(way){
            encryptionMethod()
        }else{
            decryptionMethod()
        }
    }

    private fun encryptionMethod():String{
        return when(method){
            "One" -> verticalEncryption(text.replace(" ", ""))
            "Two" -> "Во-вторых ты лох"
            "Three" -> "Во-третьих ты чмо"
            else -> "Ты ебанат?"
        }
    }

    private fun decryptionMethod():String{
        return when(method){
            "One" -> "Во-первых ты пидр и лох"
            "Two" -> "Во-вторых ты лох и пидр"
            "Three" -> "Во-третьих ты чмо, лох и пидр"
            else -> "Ты ебанат?"
        }
    }

}