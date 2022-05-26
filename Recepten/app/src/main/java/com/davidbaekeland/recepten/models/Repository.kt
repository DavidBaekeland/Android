package com.davidbaekeland.recepten.models

import android.util.Log

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository {

    var test: Flow<MutableList<Recept>> = flow { data() }

    fun data(): MutableList<Recept> {
        // Code afkomstig van lessen en FireStore
        var db = FirebaseFirestore.getInstance()
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
}