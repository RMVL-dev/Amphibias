package com.example.amphibias.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibias.R
import com.example.amphibias.data.AmphibiansModel
import com.example.amphibias.ui.viewmodels.AmphibiansViewModel
import com.example.amphibias.ui.viewmodels.interfaces.AmphibiansState

@Composable
fun AmphibiansApp(){
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = { 
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                }
            )
        }
    ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            val amphibiansViewModel: AmphibiansViewModel = viewModel(
                factory = AmphibiansViewModel.Factory
            )
            HomeScreen(
                amphibiansState = amphibiansViewModel.uiState,
                retryClick = {amphibiansViewModel.getAmphibiansData()}
            )
        }
    }

}



@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    amphibiansState: AmphibiansState,
    retryClick: () -> Unit
){
    when(amphibiansState){
        is AmphibiansState.Error -> {
            AmphibiansErrorScreen(modifier = modifier, retryClick = retryClick)
        }
        is AmphibiansState.Success -> {
            AmphibiansList(amphibians = amphibiansState.amphibians)
        }
        is AmphibiansState.Loading -> {
            LoadingScreen(modifier = modifier)
        }
    }
}


@Composable
fun AmphibiansErrorScreen(
    modifier: Modifier = Modifier,
    retryClick: () -> Unit
){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.error))
        Button(onClick = retryClick) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
){
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = stringResource(id = R.string.loading))
    }
}


@Composable
fun AmphibiansList(
    modifier: Modifier = Modifier,
    amphibians: List<AmphibiansModel>
){
    LazyColumn(
        modifier = modifier
    ){
        items(amphibians){
            item: AmphibiansModel ->
            AmphibiansCard(amphibian = item)
        }
    }
}



@Composable
fun AmphibiansCard(
    modifier: Modifier = Modifier,
    amphibian: AmphibiansModel
){
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = amphibian.name
                )
                Text(
                    text = "("+amphibian.type+")"
                )
            }
            Text(
                text = amphibian.description
            )
            AmphibiansImage(
                amphibian = amphibian,
                modifier = modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun AmphibiansImage(
    modifier: Modifier = Modifier,
    amphibian: AmphibiansModel
){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(amphibian.imgSrc)
            .crossfade(true)
            .build(),
        contentDescription = amphibian.name,
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(id = R.drawable.loading_img),
        error = painterResource(id = R.drawable.ic_broken_image),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AmphibiansCardPreview(){

}