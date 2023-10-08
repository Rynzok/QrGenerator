package com.example.qrgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class ScannerActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {
    private lateinit var zbView: ZBarScannerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zbView = ZBarScannerView(this)
        setContentView(zbView)
    }

    override fun onResume() {
        super.onResume()
        zbView.setResultHandler(this)
        zbView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        zbView.stopCamera()
    }

    override fun handleResult(result: Result?) {
        intent.putExtra("Content", result?.contents)
        setResult(RESULT_OK, intent)
        Toast.makeText(this, "${result?.contents}" , Toast.LENGTH_SHORT).show()
        finish()
    }
}