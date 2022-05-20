package com.davidbaekeland.recepten.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davidbaekeland.recepten.ReceptenAdapter
import com.davidbaekeland.recepten.databinding.FragmentNotificationsBinding
import com.davidbaekeland.recepten.models.Recept
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        data()

        return root
    }

    fun data() {

        // Code afkomstig van lessen en FireStore
        var db = FirebaseFirestore.getInstance()
        val recyclerView: RecyclerView = binding.receptReycler
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


        val receptenAdapter = ReceptenAdapter(receptenLijst)
        recyclerView.adapter = receptenAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}