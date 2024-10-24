package com.stefanoq21.whatsappgemini.presentation.screen.home.communities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CommunitiesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(0.8f))

            Text(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                text = "Stay connected with a community",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                text = "Communities bring members together in topic-based groups, and make it easy to get admin announcements. Any community you're added to will appear here.",
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                text = "See example communities >",
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                onClick = { }) {
                Text(
                    text = "Start your community",
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }


}