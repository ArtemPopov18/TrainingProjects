package com.popov.myrickandmorty.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.popov.myrickandmorty.databinding.ItemListCharacterBinding

class CharacterPagedListAdapter : PagingDataAdapter<com.popov.myrickandmorty.data.Character, CharacterViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemListCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            characterText.text = "${item?.name}\n ${item?.status} - ${item?.species}\n ${item?.origin?.name} "
            item?.let {
                Glide
                    .with(characterImage.context)
                    .load(it.image)
                    .into(characterImage)
            }
        }
    }

}

class DiffUtilCallback : DiffUtil.ItemCallback<com.popov.myrickandmorty.data.Character>() {
    override fun areItemsTheSame(oldItem: com.popov.myrickandmorty.data.Character, newItem: com.popov.myrickandmorty.data.Character): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: com.popov.myrickandmorty.data.Character, newItem: com.popov.myrickandmorty.data.Character): Boolean = oldItem == newItem
}

class CharacterViewHolder(val binding: ItemListCharacterBinding) : RecyclerView.ViewHolder(binding.root)