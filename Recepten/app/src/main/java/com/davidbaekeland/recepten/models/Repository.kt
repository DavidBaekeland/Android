package com.davidbaekeland.recepten.models

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davidbaekeland.recepten.ReceptenAdapter

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository {

    var test: Flow<MutableList<Recept>> = flow { data() }
    var db = FirebaseFirestore.getInstance()
    var check: Boolean = false


    fun data(): MutableList<Recept> {
        // Code afkomstig van lessen en FireStore
        var receptenLijst: MutableList<Recept> = mutableListOf()

        val docRef = db.collection("italiaans")
        docRef.get()
            .addOnSuccessListener { document ->

                for (result in document) {
                    val recept = result.toObject<Recept>()
                    receptenLijst.add(recept)

                }

                Log.d("TAG", " ${receptenLijst}")

            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }

        return receptenLijst
    }

    fun addData(recept: Recept) {
        db.collection("recepten").document(recept.id.toString())
            .set(recept, SetOptions.merge())
    }
}