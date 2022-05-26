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
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.davidbaekeland.recepten.ReceptenAdapter
import com.davidbaekeland.recepten.databinding.FragmentNotificationsBinding
import com.davidbaekeland.recepten.models.Recept
import com.google.gson.Gson


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


        val textView: TextView = binding.txtTest
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val queue = Volley.newRequestQueue(this.context)
        val url = "https://api.spoonacular.com/recipes/complexSearch?apiKey=48bfb05800e64a8d86c75214ef3d66ff&cuisine=italian&query=pasta"


        var receptlijst: MutableList<Recept> = mutableListOf(Recept(5, "https://spoonacular.com/recipeImages/654959-312x231.jpg", "Pasta With Tuna"), Recept(8, "https://spoonacular.com/recipeImages/511728-312x231.jpg", "Pasta Margherita"))

        val stringRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val results = response.getJSONArray("results")
//                        textView.text = results[0].toString()
                for (result in 0..5) {
                    var test2= results[result].toString()
                    var gson = Gson()
                    var recept = gson.fromJson(test2, Recept::class.java)
                    Log.d("response", results.toString())
                    receptlijst.add(recept)
                }

                val recyclerView: RecyclerView = binding.receptReycler
                recyclerView.layoutManager = LinearLayoutManager(this.context)
                recyclerView.adapter = ReceptenAdapter(receptlijst)

            },
            Response.ErrorListener { textView.text = "That didn't work!" })

        queue.add(stringRequest)


        return root
    }




    // https://www.baeldung.com/kotlin/json-convert-data-class
    // https://github.com/google/gson/blob/master/UserGuide.md
    // https://github.com/google/gson
    // https://stackoverflow.com/questions/68463691/android-kotlin-volley-how-to-get-value-from-jsonarray

//    fun data() {
//
//    }
}