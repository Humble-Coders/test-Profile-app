package com.example.profileapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    // Colors explicitly defined
    val primaryColor = Color(0xFF2196F3) // Blue
    val secondaryColor = Color(0xFF03DAC5) // Teal
    val cardBackgroundColor = Color(0xFFF5F5F5) // Light gray
    val dividerColor = Color(0xFFBDBDBD) // Medium gray
    val textPrimaryColor = Color(0xFF000000) // Black
    val textSecondaryColor = Color(0xFF757575) // Dark gray
    val textTertiaryColor = Color(0xFF9E9E9E) // Gray

    // Using Column with verticalScroll instead of LazyColumn for simple content
    // This is easier for beginners to understand than LazyColumn for static content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Bio Section
        SectionTitle(title = "Bio", color = primaryColor)
        ContentCard(backgroundColor = cardBackgroundColor) {
            Text(
                text = "Hi, I'm John Doe, a passionate Android Developer. " +
                        "I love building sleek, user-friendly apps using Kotlin and Jetpack Compose.",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = textPrimaryColor
            )
        }

        // Education Section
        SectionTitle(title = "Education", color = primaryColor)
        ContentCard(backgroundColor = cardBackgroundColor) {
            Column {
                EducationItem(
                    degree = "Bachelor of Computer Science",
                    institution = "Tech University",
                    year = "2018-2022",
                    textPrimaryColor = textPrimaryColor,
                    textSecondaryColor = textSecondaryColor,
                    textTertiaryColor = textTertiaryColor
                )

                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = dividerColor
                )

                EducationItem(
                    degree = "Android Development Certification",
                    institution = "Google Developers",
                    year = "2022",
                    textPrimaryColor = textPrimaryColor,
                    textSecondaryColor = textSecondaryColor,
                    textTertiaryColor = textTertiaryColor
                )

                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = dividerColor
                )

                EducationItem(
                    degree = "Kotlin Masterclass",
                    institution = "Online Course Platform",
                    year = "2023",
                    textPrimaryColor = textPrimaryColor,
                    textSecondaryColor = textSecondaryColor,
                    textTertiaryColor = textTertiaryColor
                )
            }
        }

        // Achievements Section
        SectionTitle(title = "Achievements", color = primaryColor)
        ContentCard(backgroundColor = cardBackgroundColor) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AchievementItem(text = "Completed 200+ Leetcode Problems", textColor = textPrimaryColor)
                AchievementItem(text = "Published 3 apps on Google Play Store", textColor = textPrimaryColor)
                AchievementItem(text = "Winner of Regional Hackathon 2023", textColor = textPrimaryColor)
                AchievementItem(text = "Open Source Contributor", textColor = textPrimaryColor)
            }
        }
    }
}

@Composable
fun SectionTitle(title: String, color: Color = Color(0xFF2196F3)) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun ContentCard(backgroundColor: Color = Color(0xFFF5F5F5), content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
fun EducationItem(
    degree: String,
    institution: String,
    year: String,
    textPrimaryColor: Color = Color(0xFF000000),
    textSecondaryColor: Color = Color(0xFF757575),
    textTertiaryColor: Color = Color(0xFF9E9E9E)
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = degree,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = textPrimaryColor
        )
        Text(
            text = institution,
            fontSize = 14.sp,
            color = textSecondaryColor
        )
        Text(
            text = year,
            fontSize = 14.sp,
            color = textTertiaryColor
        )
    }
}

@Composable
fun AchievementItem(text: String, textColor: Color = Color(0xFF000000)) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "•",
            fontSize = 16.sp,
            modifier = Modifier.padding(end = 8.dp),
            color = textColor
        )
        Text(
            text = text,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = textColor
        )
    }
}