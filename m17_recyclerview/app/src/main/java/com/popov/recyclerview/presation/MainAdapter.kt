package com.popov.recyclerview.presation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.popov.recyclerview.data.MarsList
import com.popov.recyclerview.databinding.MarsListItemBinding


class MainAdapter() : RecyclerView.Adapter<MainViewHolder>() {
    private var values: List<MarsList> = emptyList()

    fun setData(values: List<MarsList>) {
        this.values = values
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MarsListItemBinding.inflate(LayoutInflater.from(parent.context))
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = values[position].photos
        with(holder.binding) {
            dateText.text = item[position].earth_date
            solText.text = item[position].sol.toString()
            cameraText.text = item[position].camera.name
            item?.let {
                Glide
                    .with(marsImage.context)
                    .load(it[position].img_src)
                    .into(marsImage)
            }
        }
    }

    override fun getItemCount(): Int = values.size
}

class MainViewHolder(val binding: MarsListItemBinding) : RecyclerView.ViewHolder(binding.root)