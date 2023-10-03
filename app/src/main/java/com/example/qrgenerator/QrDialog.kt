package com.example.qrgenerator

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class QrDialog : DialogFragment() {

    private val listActions = arrayOf("Save", "Send", "Use")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Выберите действие")

                .setItems(listActions
                ) { dialog, which ->
                    Toast.makeText(activity, "Выбранное дейсвтие: ${listActions[which]}",
                        Toast.LENGTH_SHORT).show()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}