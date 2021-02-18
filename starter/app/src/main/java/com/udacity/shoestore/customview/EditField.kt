package com.udacity.shoestore.customview

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.udacity.shoestore.R

class EditField @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    TextInputLayout(context, attrs, defStyleAttr) {

    private lateinit var editTextInputLayout: TextInputLayout
    private lateinit var editText: TextInputEditText

    init {
        init(attrs, context)
    }

    private fun init(attrs: AttributeSet?, context: Context) {
        val view = View.inflate(context, R.layout.item_edit_field, this)
        editTextInputLayout = view.findViewById(R.id.editTextInputLayout)
        editText = view.findViewById(R.id.editText) as TextInputEditText

        val ta = context.obtainStyledAttributes(attrs, R.styleable.EditField)
        val hint = ta.getString(R.styleable.EditField_hint)
        val digits = ta.getString(R.styleable.EditField_digits)
        val inputType = ta.getString(R.styleable.EditField_inputType)
        val isNextImeOptions = ta.getBoolean(R.styleable.EditField_isNextIme, false)
        val isDoneImeOptions = ta.getBoolean(R.styleable.EditField_isDoneIme, false)

        try {
            if(isNextImeOptions)editText.imeOptions = EditorInfo.IME_ACTION_NEXT else editText.imeOptions = EditorInfo.IME_ACTION_DONE
            if(isDoneImeOptions)editText.imeOptions = EditorInfo.IME_ACTION_DONE
            hint?.let { editTextInputLayout.hint = it }
            digits?.let { editText.keyListener = DigitsKeyListener.getInstance(it) }
            when(inputType){
                "number" -> editText.inputType = InputType.TYPE_CLASS_NUMBER
                "text" -> editText.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            }
        } finally {
            ta.recycle()
        }
    }
}