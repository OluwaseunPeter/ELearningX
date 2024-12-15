package com.app.miva.player.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.app.miva.lessons.domain.model.Note
import com.app.miva.player.presentation.viewmodel.PlayerViewmodel
import org.koin.compose.koinInject

const val VIDEO_URL = "VIDEO_URL"

@Composable
fun VideoPlayerCompose(videoUrl: String, viewModel: PlayerViewmodel = koinInject()) {
    viewModel.fetchVideoNotes(videoUrl)
    val state by viewModel.uiState.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    Box {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 14.dp)
        ) {
            VideoPlayer(url = videoUrl, modifier = Modifier.fillMaxWidth())
            LazyColumn {
                items(
                    state.notes
                ) { item ->
                    Box(modifier = Modifier
                        .padding(vertical = 12.dp, horizontal = 14.dp)
                        .clickable {

                        }) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(item.text)
                            IconButton(onClick = {
                                viewModel.setCurrentNote(item)
                                showDialog = true
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = "Edit",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Note"
            )
        }
    }

    if (showDialog) {
        showDialog(
            initialText = state.currentNote?.text.orEmpty(),
            onDismiss = {
                showDialog = false
                viewModel.setCurrentNote(null)
            },
            onSave = {
                showDialog = false
                val note = state.currentNote?.copy(text = it) ?: Note(
                    lessonId = videoUrl,
                    text = it
                )
                viewModel.setCurrentNote(null)
                viewModel.saveVideoNote(note)
            }
        )
    }

}

@Composable
fun showDialog(
    initialText: String,
    onDismiss: () -> Unit,
    onSave: (note: String) -> Unit
) {
    var text by remember { mutableStateOf(initialText) }
    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Surface(
                modifier = Modifier
                    .width(280.dp)
                    .height(160.dp),
                tonalElevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        placeholder = { Text("Enter note") }
                    )

                    Button(onClick = {
                        onSave(text)
                        text = ""
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    )
}