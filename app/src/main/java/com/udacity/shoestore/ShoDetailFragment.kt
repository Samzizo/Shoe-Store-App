package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoDetailBinding
import com.udacity.shoestore.models.ShoeListViewModel


class ShoDetailFragment : Fragment() {

    private val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var binding: FragmentShoDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sho_detail, container, false)

        binding.viewModel= viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.CancelButton.setOnClickListener{view: View ->
            view.findNavController().popBackStack()
        }

        binding.SaveButton.setOnClickListener{view: View ->
            if (viewModel.fieldsNotEmpty()){
                viewModel.createShoe()
                viewModel.createShoeComplete()
                // navigate back to shoe list screen
                view.findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Please Fill All Details",
                    Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


}