package com.example.medicamomento

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.DataSetObserver
import android.graphics.BitmapFactory
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class Adaptador2(private val medicamentos: List<DBhelper.Medicamento>) : RecyclerView.Adapter<Adaptador2.ViewHolder>(),
    ListAdapter {

    private var selectedPosition = RecyclerView.NO_POSITION

    fun updateSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }
    fun getSelectedPosition(): Int {
        return selectedPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medicinaitem2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medicamento = medicamentos[position]
        holder.bind(medicamento)
        holder.itemView.isActivated = position == selectedPosition

        holder.itemView.setOnClickListener {
            selectedPosition = holder.adapterPosition
            notifyDataSetChanged()
            updateSelectedPosition(position)
        }
    }

    override fun getItemCount(): Int {
        return medicamentos.size
    }
    fun getMedicamentoAtPosition(position: Int): DBhelper.Medicamento? {
        if (position != RecyclerView.NO_POSITION) {
            return medicamentos[position]
        }
        return null
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtNombre: TextView = itemView.findViewById(R.id.txt_name)
        private val txtDosis: TextView = itemView.findViewById(R.id.txt_dosis)
        private val txtHorario: TextView = itemView.findViewById(R.id.txt_time)
        private val imgmed: ImageView = itemView.findViewById(R.id.imgMedicamento)
        

        fun bind(medicamento: DBhelper.Medicamento) {
            val bitmap = BitmapFactory.decodeByteArray(medicamento.imagen, 0, medicamento.imagen.size)
            imgmed.setImageBitmap(bitmap)
            txtNombre.text = medicamento.nombre
            txtDosis.text = "Tomar: " + medicamento.dosis
            txtHorario.text = "Tomar cada: " + medicamento.horario
        }


    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun areAllItemsEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(position: Int): Boolean {
        TODO("Not yet implemented")
    }

}