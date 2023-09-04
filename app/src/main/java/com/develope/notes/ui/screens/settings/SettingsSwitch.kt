package com.develope.notes.ui.screens.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develope.notes.R
import com.develope.notes.ui.theme.NotesTheme

@Composable
fun SettingSwitchCard(
    icon: Painter,
    text: String,
    isChecked: Boolean,
    onCheckedChange: () -> Unit

) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = icon, contentDescription = null)
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = text,

                )
            Switch(modifier = Modifier.padding(start = 130.dp),
                checked = isChecked,
                onCheckedChange = { onCheckedChange() }
            )

        }
    }
}


@Preview
@Composable
fun PreviewSettingsSwitchCard() {
    NotesTheme {
        SettingSwitchCard(
            icon = painterResource(id = R.drawable.moon_icon),
            text = "Dark Mode",
            isChecked = false,
            onCheckedChange = {}
        )
    }
}