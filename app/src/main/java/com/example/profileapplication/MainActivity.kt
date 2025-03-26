package com.example.profileapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profileapplication.ui.theme.ProfileApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enable edge-to-edge display
        setContent {
            ProfileApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F5F5) // Light gray background color
                ) {
                    MainScreen()
                }
            }
        }
    }
}

enum class Screen {
    Home, Projects, Skills, Contact
}

@Composable
fun MainScreen() {
    // State to track which screen is currently selected
    var selectedScreen by remember {
        mutableStateOf(Screen.Home)
    }

    // Primary colors explicitly defined
    val primaryColor = Color(0xFF2196F3) // Blue

    // Main container using simple Column layout
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Content area that takes the available space
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // Show the appropriate screen based on selection
            when (selectedScreen) {
                Screen.Home -> HomeScreen()
                Screen.Projects -> ProjectsScreen()
                Screen.Skills -> SkillScreen()
                Screen.Contact -> ContactScreen()
            }
        }

        // Bottom navigation
        BottomNavigationBar(
            selectedScreen = selectedScreen,
            onScreenSelected = { selectedScreen = it },
            primaryColor = primaryColor
        )
    }
}

@Composable
fun BottomNavigationBar(
    selectedScreen: Screen,
    onScreenSelected: (Screen) -> Unit,
    primaryColor: Color
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = primaryColor
    ) {
        // Home navigation item
        NavigationBarItem(
            selected = selectedScreen == Screen.Home,
            onClick = { onScreenSelected(Screen.Home) },
            icon = { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "Home") },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryColor,
                selectedTextColor = primaryColor,
                indicatorColor = Color.White,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        // Projects navigation item
        NavigationBarItem(
            selected = selectedScreen == Screen.Projects,
            onClick = { onScreenSelected(Screen.Projects) },
            icon = { Icon(painterResource(id = R.drawable.ic_projects), contentDescription = "Projects") },
            label = { Text("Projects") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryColor,
                selectedTextColor = primaryColor,
                indicatorColor = Color.White,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        // Skills navigation item
        NavigationBarItem(
            selected = selectedScreen == Screen.Skills,
            onClick = { onScreenSelected(Screen.Skills) },
            icon = { Icon(painterResource(id = R.drawable.ic_skills), contentDescription = "Skills") },
            label = { Text("Skills") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryColor,
                selectedTextColor = primaryColor,
                indicatorColor = Color.White,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        // Contact navigation item
        NavigationBarItem(
            selected = selectedScreen == Screen.Contact,
            onClick = { onScreenSelected(Screen.Contact) },
            icon = { Icon(painterResource(id = R.drawable.ic_contact), contentDescription = "Contact") },
            label = { Text("Contact") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryColor,
                selectedTextColor = primaryColor,
                indicatorColor = Color.White,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}
