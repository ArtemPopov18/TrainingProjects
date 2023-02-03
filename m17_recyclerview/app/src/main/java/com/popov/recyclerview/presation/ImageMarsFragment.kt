package com.popov.recyclerview.presation

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.popov.recyclerview.databinding.FragmentImageMarsBinding


class ImageMarsFragment : Fragment() {

    private var _binding: FragmentImageMarsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString("MyArg")
        Log.d("AAAA", url.toString())
        Glide.with(requireActivity())
            .load(url)
//            .listener(object : RequestListener<Drawable?>{
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable?>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    Log.e("TAG", "Error loading image", e)
//                    return false // important to return false so the error placeholder can be placed
//                }
//
//                override fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: Target<Drawable?>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    return false
//                }
//
//            })
            .into(binding.imageMarsView)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}