package com.example.notdefterim.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notdefterim.data.local.model.Note

@Composable
private fun HomeDetail(
    notes: List<Note>,
    modifier: Modifier,
    onBookmarkChange:(note:Note) -> Unit,
    onDeleteNote:(Long) ->Unit,
    onNoteClicked:(Long) ->Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        modifier = modifier
    ) {
        itemsIndexed(notes) {index, note ->

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(
    index: Int,
    note: Note,
    onBookmarkChange:(note:Note) -> Unit,
    onDeleteNote:(Long) ->Unit,
    onNoteClicked:(Long) ->Unit,
) {
    val isEvenIndex = index % 2 == 0
    val shape = when{
        isEvenIndex -> {
            RoundedCornerShape(
                topStart = 50f,
                bottomEnd = 50f,
            )
        }
        else -> {
            RoundedCornerShape(
                topEnd = 50f,
                bottomStart = 50f
            )
        }
    }
    val icon = if (note.isBookMarked) Icons.Default.BookmarkRemove
    else Icons.Outlined.BookmarkAdd
    Card(
        modifier= Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape= shape,
        onClick = {onNoteClicked(note.id)}
    ){
        Column (
            modifier= Modifier.fillMaxWidth().padding(8.dp)
        ) { }
    }
}