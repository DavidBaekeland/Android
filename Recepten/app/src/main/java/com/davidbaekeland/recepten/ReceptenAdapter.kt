package com.davidbaekeland.recepten

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.davidbaekeland.recepten.models.Recept
import com.squareup.picasso.Picasso

// Code afkomstig van lessen
class ReceptenAdapter(var recepten: MutableList<Recept>) : RecyclerView.Adapter<ReceptenAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView
        val imgUrl: ImageView
        val button: Button

        init {
            txtTitle = view.findViewById(R.id.txt_name)
            imgUrl = view.findViewById(R.id.img)
            button = view.findViewById(R.id.img_favorite)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recept_item, parent, false)

        return ViewHolder(view)
    }


    // https://square.github.io/picasso/
    // https://github.com/square/picasso
    // https://www.codegrepper.com/code-examples/whatever/picasso+android+kotlin
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text = recepten[position].title
        holder.button.id = recepten[position].id
        Picasso.get().load(recepten[position].image).resize(900, 470).centerCrop().into(holder.imgUrl)

    }

    override fun getItemCount(): Int {
        return recepten.size
    }
}