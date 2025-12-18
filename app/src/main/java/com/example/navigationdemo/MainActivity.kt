package com.example.navigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationdemo.ui.theme.NavigationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToWelcome = { name ->
                    // Переход на WelcomeScreen с параметром
                    navController.navigate("welcome/$name")
                },
                onNavigateToProfile = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }

        composable(Routes.WELCOME) { backStackEntry ->
            // Извлекаем параметр из маршрута
            val name = backStackEntry.arguments?.getString("name") ?: "Гость"
            WelcomeScreen(
                name = name,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

// Примеры экранов (должны быть в отдельных файлах)
@Composable
fun HomeScreen(
    onNavigateToWelcome: (String) -> Unit,
    onNavigateToProfile: () -> Unit
) {
    Column {
        Text("Home Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onNavigateToWelcome("User") }) {
            Text("Go to Welcome")
        }
        Button(onClick = onNavigateToProfile) {
            Text("Go to Profile")
        }
    }
}

@Composable
fun WelcomeScreen(name: String, onNavigateBack: () -> Unit) {
    Column {
        Text("Welcome, $name!")
        Button(onClick = onNavigateBack) {
            Text("Back")
        }
    }
}

@Composable
fun ProfileScreen(onNavigateBack: () -> Unit) {
    Column {
        Text("Profile Screen")
        Button(onClick = onNavigateBack) {
            Text("Back")
        }
    }
}