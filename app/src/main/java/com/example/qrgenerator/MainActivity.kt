package com.example.qrgenerator

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
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
    var bEncrypt: Button? = null
    var bDecrypt: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        im = findViewById(R.id.imageView)
        val userDate: EditText = findViewById(R.id.userText)
        val spinner: Spinner = findViewById(R.id.cryptList)
        val cryptText: TextView? = findViewById(R.id.CryptText)
        bGenerate = findViewById(R.id.button)
        bScanner = findViewById(R.id.buttonScanner)
        bEncrypt = findViewById(R.id.bEncrypt)
        bDecrypt = findViewById(R.id.bDecrypt)

        bEncrypt?.setOnClickListener{
            cryptText?.text = encryption(userDate.text.toString().trim(), "One", true)
        }

        bDecrypt?.setOnClickListener{
            cryptText?.text = encryption(userDate.text.toString().trim(), "One", false)
        }


        bScanner?.setOnClickListener{
            checkCameraPermission()
        }

        bGenerate?.setOnClickListener{
            generateQrCode(userDate.text.toString().trim())
            userDate.text.clear()

        }

        ArrayAdapter.createFromResource(
            this,
            R.array.encryptionMethods,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
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
    private fun encryption(
        text: String,
        method: String,
        way: Boolean
    ): (String)
    {
        return if(way){
            "Ты пидр"
        }else{
            "Ты лох"
        }

    }


}