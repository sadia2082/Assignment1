package com.example.assignment1.ui.addrecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.assignment1.R

class AddRecordFragment : Fragment() {

    companion object {
        fun newInstance() = AddRecordFragment()
    }

    private lateinit var viewModel: AddRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.add_record_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddRecordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}