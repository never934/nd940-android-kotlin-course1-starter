package com.udacity.shoestore.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.udacity.shoestore.R
import com.udacity.shoestore.base.BaseCustomView
import com.udacity.shoestore.models.Shoe

class ShoeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, private val shoe: Shoe?) :
    BaseCustomView(context, attrs, defStyleAttr) {

    private lateinit var imageView: ImageView
    private lateinit var shoeName: TextView
    private lateinit var shoeCompany: TextView
    private lateinit var shoeSize: TextView
    private lateinit var shoeDescription: TextView

    init {
        init(attrs, context)
    }

    private fun init(attrs: AttributeSet?, context: Context) {
        val view = View.inflate(context, R.layout.item_shoe, this)
        imageView = view.findViewById(R.id.shoeImageView) as ImageView
        shoeName = view.findViewById(R.id.shoeNameView) as TextView
        shoeCompany = view.findViewById(R.id.shoeCompanyView) as TextView
        shoeSize = view.findViewById(R.id.shoeSizeView) as TextView
        shoeDescription = view.findViewById(R.id.shoeDescriptionView) as TextView
        shoe?.let { setShoe(it) }
    }

    private fun setShoe(shoe: Shoe){
        shoeName.text = String.format(context.getString(R.string.shoe_name), shoe.name)
        shoeCompany.text = String.format(context.getString(R.string.shoe_company), shoe.company)
        shoeSize.text = String.format(context.getString(R.string.shoe_size), shoe.size ?: "")
        shoeDescription.text = String.format(context.getString(R.string.shoe_description), shoe.description)
        imageView.setImage(shoe.image)
    }
}