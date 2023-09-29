package com.example.qrgenerator

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.WriterException


class MainActivity : AppCompatActivity() {
    var im: ImageView? = null
    var bGenerate: Button? = null
    var bScanner: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        im = findViewById(R.id.imageView)
        bGenerate = findViewById(R.id.button)
        bScanner = findViewById(R.id.buttonScanner)

        bScanner?.setOnClickListener{
            checkCameraPermission()
        }

        bGenerate?.setOnClickListener{
            generateQrCode("Круто же?")

        }
    }

    private fun generateQrCode(text: String){
        val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 300)
        qrGenerator.colorBlack = Color.WHITE
        qrGenerator.colorWhite = Color.BLACK
        try{
            val bMap = qrGenerator.bitmap
            im?.setImageBitmap(bMap)

        } catch(e: WriterException){

        }

    }

    private fun checkCameraPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA), 12)

        } else{
            startActivity(Intent(this, ScannerActivity::class.java))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 12){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivity(Intent(this, ScannerActivity::class.java))
            }
        }

    }
}