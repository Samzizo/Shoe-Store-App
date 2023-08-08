package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.databinding.FragmentShoItemBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel
import kotlinx.coroutines.NonDisposableHandle.parent


class ShoeListFragment : Fragment() {
    private val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.AddNewShoe.setOnClickListener{
            view?.findNavController()?.navigate(
                ShoeListFragmentDirections.actionShoeListFragmentToShoDetailFragment())
        }

        viewModel.shoeList.observe(this.viewLifecycleOwner, Observer { shoeList->
            for (shoe in shoeList) {
                addView(binding.shoeList, shoe)
            }
        })
        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)

        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        view?.findNavController()?.navigate(ShoeListFragmentDirections
            .actionShoeListFragmentToLoginFragment())
        return true
    }

    private fun addView(parent: ViewGroup?, shoe: Shoe){
        val shoeBinding: FragmentShoItemBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_sho_item, parent, false)

        shoeBinding.shoe = shoe
        binding.shoeList.addView(shoeBinding.root)
    }
}