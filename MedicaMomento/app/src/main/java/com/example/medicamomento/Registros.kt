package com.example.medicamomento

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView

class Registros : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adaptador
    private lateinit var medicamentos: List<DBhelper.Medicamento>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registros)


    val database = DBhelper.getInstance(this)
    medicamentos = database.getMedicamentos()


    //Configurar el RecyclerView y el adaptador
    recyclerView = findViewById(R.id.recyclerView)
    //recyclerView.layoutManager = LinearLayoutManager(this)
    adapter = Adaptador(medicamentos)
    recyclerView.adapter = adapter
    }
}