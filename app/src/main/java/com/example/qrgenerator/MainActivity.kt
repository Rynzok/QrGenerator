package com.example.qrgenerator

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.ArrayAdapter
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidmads.library.qrgenearator.QRGSaver
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.drawToBitmap
import com.example.qrgenerator.databinding.ActivityMainBinding
import com.google.zxing.WriterException
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataModel: DataModel by viewModels()
    private val getContent = registerForActivityResult(MyActivityContract()){text: String? ->
            binding.userText.setText(text)

    }


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
            if(binding.cryptText.text == ""){
                generateQrCode(binding.userText.text.toString().trim())
            }
            else{
                generateQrCode(binding.cryptText.text.toString().trim())
                binding.cryptText.text = null
            }
            binding.userText.text.clear()

        }

        binding.bChange.setOnClickListener {
            binding.userText.setText(binding.cryptText.text)
            binding.cryptText.text = null
        }

        binding.imageView.setOnClickListener {
            val qrDialog = QrDialog()
            val manager = supportFragmentManager
            qrDialog.show(manager,"Cool")
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
                "Send" -> shareQr()
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
//            startActivity(Intent(this, ScannerActivity::class.java))
            getContent.launch("idiot")
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
                getContent.launch("idiot")
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun saveQr(){
        val qrgSaver = QRGSaver()
        val sdf = SimpleDateFormat("dd-M hh-mm-ss")
        val date = sdf.format(Date())
        qrgSaver.save(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).path + "/QRCode/",
            "Qr-code_${date}",
            binding.imageView.drawToBitmap(),
            QRGContents.ImageType.IMAGE_JPEG
        )
    }

    private fun shareQr(){
        val contentUri = getContentUri()
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/png"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here") //for sharing with email apps
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(shareIntent, "Share Via"))
    }

    private  fun getContentUri(): Uri?{
        val imagesFolder = File(cacheDir, "images")
        var contentUri: Uri? = null
        try {
            imagesFolder.mkdirs() //create if not exists
            val file = File(imagesFolder, "shared_image.png")
            val stream = FileOutputStream(file)
            binding.imageView.drawToBitmap().compress(Bitmap.CompressFormat.PNG, 50, stream)
            stream.flush()
            stream.close()
            contentUri = FileProvider.getUriForFile(this, "com.technifysoft.shareimagetext.fileprovider", file)
        } catch (e: Exception) {
            Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
        }
        return contentUri

    }

}