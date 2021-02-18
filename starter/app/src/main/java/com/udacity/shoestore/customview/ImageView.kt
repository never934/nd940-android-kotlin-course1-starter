package com.udacity.shoestore.customview

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.udacity.shoestore.R
import com.udacity.shoestore.base.BaseCustomView

class ImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    BaseCustomView(context, attrs, defStyleAttr) {

    private lateinit var imageView: ImageView

    init {
        init(attrs, context)
    }

    private fun init(attrs: AttributeSet?, context: Context) {
        val view = View.inflate(context, R.layout.item_image_view, this)
        imageView = view.findViewById(R.id.imageView) as ImageView
    }

    fun setImage(bitmap: Bitmap?) {
        bitmap?.let {
            imageView.setImageBitmap(it)
        }
    }

    fun getClickZone() : ImageView{
        return imageView
    }
}