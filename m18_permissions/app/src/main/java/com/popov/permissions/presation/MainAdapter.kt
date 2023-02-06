package com.popov.permissions.presation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.popov.permissions.databinding.PhotoListItemBinding
import com.popov.permissions.entity.Photo

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var values: List<Photo> = emptyList()

    fun setData(values: List<Photo>) {
        this.values = values
        Log.d("AAA", "ViewHolderList= $values")
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding =
            PhotoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = values.getOrNull(position)
        Log.d("AAA", "ViewHolderItem= $item")
        with(holder.binding) {
            Log.d("AAA", "ViewHolderDate= ${item?.date}")
            dateText.text = item?.date ?: ""
            Log.d("AAA", "ViewHolderPath = ${item?.path}")
            item?.let {
                Glide.with(photoImage.context).load(it.path).into(photoImage)
            }
        }
    }

    override fun getItemCount(): Int = values.size

}

class MainViewHolder(val binding: PhotoListItemBinding) : RecyclerView.ViewHolder(binding.root)