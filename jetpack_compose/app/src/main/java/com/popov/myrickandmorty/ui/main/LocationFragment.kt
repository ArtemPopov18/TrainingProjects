package com.popov.myrickandmorty.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed

class LocationFragment : Fragment() {
    private val viewModel: LocationViewModel by viewModels()

    companion object {
        fun newInstance() = LocationFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
       setContent {
          LocationAll(viewModel = viewModel)
       }
    }
}

@Composable
fun LocationAll(viewModel: LocationViewModel){
    val locationList = viewModel.pagedLocation.collectAsLazyPagingItems()
    
    LazyColumn {
        itemsIndexed(locationList){ index, item ->
            LocationItemView(result = item!!)
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