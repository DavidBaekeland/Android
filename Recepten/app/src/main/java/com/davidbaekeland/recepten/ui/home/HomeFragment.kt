package com.davidbaekeland.recepten.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.davidbaekeland.recepten.R
import com.davidbaekeland.recepten.databinding.FragmentHomeBinding
import com.davidbaekeland.recepten.models.Recept
import com.davidbaekeland.recepten.ui.dashboard.DashboardFragment

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

        // https://romannurik.github.io/AndroidAssetStudio/nine-patches.html#&sourceDensity=320&name=example
        // https://developer.android.com/guide/navigation/navigation-navigate
        // https://developer.android.com/guide/navigation/navigation-pass-data
        var breakfast: CardView = binding.cvOntbijt
        filter(breakfast, "breakfast")

        var lunch: CardView = binding.cvLunch
        filter(lunch, "lunch")

        var dinner: CardView = binding.cvDinner
        filter(dinner, "dinner")

        var pasta: CardView = binding.cvPasta
        filter(pasta, "pasta")

        var oosters: CardView = binding.cvOosters
        filter(oosters, "asian")

        var smoothies: CardView = binding.cvSmooties
        filter(smoothies, "smoothies")

        var veggie: CardView = binding.cvVeggie
        filter(veggie, "veggie")

        var fish: CardView = binding.cvVis
        filter(fish, "fish")

        var meat: CardView = binding.cvVlees
        filter(meat, "meat")

        var lactose: CardView = binding.cvLactose
        filter(lactose, "lactose")

        var nuts: CardView = binding.cvNoten
        filter(nuts, "nuts")

        var soja: CardView = binding.cvSoja
        filter(soja, "soja")

        return root
    }

    // https://developer.android.com/guide/navigation/navigation-navigate
    // https://developer.android.com/guide/navigation/navigation-pass-data
    fun filter(cardView: CardView, filter: String) {
        cardView.setOnClickListener { view ->
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationNotifications(filter)
            view.findNavController().navigate(action)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}