package com.udacity.shoestore.screens

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.AddShoeFragmentBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.add_shoe_fragment.*
import kotlinx.android.synthetic.main.item_shoe.*

class AddShoeFragment : Fragment() {

    private lateinit var binding: AddShoeFragmentBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var bitmap: Bitmap? = null
    private lateinit var galleryResult: ActivityResultLauncher<Intent>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_shoe_fragment,
            container,
            false
        )
        galleryResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val photoUri = result.data?.data
                photoUri?.let {
                    val imageStream = requireActivity().contentResolver.openInputStream(it)
                    val photo = BitmapFactory.decodeStream(imageStream)
                    bitmap = photo
                    addShoeImageView.setImageBitmap(bitmap)
                }
            }
        }
        binding.addShoeImageView.setOnClickListener {
            startGallery()
        }
        binding.addShoeButton.setOnClickListener {
            val shoe = Shoe(
                name = binding.addShoeNameField.getText(),
                company = binding.addShoeCompanyField.getText(),
                size = binding.addShoeSizeField.getText().toDoubleOrNull(),
                description = binding.addShoeDescriptionField.getText(),
                image = bitmap
            )
            viewModel.addShoe(shoe)
            findNavController().navigate(AddShoeFragmentDirections.actionAddShoeFragmentToShoesFragment())
        }
        return binding.root
    }

    private fun startGallery(){
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        galleryResult.launch(photoPickerIntent)
    }
}