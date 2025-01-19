package com.example.my_soothe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home.R

@Composable
fun MySootheApp() {
    val data = listOf("", "", "", "", "", "", "", "", "")

    Scaffold(
        bottomBar = { SootheBottomNavigation(modifier = Modifier) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            SearchBar(modifier = Modifier)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(data) { item ->
                    AlignYourBodyElement(modifier = Modifier)
                }

            }
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.height(168.dp)
            ) {
                items(data) {
                    FavoriteCollectionCard(modifier = Modifier)

                }
            }

        }
    }


}


@Composable
fun SearchBar(modifier: Modifier) {
    var searchKeyword by remember { mutableStateOf("") }
    TextField(
        value = searchKeyword,
        onValueChange = {
            searchKeyword = it
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(16.dp),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(
            errorTrailingIconColor = Color.Red,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.Gray,
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Cyan,
        ),
        placeholder = {
            Text(text = "Search")
        }
    )
}

@Composable
fun AlignYourBodyElement(modifier: Modifier) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = null,
            modifier
                .size(88.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.padding(horizontal = 10.dp))
        Text(
            text = "Inversion",
            modifier = modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )

    }
}

@Composable
fun FavoriteCollectionCard(modifier: Modifier) {

    Box(
        modifier = modifier
            .width(255.dp)
            .heightIn(min = 56.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.LightGray)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
    ) {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = null,
                modifier
                    .size(88.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier.padding(start = 20.dp))
            Text(
                text = "Inversion",
                modifier = modifier
                    .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodyMedium,
//                fontSize = TextUnit(16.sp, TextUnitType.Sp)
            )
        }
    }
}

@Composable
fun SootheBottomNavigation(modifier: Modifier) {
    NavigationBar(modifier = modifier, containerColor = MaterialTheme.colorScheme.background) {
        NavigationBarItem(
            icon = {
                Icon(Icons.Default.Home, contentDescription = null)
            },
            label = {
                Text("Home")
            },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(Icons.Default.AccountCircle, contentDescription = null)
            },
            label = {
                Text("Profile")
            },
            selected = false,
            onClick = {}
        )
    }
}