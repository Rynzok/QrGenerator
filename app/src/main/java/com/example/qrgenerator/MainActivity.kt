package com.example.qrgenerator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.WriterException


class MainActivity : AppCompatActivity() {
    var im: ImageView? = null
    var bGenerate: Button? = null
    var bScanner: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        im = findViewById(R.id.imageView)
        bScanner = findViewById(R.id.buttonScanner)
        bScanner?.setOnClickListener{
            startActivity(Intent(this, ScannerActivity::class.java))
        }
        bGenerate = findViewById(R.id.button)
        bGenerate?.setOnClickListener{
            generateQrCode("Круто же?")

        }
    }

    private fun generateQrCode(text: String){
        val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 200)
        try{
            val bMap = qrGenerator.getBitmap()
            im?.setImageBitmap(bMap)

        } catch(e: WriterException){

        }

    }
}