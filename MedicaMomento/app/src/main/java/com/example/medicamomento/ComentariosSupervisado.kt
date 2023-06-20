package com.example.medicamomento

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.*
import com.example.medicamomento.databinding.ActivityComentariosBinding
import com.example.medicamomento.databinding.ActivityComentariosSuperviasadoBinding

class ComentariosSupervisado : AppCompatActivity() {
private lateinit var binding: ActivityComentariosSuperviasadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComentariosSuperviasadoBinding.inflate(layoutInflater)
        setContentView(binding.root)



      //  setContentView(R.layout.activity_comentarios_superviasado)
        val txtcomentario:EditText = findViewById(R.id.txtCoemntarioS)
        val btncoment:Button = findViewById(R.id.btn_coments)

        val dbHelper = DBhelper(applicationContext)
        val db = dbHelper.writableDatabase

        btncoment.setOnClickListener {
            val comment = txtcomentario.text.toString()
            if (comment.isNotEmpty()){

                val values = ContentValues().apply {
                    put(Constants.comentarios.COLUMN_COMENT, comment)
                }
                db.insert(Constants.comentarios.TABLE_NAME, null, values)
                Toast.makeText(applicationContext,"Comentario guardado",Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, Comentarios::class.java))
                overridePendingTransition(0, 0)

            }else{
                Toast.makeText(
                    this,
                    "Primero llena un comentario",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        val cursor = db.query(Constants.comentarios.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        val comentarios = ArrayList<String>()
        while (cursor.moveToNext()) {
            val coment = cursor.getString(cursor.getColumnIndexOrThrow(Constants.comentarios.COLUMN_COMENT))
            val comentada = "$coment"
            comentarios.add(comentada)
        }

        val arrayAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            comentarios
        )

        val list_com = findViewById<ListView>(R.id.listv_comentariosS)
        list_com.adapter = arrayAdapter

    }
    fun regresar(view: View){
        val intent = Intent(this, modoSupervisado::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "Regreso")
        }
        startActivity(intent)
    }
}