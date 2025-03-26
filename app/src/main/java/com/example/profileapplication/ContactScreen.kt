package com.example.profileapplication

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactScreen() {
    // Explicitly define colors
    val primaryColor = Color(0xFF2196F3) // Blue
    val surfaceVariantColor = Color(0xFFE3F2FD) // Light Blue

    val contacts = listOf(
        Contact(Icons.Default.Email, "Email", "johndoe@gmail.com"),
        Contact(Icons.Default.Phone, "Phone", "+1 123 456 7890"),
        Contact(Icons.Default.Share, "LinkedIn", "linkedin.com/in/johndoe"),
        Contact(Icons.Default.List, "GitHub", "github.com/johndoe")
    )

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Contact Me",
                fontSize = 24.sp,
                color = primaryColor
            )
        }
        items(contacts) { contact ->
            ContactCard(contact, surfaceVariantColor)
        }
    }
}

data class Contact(val icon: ImageVector, val label: String, val value: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactCard(
    contact: Contact,
    backgroundColor: Color = Color(0xFFE3F2FD),
    textColor: Color = Color(0xFF000000),
    iconColor: Color = Color(0xFF2196F3)
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        onClick = { expanded = !expanded },
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        ) {
            Icon(
                contact.icon,
                contentDescription = null,
                modifier = Modifier.padding(16.dp),
                tint = iconColor
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = contact.label,
                    fontSize = 20.sp,
                    color = textColor,
                    modifier = Modifier.padding(top = 16.dp, end = 16.dp)
                )
                if (expanded) {
                    Text(
                        text = contact.value,
                        fontSize = 14.sp,
                        color = Color(0xFF757575), // Gray text for value
                        modifier = Modifier.padding(top = 8.dp, end = 16.dp, bottom = 16.dp)
                    )
                }
            }
        }
    }
}