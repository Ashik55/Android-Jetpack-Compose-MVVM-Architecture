package com.eggroup.composearch.ui.screens.list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eggroup.composearch.data.remote.Post
import com.eggroup.composearch.network.ApiState
import com.eggroup.composearch.ui.screens.Destinations


@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top news") },
            )
        }
    ) {
        when (val result = viewModel.response.value) {
            is ApiState.Success -> {
                LazyColumn {
                    items(result.data) { response ->
                        EachRow(navController, response)
                    }
                }
            }
            is ApiState.Failure -> {
                Text(text = "${result.msg}")
            }
            ApiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize() ){
                    CircularProgressIndicator()
                }

            }
            ApiState.Empty -> {

            }
        }

    }



}

@Composable
fun EachRow(navController: NavController, post: Post) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("${Destinations.DETAILS_SCREEN}/${post.title}")
            },
        elevation = 2.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(text = post.body!!, modifier = Modifier.padding(10.dp), fontStyle = FontStyle.Italic)
    }
}

