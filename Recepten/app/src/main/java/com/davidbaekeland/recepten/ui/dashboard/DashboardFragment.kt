package com.davidbaekeland.recepten.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davidbaekeland.recepten.ReceptenAdapter
import com.davidbaekeland.recepten.databinding.FragmentDashboardBinding
import com.davidbaekeland.recepten.models.Recept
import com.davidbaekeland.recepten.models.Repository
import com.davidbaekeland.recepten.ui.home.HomeFragmentDirections
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()

        var db = FirebaseFirestore.getInstance()


        // Code afkomstig van lessen en FireStore
        // https://stackoverflow.com/questions/38802269/firebase-user-is-missing-a-constructor-with-no-arguments
        var favoriteList: MutableList<Recept> = mutableListOf()

        val docRef = db.collection("recepten")
        docRef.get()
            .addOnSuccessListener { document ->

                for (result in document) {
                    val recept = result.toObject<Recept>()
                    favoriteList.add(recept)
                    Log.d("TAG", "${favoriteList}")
                }


                val recyclerView: RecyclerView = binding.favoriteReycler
                recyclerView.layoutManager = LinearLayoutManager(this.context)
                recyclerView.adapter = ReceptenAdapter(favoriteList)

            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }
}