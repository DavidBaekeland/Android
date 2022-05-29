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

//    var test: Flow<MutableList<Recept>> = flow { data() }
    var db = FirebaseFirestore.getInstance()

    fun addData(recept: Recept) {
        db.collection("favorieten").document(recept.id.toString())
            .set(recept, SetOptions.merge())
    }
}