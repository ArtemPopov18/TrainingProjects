package com.popov.castomview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.popov.castomview.databinding.MyCustomViewBinding

class MyCustomView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val binding = MyCustomViewBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun setTextTop(text: String) {
        binding.topText.text = text
    }

    fun setTextBottom(text: String) {
        binding.bottomText.text = text
    }
}