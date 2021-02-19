package com.udacity.shoestore.screens.addshoe

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class AddShoeViewModel : ViewModel() {

    val shoeName: MutableLiveData<String> = MutableLiveData()
    val shoeCompany: MutableLiveData<String> = MutableLiveData()
    val shoeSize: MutableLiveData<String> = MutableLiveData()
    val shoeDescription: MutableLiveData<String> = MutableLiveData()
    val shoeImage: MutableLiveData<Bitmap> = MutableLiveData()

    fun getShoe() : Shoe {
        return Shoe(
            name = shoeName.value ?: "",
            size = shoeSize.value ?: "" ,
            company = shoeCompany.value ?: "",
            description = shoeDescription.value ?: "",
            image = shoeImage.value
        )
    }

    fun onImage(bitmap: Bitmap){
        shoeImage.value = bitmap
    }
}