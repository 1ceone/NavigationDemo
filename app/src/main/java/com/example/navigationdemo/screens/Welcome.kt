package com.example.navigationdemo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navigationdemo.ProfileScreen

@Composable
fun Welcome(
    onNavigateBack: () -> Unit,
    onNavigateToProfile: () -> Unit,
    name: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome, $name!")
        Button(onClick = onNavigateBack) {
            Text("Back")
        }
        Button(onClick = onNavigateToProfile) {
            Text("Go to Profile")
        }
    }
}
