package com.example.profileapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

    Box(modifier = Modifier.fillMaxSize()) {
        // Using Column with verticalScroll for all content including header
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // add spacer here for 40dp
            Spacer(modifier = Modifier.height(40.dp))

            // Include ProfileHeader at the top
            ProfileHeader(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor
            )

            // Content area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
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

                // Add bottom spacing to ensure content is not cut off by navigation bar
                Spacer(modifier = Modifier.height(8.dp))
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


@Composable
fun ProfileHeader(
    primaryColor: Color = Color(0xFF2196F3),
    secondaryColor: Color = Color(0xFF03DAC5),
    surfaceColor: Color = Color.White
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture
            Image(
                painter = painterResource(id = R.drawable.ic_person1),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Name with slightly larger, bolder text
            Text(
                text = "John Doe",
                fontSize = 24.sp,
                color = primaryColor,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )

            // Job title
            Text(
                text = "Android Developer",
                fontSize = 16.sp,
                color = secondaryColor,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}