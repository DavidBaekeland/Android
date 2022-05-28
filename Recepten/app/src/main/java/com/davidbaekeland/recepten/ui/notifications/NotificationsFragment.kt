package com.davidbaekeland.recepten.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.ui.window.Dialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.davidbaekeland.recepten.R
import com.davidbaekeland.recepten.ReceptenAdapter
import com.davidbaekeland.recepten.databinding.FragmentNotificationsBinding
import com.davidbaekeland.recepten.models.Recept
import com.davidbaekeland.recepten.ui.home.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    val args: NotificationsFragmentArgs by navArgs()


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


        val filter = args.filter

        val queue = Volley.newRequestQueue(this.context)
        var url = "https://api.spoonacular.com/recipes/complexSearch?apiKey=48bfb05800e64a8d86c75214ef3d66ff&cuisine=italian&query=${filter}"
        when(filter) {
            "nuts" -> url = "https://api.spoonacular.com/recipes/complexSearch?apiKey=48bfb05800e64a8d86c75214ef3d66ff&intolerances=nuts"
            "soja" -> url = "https://api.spoonacular.com/recipes/complexSearch?apiKey=48bfb05800e64a8d86c75214ef3d66ff&intolerances=soja"
            "veggie" -> url = "https://api.spoonacular.com/recipes/complexSearch?apiKey=48bfb05800e64a8d86c75214ef3d66ff&diet=vegetarian"
            "lactose" -> url = "https://api.spoonacular.com/recipes/complexSearch?apiKey=48bfb05800e64a8d86c75214ef3d66ff&diet=lactose"
        }



        var receptlijst: MutableList<Recept> = mutableListOf()

        val stringRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val results = response.getJSONArray("results")
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
            { textView.text = "That didn't work!" })

        queue.add(stringRequest)


        return root
    }


    override fun onStart() {
        super.onStart()
        // https://programmerr47.medium.com/navigate-back-with-navigation-component-6cec37ba6964
        var button: Button = view?.findViewById(R.id.navigation_home) ?: binding.btnTest2
        button.setOnClickListener { view ->
            view.findNavController().popBackStack()
//            view.findNavController().navigate(R.id.action_navigation_home_to_navigation_notifications)
        }
    }


    // https://www.baeldung.com/kotlin/json-convert-data-class
    // https://github.com/google/gson/blob/master/UserGuide.md
    // https://github.com/google/gson
    // https://stackoverflow.com/questions/68463691/android-kotlin-volley-how-to-get-value-from-jsonarray

//    fun data() {
//
//    }
}