package com.example.assignment1.ui.favourite
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment1.databinding.ActivityMainBinding

class FavouriteFragment : Fragment() {
    private var _binding:  ActivityMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ViewModel =
            ViewModelProvider(this).get(FavouriteViewModel::class.java)

        _binding = ActivityMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        ViewModel.getAllRecords().observe(viewLifecycleOwner){
            binding.recyclerView.adapter = RvAdapter(it)
        }

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

