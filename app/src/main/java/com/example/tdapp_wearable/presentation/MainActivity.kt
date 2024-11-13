package com.example.tdapp_wearable.presentation

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tdapp_wearable.R
import com.google.android.gms.wearable.Wearable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val findPhoneButton: Button = findViewById(R.id.find_phone_button)

        findPhoneButton.setOnClickListener {
            val messageClient = Wearable.getMessageClient(this)
            messageClient.sendMessage(
                "<ID_DEL_DISPOSITIVO_MÓVIL>",
                "/find-phone",
                null
            ).addOnSuccessListener {
                // Acción en caso de éxito
            }.addOnFailureListener {
                // Acción en caso de error
            }
        }
    }
}