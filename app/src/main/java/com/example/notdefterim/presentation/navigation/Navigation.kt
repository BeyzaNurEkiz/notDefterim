package com.example.notdefterim.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notdefterim.presentation.bookmark.BookmarkScreen
import com.example.notdefterim.presentation.bookmark.BookmarkViewModel
import com.example.notdefterim.presentation.detail.DetailAssistedFactory
import com.example.notdefterim.presentation.detail.DetailScreen
import com.example.notdefterim.presentation.home.HomeScreen
import com.example.notdefterim.presentation.home.HomeViewModel

enum class Screens{
    Home,Detail,Bookmark
}

@Composable
fun NoteNavigation(
    modifier: Modifier= Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    bookmarkViewModel: BookmarkViewModel,
    assistedFactory: DetailAssistedFactory
) {
    NavHost(
        navController = navHostController,
        startDestination= Screens.Home.name){
        composable(route= Screens.Home.name) {
            val state by homeViewModel.state.collectAsState()
            HomeScreen(
                state = state,
                onBookmarkChange = homeViewModel::onBookMarkChange,
                onDeleteNote = homeViewModel::deleteNote,
                onNoteClicked = {
                    navHostController.navigateToSingleTop(
                        route= "${Screens.Detail.name}?id=$it"
                    )
                }
            )
        }
        composable(route=Screens.Bookmark.name){
            val state by bookmarkViewModel.state.collectAsState()
            BookmarkScreen(
                state = state,
                onBookmarkChange = bookmarkViewModel::onBookmarkChange,
                onDelete =bookmarkViewModel::deleteNote,
                modifier = modifier,
                onNoteClicked = {
                    navHostController.navigateToSingleTop(
                        route= "${Screens.Detail.name}?id=$it"
                    )
                }
            )
        }
        composable(
            route= "${Screens.Detail.name}?id={id}",
            arguments = listOf(
                navArgument("id"){
                    NavType.LongType
                    defaultValue= -1L
                }
            )
        ){backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: -1L
            DetailScreen(
                noteId= id,
                assistedFactory= assistedFactory,
                navigateUp= {navHostController.navigateUp()}
            )
            
        }
    }

}

fun NavHostController.navigateToSingleTop(route: String){
    navigate(route){
        popUpTo(graph.findStartDestination().id){saveState=true}
        launchSingleTop= true
        restoreState= true
    }
}









