package com.udacity.shoestore.utils

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment

class AppsUtils {
    companion object{
        fun getPhotoFromGallery(context: Fragment, code: Int){
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            context.startActivityForResult(photoPickerIntent, code)
        }
    }
}