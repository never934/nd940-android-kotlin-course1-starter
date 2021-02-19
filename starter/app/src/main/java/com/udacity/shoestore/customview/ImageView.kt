package com.udacity.shoestore.customview

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.base.BaseCustomView
import com.udacity.shoestore.databinding.ItemImageViewBinding

class ImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    BaseCustomView(context, attrs, defStyleAttr) {

    private lateinit var binding: ItemImageViewBinding

    init {
        init(attrs, context)
    }

    private fun init(attrs: AttributeSet?, context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.item_image_view, this, true)
    }

    fun setImage(bitmap: Bitmap?) {
        bitmap?.let {
            binding.imageView.setImageBitmap(it)
        }
    }

    fun getClickZone() : ImageView{
        return binding.imageView
    }
}