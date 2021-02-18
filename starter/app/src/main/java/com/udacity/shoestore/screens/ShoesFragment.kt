package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.customview.ShoeView
import com.udacity.shoestore.databinding.LoginFragmentBinding
import com.udacity.shoestore.databinding.ShoesFragmentBinding

class ShoesFragment : Fragment() {

    private lateinit var binding: ShoesFragmentBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var shoesOnScreen = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoes_fragment,
            container,
            false
        )
        setHasOptionsMenu(true)
        viewModel.shoes.observe(this, Observer {
            it.forEach { shoeItem ->
                val shoe = ShoeView(requireContext(), null, 0, shoeItem)
                binding.shoesLayout.addView(shoe)
            }
        })
        binding.shoeAddFab.setOnClickListener { findNavController().navigate(ShoesFragmentDirections.actionShoesFragmentToAddShoeFragment()) }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.loginFragment -> {
                viewModel.clean()
                findNavController().navigate(ShoesFragmentDirections.actionShoesFragmentToLoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}