package com.ozancanguz.distancetrackerapp.ui.fragments.permission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ozancanguz.distancetrackerapp.R
import com.ozancanguz.distancetrackerapp.databinding.FragmentPermissionBinding


class PermissionFragment : Fragment() {
    private var _binding: FragmentPermissionBinding? = null

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         _binding = FragmentPermissionBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }


}