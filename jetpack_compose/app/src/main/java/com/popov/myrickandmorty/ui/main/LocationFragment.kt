package com.popov.myrickandmorty.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class LocationFragment : Fragment() {
    private val viewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
       setContent {
          LazyColumn{
              itemsIndexed(
                  viewModel.pagedLocation
              ){ index, item ->
                    LocationItemView(result = item)
              }
          }
       }
    }
}


@Composable
private fun LocationItemView(result: com.popov.myrickandmorty.data.Result) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Row(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()) {
            Text(text = result.name)
        }
        Row(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()) {
            Text(text = result.dimension)
        }
        Row(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()) {
            Text(text = result.created)
        }
    }
}