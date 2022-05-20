package com.davidbaekeland.recepten.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.davidbaekeland.recepten.databinding.FragmentHomeBinding
import com.davidbaekeland.recepten.models.Recept

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

//        data()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    fun data() {
//        val receptenService = ReceptenService.create().getPasta()
//
//
//        receptenService.enqueue( object : Callback<List<Recept>> {
//            override fun onResponse(
//                call: Call<List<Recept>>,
//                response: retrofit2.Response<List<Recept>>
//            ) {
//                Log.d("qsdf", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<List<Recept>>?, t: Throwable?) {
//                Log.d("q", "error")
//            }
//        })
//    }
}