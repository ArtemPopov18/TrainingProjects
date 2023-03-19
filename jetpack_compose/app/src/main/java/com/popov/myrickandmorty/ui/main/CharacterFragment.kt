package com.popov.myrickandmorty.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.popov.myrickandmorty.data.Character
import com.popov.myrickandmorty.ui.theme.ColorRickAndMorty

class CharacterFragment : Fragment() {



    companion object {
        fun newInstance() = CharacterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        val character = arguments?.getParcelable<com.popov.myrickandmorty.data.Character>("MyArg")
        setContent {
            CharacterItemView(character = character!!)
        }
    }
}

//@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun CharacterItemView(character: com.popov.myrickandmorty.data.Character) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp),
    ) {
//        GlideImage(model = character.image, contentDescription = null)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorRickAndMorty)
        ) {
            Text(
                text = character.name,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorRickAndMorty)
        ) {
            Text(
                text = "Live status\n${character.status}",
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorRickAndMorty)
        ) {
            Text(
                text = "Species and gender\n${character.species}(${character.gender})",
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorRickAndMorty)
        ) {
            Text(
                text = "Last known location\n${character.location.name}",
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorRickAndMorty)
        ) {
            Text(
                text = "Episodes:\n${character.episode[0]}",
            )
        }
    }
}