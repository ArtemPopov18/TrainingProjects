package com.popov.castomview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.popov.castomview.databinding.MyCustomViewBinding

class MyCustomView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

//    val binding = MyCustomViewBinding.inflate(LayoutInflater.from(context))
    val binding: MyCustomViewBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.my_custom_view, this, true)
        binding = MyCustomViewBinding.bind(this)
//        addView(binding.root)
    }

    fun setTextTop(text: String) {
        binding.topText.text = text
    }

    fun setTextBottom(text: String) {
        binding.bottomText.text = text
    }
}