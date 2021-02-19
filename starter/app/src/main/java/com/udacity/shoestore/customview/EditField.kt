package com.udacity.shoestore.customview

import android.content.Context
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.base.BaseCustomView
import com.udacity.shoestore.databinding.ItemEditFieldBinding

class EditField @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    BaseCustomView(context, attrs, defStyleAttr) {

    private lateinit var binding: ItemEditFieldBinding

    init {
        init(attrs, context)
    }

    private fun init(attrs: AttributeSet?, context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.item_edit_field, this, true)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.EditField)
        val hint = ta.getString(R.styleable.EditField_hint)
        val digits = ta.getString(R.styleable.EditField_digits)
        val inputType = ta.getString(R.styleable.EditField_inputType)
        val isNextImeOptions = ta.getBoolean(R.styleable.EditField_isNextIme, false)
        val isDoneImeOptions = ta.getBoolean(R.styleable.EditField_isDoneIme, false)

        try {
            if(isNextImeOptions)binding.editText.imeOptions = EditorInfo.IME_ACTION_NEXT else binding.editText.imeOptions = EditorInfo.IME_ACTION_DONE
            if(isDoneImeOptions)binding.editText.imeOptions = EditorInfo.IME_ACTION_DONE
            hint?.let { binding.editTextInputLayout.hint = it }
            when(inputType){
                "number" -> binding.editText.inputType = InputType.TYPE_CLASS_NUMBER
                "text" -> binding.editText.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                "password" -> {
                    binding.editText.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                    binding.editText.transformationMethod = PasswordTransformationMethod()
                }
            }
            digits?.let { binding.editText.keyListener = DigitsKeyListener.getInstance(it) }
        } finally {
            ta.recycle()

        }
    }

    fun getText() : String {
        return binding.editText.text.toString()
    }

    fun setText(text: String?){
        text?.let {
            binding.editText.setText(it, TextView.BufferType.EDITABLE)
        }
    }

    fun getEditText() : EditText{
        return binding.editText
    }
}