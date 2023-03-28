package com.popov.myrickandmorty.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.popov.myrickandmorty.data.Character
import com.popov.myrickandmorty.ui.theme.ColorRickAndMorty
import androidx.navigation.fragment.findNavController

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
            CharacterItemView(
                character = character!!,
                findNavController()
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun CharacterItemView(
    character: com.popov.myrickandmorty.data.Character,
    navigator: NavController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        GlideImage(
            model = character.image, contentDescription = null,
            Modifier
                .weight(1.0f)
                .fillMaxSize()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
                .background(ColorRickAndMorty)
        ) {
            Text(
                text = character.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .background(ColorRickAndMorty)
        ) {
            Text(
                text = "Live status:\n${character.status}", color = Color.White,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .background(ColorRickAndMorty)
        ) {
            Text(
                text = "Species and gender:\n${character.species}(${character.gender})",
                color = Color.White,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .background(ColorRickAndMorty)
        ) {
            Text(
                text = "Last known location:\n${character.location.name}", color = Color.White,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .background(ColorRickAndMorty)
        ) {
            Text(
                text = "First seen in:\n${character.episode[0]}", color = Color.White,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .background(ColorRickAndMorty)
                .clickable {
                    val action2 =
                        CharacterFragmentDirections.actionCharacterFragmentToEpisodesFragment(
                            character.episode.toTypedArray()
                        )
                    navigator.navigate(action2)
                }
        ) {
            Text(
                text = "Episodes:\n${character.episode[0]}", fontSize = 12.sp, color = Color.White,
            )
        }
    }
}