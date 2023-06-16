package com.example.medicamomento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Registros : AppCompatActivity() {
    private lateinit var medicamentos: List<DBhelper.Medicamento>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registros)
    }

}