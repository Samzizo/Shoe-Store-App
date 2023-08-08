package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding



class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        binding.signIn.setOnClickListener {
            if (binding.email.text.isNullOrEmpty() || binding.password.text.isNullOrEmpty()){
                Toast.makeText(context, "Please enter email and password",
                    Toast.LENGTH_LONG).show()
            } else {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                )
        }
        }
        binding.signUp.setOnClickListener {
            if (binding.email.text.isNullOrEmpty() || binding.password.text.isNullOrEmpty())
            {
            Toast.makeText(context, "Please enter email and password",
                Toast.LENGTH_LONG).show()
        } else {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
            )
        }
        }
        return binding.root
    }



}