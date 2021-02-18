package com.udacity.shoestore.utils
import android.graphics.Bitmap
import androidx.core.widget.addTextChangedListener
import androidx.databinding.*
import com.udacity.shoestore.customview.EditField
import com.udacity.shoestore.customview.ImageView

class Converters {
    companion object{

        @JvmStatic
        @BindingAdapter("bindingTextAttrChanged")
        fun setListener(editField: EditField, listener: InverseBindingListener) {
            editField.getEditText().addTextChangedListener {
                listener.onChange()
            }
        }

        @JvmStatic
        @BindingAdapter("bindingText")
        fun setTextValue(editField: EditField, value: String?) {
            editField.setText(value)
        }

        @JvmStatic
        @InverseBindingAdapter(attribute = "bindingText")
        fun getTextValue(editField: EditField): String {
            return editField.getText()
        }

        @JvmStatic
        @BindingAdapter("bindingImage")
        fun setImage(imageView: ImageView, value: Bitmap?) {
            imageView.setImage(value)
        }

    }
}