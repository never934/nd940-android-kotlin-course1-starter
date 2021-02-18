package com.udacity.shoestore

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.utils.ImagesCacheController
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel : ViewModel() {

    private val _shoes = MutableLiveData<ArrayList<Shoe>>()
    val shoes: LiveData<List<Shoe>>
    get() = Transformations.map(_shoes) {
        it.toList()
    }

    private val imagesController: ImagesCacheController = ImagesCacheController()

    init {
        _shoes.value = ArrayList()
    }

    fun addShoe(shoe: Shoe, image: Bitmap?){
        image?.let {
            val imageId = UUID.randomUUID().toString()
            imagesController.saveBitmap(imageId, it)
            shoe.imageId = imageId
        }
        _shoes.value?.add(shoe)
    }

    fun clean(){
        _shoes.value?.clear()
    }

    override fun onCleared() {
        super.onCleared()
        imagesController.clean()
    }
}