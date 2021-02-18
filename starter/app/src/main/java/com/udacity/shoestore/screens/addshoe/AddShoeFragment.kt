package com.udacity.shoestore.screens.addshoe

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.AddShoeFragmentBinding

class AddShoeFragment : Fragment() {

    private lateinit var binding: AddShoeFragmentBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var addShoeViewModel: AddShoeViewModel
    private lateinit var galleryResult: ActivityResultLauncher<Intent>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_shoe_fragment,
            container,
            false
        )
        addShoeViewModel = ViewModelProvider(this).get(AddShoeViewModel::class.java)
        binding.addShoeViewModel = addShoeViewModel
        initGalleryResult()
        binding.addShoeImageView.getClickZone().setOnClickListener {
            startGallery()
        }
        binding.addShoeButton.setOnClickListener {
            viewModel.addShoe(addShoeViewModel.getShoe())
            findNavController().navigate(AddShoeFragmentDirections.actionAddShoeFragmentToShoesFragment())
        }
        return binding.root
    }

    private fun initGalleryResult(){
        galleryResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val photoUri = result.data?.data
                photoUri?.let {
                    val imageStream = requireActivity().contentResolver.openInputStream(it)
                    val photo = BitmapFactory.decodeStream(imageStream)
                    addShoeViewModel.onImage(photo)
                    binding.invalidateAll()
                }
            }
        }
    }

    private fun startGallery(){
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        galleryResult.launch(photoPickerIntent)
    }
}