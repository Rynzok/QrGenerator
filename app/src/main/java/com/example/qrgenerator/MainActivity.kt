package com.example.qrgenerator

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.os.Environment
import android.widget.ArrayAdapter
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidmads.library.qrgenearator.QRGSaver
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.drawToBitmap
import com.example.qrgenerator.databinding.ActivityMainBinding
import com.google.zxing.WriterException
import java.util.Date


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataModel: DataModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bEncrypt.setOnClickListener{
            val enigma = EncryptionMachine(binding.userText.text.toString().trim(), binding.cryptList.selectedItem.toString(),true)
            binding.cryptText.text = enigma.encryption()
        }

        binding.bDecrypt.setOnClickListener{
            val enigma = EncryptionMachine(binding.userText.text.toString().trim(), binding.cryptList.selectedItem.toString(), false)
            binding.cryptText.text = enigma.encryption()
        }


        binding.bScanner.setOnClickListener{
            checkCameraPermission()
        }

        binding.bGeneration.setOnClickListener{
            generateQrCode(binding.userText.text.toString().trim())
            binding.userText.text.clear()

        }

        binding.imageView.setOnClickListener {
            val qrDialog = QrDialog()
            val manager = supportFragmentManager
            qrDialog.show(manager,"Круто")
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.encryptionMethods,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.cryptList.adapter = adapter
        }
        dataModel.selectedItem.observe(this) {
            when (it) {
                "Save" -> saveQr()
            }
        }

    }

    private fun generateQrCode(text: String){
        val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 300)
        qrGenerator.colorBlack = Color.WHITE
        qrGenerator.colorWhite = Color.BLACK
        try{
            val bMap = qrGenerator.bitmap
            binding.imageView.setImageBitmap(bMap)

        } catch(_: WriterException){

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

    private fun saveQr(){
        val qrgSaver = QRGSaver()
        val date = Calendar.getInstance().time
        qrgSaver.save(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).path + "/QRCode/",
            "Qr-code_${date.time}",
            binding.imageView.drawToBitmap(),
            QRGContents.ImageType.IMAGE_JPEG
        )
    }

}