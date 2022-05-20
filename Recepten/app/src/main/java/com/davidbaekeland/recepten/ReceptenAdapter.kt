package com.davidbaekeland.recepten

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.davidbaekeland.recepten.models.Recept

// Code afkomstig van lessen
class ReceptenAdapter(private val recepten: MutableList<Recept>) : RecyclerView.Adapter<ReceptenAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView
        val txtUrl: ImageView

        init {
            txtTitle = view.findViewById(R.id.txt_name)
            txtUrl = view.findViewById(R.id.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recept_item, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text = recepten[position].name
    }

    override fun getItemCount(): Int {
        return recepten.size
    }
}