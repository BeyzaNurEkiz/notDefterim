package com.example.notdefterim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.notdefterim.presentation.bookmark.BookmarkViewModel
import com.example.notdefterim.presentation.detail.DetailAssistedFactory
import com.example.notdefterim.presentation.home.HomeViewModel
import com.example.notdefterim.presentation.navigation.NoteNavigation
import com.example.notdefterim.presentation.navigation.Screens
import com.example.notdefterim.presentation.navigation.navigateToSingleTop
import com.example.notdefterim.ui.theme.NotDefterimTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var assistedFactory: DetailAssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotDefterimTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color= MaterialTheme.colorScheme.background,
                    contentColor =MaterialTheme.colorScheme.onSurface
                ){
                    NoteApp()
                }
            }
        }
    }

    @Composable
    fun NoteApp() {
        val homeViewModel:HomeViewModel = viewModel()
        val bookmarkViewModel:BookmarkViewModel= viewModel()
        val navController= rememberNavController()
        var currentTab by remember{
            mutableStateOf(TabScreen.Home)
        }
        Scaffold (
            bottomBar = {
                BottomAppBar (
                    actions= {
                        Row (
                            horizontalArrangement = Arrangement.Center
                        ){
                            InputChip(
                                selected = currentTab==TabScreen.Home,
                                onClick = {
                                          currentTab= TabScreen.Home
                                    navController.navigateToSingleTop(
                                        route = Screens.Home.name
                                    )
                                },
                                label = {
                                    Text("Anasayfa")
                                },
                                trailingIcon = {
                                    Icon(imageVector= Icons.Default.Home, contentDescription= null,)
                                }
                            )
                            Spacer(modifier = Modifier.Companion.size(12.dp))
                            InputChip(
                                selected = currentTab==TabScreen.Home,
                                onClick = {
                                    currentTab= TabScreen.BookMark
                                    navController.navigateToSingleTop(
                                        route = Screens.Bookmark.name
                                    )
                                },
                                label = {
                                    Text("Kaydedilenler")
                                },
                                trailingIcon = {
                                    Icon(imageVector= Icons.Default.Bookmark, contentDescription= null,)
                                }
                            )
                        }

                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { navController.navigateToSingleTop(route = Screens.Detail.name) }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }
                    }
                )
            }
        ){
            NoteNavigation(
                modifier = Modifier.padding(it),
                navHostController = navController,
                homeViewModel = homeViewModel,
                bookmarkViewModel = bookmarkViewModel,
                assistedFactory = assistedFactory
            )
        }
    }
}

enum class TabScreen{
    Home,BookMark
}

