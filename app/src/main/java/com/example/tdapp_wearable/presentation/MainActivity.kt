package com.example.tdapp_wearable.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.tdapp_wearable.R

class MainActivity : AppCompatActivity() {

    private lateinit var descriptionTextView: TextView
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        descriptionTextView = findViewById(R.id.description_text)
        val findPhoneButton: Button = findViewById(R.id.find_phone_button)

        // Inicializar Firebase Database
        database = FirebaseDatabase.getInstance().reference

        // Leer la descripción desde Firebase y actualizar la UI
        database.child("phone-location-description").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val description = snapshot.getValue(String::class.java) ?: "Descripción no disponible"
                descriptionTextView.text = description
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error al leer la descripción
                descriptionTextView.text = "Error al obtener la descripción"
            }
        })

        // Al hacer clic en el botón, enviar solicitud para localizar el teléfono
        findPhoneButton.setOnClickListener {
            // Actualizar la base de datos para indicar que se debe localizar el teléfono
            val requestReference = database.child("locate-phone-request")
            requestReference.setValue(true)
                .addOnSuccessListener {
                    Toast.makeText(this, "Solicitud de localización enviada", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error al enviar la solicitud", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
