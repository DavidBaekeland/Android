package com.davidbaekeland.recepten

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.davidbaekeland.recepten.models.Recept
import com.davidbaekeland.recepten.models.Repository
import com.google.firebase.firestore.FirebaseFirestore
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


    // https://stackoverflow.com/questions/2173936/how-to-set-background-color-of-a-view
    // https://square.github.io/picasso/
    // https://github.com/square/picasso
    // https://www.codegrepper.com/code-examples/whatever/picasso+android+kotlin
    // https://www.youtube.com/watch?v=FA5cGLLiSWs&t=219s
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text = recepten[position].title
        Picasso.get().load(recepten[position].image).resize(900, 480).centerCrop().into(holder.imgUrl)

        Log.d("test", recepten[position].title)

        var repository = Repository()

        var db = FirebaseFirestore.getInstance()
        var recept = db.collection("favorieten").document(recepten[position].id.toString())
        recept.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    holder.button.setBackgroundResource(R.drawable.ic_favorite2)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }


        holder.button.setOnClickListener {
            holder.button.setBackgroundResource(R.drawable.ic_favorite2)
            repository.addData(recepten[position])
        }
    }

    override fun getItemCount(): Int {
        return recepten.size
    }
}