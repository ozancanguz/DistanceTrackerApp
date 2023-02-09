package com.ozancanguz.distancetrackerapp.ui.fragments.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ozancanguz.distancetrackerapp.R
import com.ozancanguz.distancetrackerapp.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


}