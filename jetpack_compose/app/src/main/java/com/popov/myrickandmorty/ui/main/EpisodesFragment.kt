package com.popov.myrickandmorty.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.popov.myrickandmorty.ui.theme.ColorRickAndMorty


class EpisodesFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        val episodesList : EpisodesFragmentArgs by navArgs()
        setContent {
            EpisodesItemView(episodes = episodesList.myArg2.toList())
        }
    }
}

@Composable
private fun EpisodesItemView(episodes: List<String>){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(ColorRickAndMorty)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Episodes", color = Color.White, fontSize = 24.sp)
        }
        LazyColumn {
            itemsIndexed(episodes){index, item ->
                Text(text = item)
            }
        }
    }
}