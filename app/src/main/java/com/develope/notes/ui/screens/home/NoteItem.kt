package com.develope.notes.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.develope.notes.dto.Note
import com.develope.notes.utils.DATE_TIME_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NoteItem(
    note: Note,
    navigateToEditScreen: (noteId: Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(12.dp)
            .clickable {
                navigateToEditScreen(note.id)
            },
        elevation = CardDefaults.cardElevation(4.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(text = note.title, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.note, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            val formattedTime =
                SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(note.dateTime)
            Text(text = formattedTime)
        }
    }
}