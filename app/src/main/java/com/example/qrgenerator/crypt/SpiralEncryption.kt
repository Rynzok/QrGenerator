package com.example.qrgenerator.crypt

fun spiralEncryption(text: String) : String {
    return getFromArrayEncryptSpiral(fillingArrayEncrypt(text, findSize(text.length)), findSize(text.length), text.length).joinToString(separator = "")
}

fun spiralDecryption(text: String) : String {
    return getFromArrayDecrypt(spiralWritingToArray(text, findSize(text.length)), findSize(text.length), text.length).joinToString(separator = "")
}

fun getFromArrayEncryptSpiral(arr: Array<CharArray>, size: Pair<Int, Int>, length: Int): Array<Char?>{
    val resultArray = arrayOfNulls<Char>(length)
    var n = 0
    var k = 0

    while (n != length)
    {
        var j1 = k
        for (i in k..<size.second - k) {
            if (arr[i][j1] != ' ') {
                resultArray[n] = arr[i][j1]
                n++
            }
        }

        var i1 = size.second -1 - k

        for (j in k + 1..<size.first - k)
        {
            if (arr[i1][j] != ' ')
            {
                resultArray[n] = arr[i1][j]
                n++
            }
        }

        j1 = size.first - 1 - k

        for (i in size.second - 2 - k downTo k )
        {
            if (arr[i][j1] != ' ')
            {
                resultArray[n] = arr[i][j1]
                n++
            }
        }

        i1 = k

        for (j in size.first - 2 - k downTo k + 1)
        {
            if (arr[i1][j] != ' ')
            {
                resultArray[n] = arr[i1][j]
                n++
            }
        }
        k++
    }
    return resultArray
}

fun spiralWritingToArray(text: String, size: Pair<Int, Int>): Array<CharArray>{
    val arr = Array(size.second) { CharArray(size.first){' '} }
    var n = 0
    var k = 0
    while (n != text.length)
    {
        var j1 = k
        for (i in k..<size.second - k) {
            arr[i][j1] = text[n]
            n++
            if (n == text.length) break
        }
        if (n == text.length) break

        var i1 = size.second -1 - k

        if (size.first * size.second > text.length && k == 0){
            for (j in 1..<size.first- 1)
            {
                arr[i1][j] = text[n]
                n++
                if (n == text.length) break
            }
        }else {
            for (j in k + 1..<size.first- k)
            {
                arr[i1][j] = text[n]
                n++
                if (n == text.length) break
            }
        }


        if (n == text.length) break

        j1 = size.first - 1 - k

        for (i in size.second - 2 - k downTo k )
        {
            arr[i][j1] = text[n]
            n++
            if (n == text.length) break
        }
        if (n == text.length) break

        i1 = k

        for (j in size.first - 2 - k downTo k + 1)
        {
            arr[i1][j] = text[n]
            n++
            if (n == text.length) break
        }
        if (n == text.length) break
        k++
    }
    return arr

}