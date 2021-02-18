package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.AddShoeFragmentBinding
import com.udacity.shoestore.models.Shoe

class AddShoeFragment : Fragment() {

    private lateinit var binding: AddShoeFragmentBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_shoe_fragment,
            container,
            false
        )
        binding.addShoeButton.setOnClickListener {
            val shoe = Shoe(
                name = binding.addShoeNameField.getText(),
                company = binding.addShoeCompanyField.getText(),
                size = binding.addShoeSizeField.getText().toDoubleOrNull(),
                description = binding.addShoeDescriptionField.getText(),
                imageId = null
            )
            viewModel.addShoe(shoe, null)
            findNavController().navigate(AddShoeFragmentDirections.actionAddShoeFragmentToShoesFragment())
        }
        return binding.root
    }
}