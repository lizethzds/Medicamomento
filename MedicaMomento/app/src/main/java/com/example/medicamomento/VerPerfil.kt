package com.example.medicamomento

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.medicamomento.databinding.ActivityAgregarPerfilBinding
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.bottomnavigation.BottomNavigationView

class VerPerfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_perfil)

        val nombre: EditText = findViewById(R.id.etname)
        val edad: EditText = findViewById(R.id.edEdad)
        val sangre: EditText = findViewById(R.id.edSangre)
        val enfermedades: EditText = findViewById(R.id.edEnfermedades)
        val alergias: EditText = findViewById(R.id.edAlergias)
        val servicio: EditText = findViewById(R.id.edServicio)

        var resultado: String? = null
        val dbHelper = DBhelper(applicationContext)
        val db = dbHelper.writableDatabase


        val consulta = "SELECT NOMBRE FROM PERFIL"
        val cursor = db.rawQuery(consulta, null)
        fun validar(): Boolean {
            return cursor.count > 0
            cursor.close()
            db.close()
        }

        if (validar() === true) {
            // Obtiene el perfil de la base de datos
            val dbHelper = DBhelper(applicationContext)
            val perfil = dbHelper.getPerfil().first()

            // Guarda el perfil en SharedPreferences
            guardarPerfilEnSharedPreferences(perfil)

            nombre.isEnabled = false
            edad.isEnabled = false
            sangre.isEnabled = false
            enfermedades.isEnabled = false
            alergias.isEnabled = false
            servicio.isEnabled = false

            val consulta1 = "SELECT NOMBRE FROM PERFIL"
            val consulta2 = "SELECT EDAD FROM PERFIL"
            val consulta3 = "SELECT T_Sangre FROM PERFIL"
            val consulta4 = "SELECT ENFERMEDADES FROM PERFIL"
            val consulta5 = "SELECT ALERGIAS FROM PERFIL"
            val consulta6 = "SELECT S_MEDICO FROM PERFIL"

            val cursor1 = db.rawQuery(consulta1, null)
            val cursor2 = db.rawQuery(consulta2, null)
            val cursor3 = db.rawQuery(consulta3, null)
            val cursor4 = db.rawQuery(consulta4, null)
            val cursor5 = db.rawQuery(consulta5, null)
            val cursor6 = db.rawQuery(consulta6, null)

            var resultado1 = ""
            var resultado2 = ""
            var resultado3 = ""
            var resultado4 = ""
            var resultado5 = ""
            var resultado6 = ""

            if (cursor1.moveToFirst()) {
                resultado1 = cursor1.getString(0)
            }
            if (cursor2.moveToFirst()) {
                resultado2 = cursor2.getString(0)
            }
            if (cursor3.moveToFirst()) {
                resultado3 = cursor3.getString(0)
            }
            if (cursor4.moveToFirst()) {
                resultado4 = cursor4.getString(0)
            }
            if (cursor5.moveToFirst()) {
                resultado5 = cursor5.getString(0)
            }
            if (cursor6.moveToFirst()) {
                resultado6 = cursor6.getString(0)
            }

            cursor1.close()
            cursor2.close()
            cursor3.close()
            cursor4.close()
            cursor5.close()
            cursor6.close()

            db.close()

            nombre.setText(resultado1)
            edad.setText(resultado2)
            sangre.setText(resultado3)
            enfermedades.setText(resultado4)
            alergias.setText(resultado5)
            servicio.setText(resultado6)

        }


        //bottomnav
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation2)
        bottomNavigationView.selectedItemId = R.id.bnPerfil
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bnPerfil -> true
                R.id.bnMedicamentos -> {
                    startActivity(Intent(applicationContext, InicioSupervisado::class.java))
                    finish()
                    true
                }

                R.id.bnInicio -> {
                    startActivity(Intent(applicationContext, modoSupervisado::class.java))
                    finish()
                    true
                }

                else -> false
            }
        }
    }

    private fun guardarPerfilEnSharedPreferences(perfil: DBhelper.Perfil) {
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("perfil_id", perfil.id)
        editor.putString("perfil_nombre", perfil.nombre)
        editor.putInt("perfil_edad", perfil.edad)
        editor.putString("perfil_sangre", perfil.sangre)
        editor.putString("perfil_enfermedades", perfil.enfermedades)
        editor.putString("perfil_alergias", perfil.alergias)
        editor.putString("perfil_servicio", perfil.servicio)
        editor.apply()
    }


}