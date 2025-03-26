package com.example.profileapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class for project information
data class ProjectData(
    val name: String,
    val techStack: String,
    val description: String,
    val completed: Boolean
)

@Composable
fun ProjectsScreen() {
    // Explicitly defined colors
    val primaryColor = Color(0xFF2196F3) // Blue
    val surfaceColor = Color(0xFFFFFFFF) // White
    val completedColor = Color(0xFFE8F5E9) // Light green
    val inProgressColor = Color(0xFFFFF8E1) // Light amber
    val completedTextColor = Color(0xFF388E3C) // Green text
    val inProgressTextColor = Color(0xFFFFA000) // Amber text
    val buttonColor = Color(0xFF2196F3) // Blue
    val buttonTextColor = Color(0xFFFFFFFF) // White
    val dividerColor = Color(0xFFE0E0E0) // Light gray

    // State for selected project category
    var selectedCategory by remember { mutableStateOf("Completed") }

    // State for projects list
    var projects by remember { mutableStateOf(getSampleProjects()) }

    // State for add project dialog
    var showAddDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Project category selection
        ProjectCategoryTabs(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            primaryColor = primaryColor,
            surfaceColor = surfaceColor,
            dividerColor = dividerColor
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Filter projects based on selected category
        val filteredProjects = projects.filter {
            if (selectedCategory == "Completed") it.completed else !it.completed
        }

        // Project list
        if (filteredProjects.isEmpty()) {
            // Empty state
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No ${selectedCategory.lowercase()} projects yet",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        } else {
            // Project list
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredProjects) { project ->
                    ProjectItem(
                        project = project,
                        completedColor = completedColor,
                        inProgressColor = inProgressColor,
                        completedTextColor = completedTextColor,
                        inProgressTextColor = inProgressTextColor,
                        dividerColor = dividerColor
                    )
                }
            }
        }

        // Add project button
        Button(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = buttonTextColor
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Project",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Add New Project")
        }
    }

    // Add project dialog
    if (showAddDialog) {
        AddProjectDialog(
            onDismiss = { showAddDialog = false },
            onAddProject = { name, techStack, description ->
                projects = projects + ProjectData(
                    name = name,
                    techStack = techStack,
                    description = description,
                    completed = selectedCategory == "Completed"
                )
                showAddDialog = false
            },
            primaryColor = primaryColor,
            textColor = Color.Black,
            buttonTextColor = buttonTextColor
        )
    }
}

@Composable
fun ProjectCategoryTabs(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    primaryColor: Color,
    surfaceColor: Color,
    dividerColor: Color
) {
    // Tabs for project categories
    TabRow(
        selectedTabIndex = if (selectedCategory == "Completed") 0 else 1,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        containerColor = surfaceColor,
        contentColor = primaryColor,
        divider = { Divider(thickness = 2.dp, color = dividerColor) }
    ) {
        // Completed projects tab
        Tab(
            selected = selectedCategory == "Completed",
            onClick = { onCategorySelected("Completed") },
            text = { Text("Completed") }
        )

        // In progress projects tab
        Tab(
            selected = selectedCategory == "In Progress",
            onClick = { onCategorySelected("In Progress") },
            text = { Text("In Progress") }
        )
    }
}

@Composable
fun ProjectItem(
    project: ProjectData,
    completedColor: Color = Color(0xFFE8F5E9),
    inProgressColor: Color = Color(0xFFFFF8E1),
    completedTextColor: Color = Color(0xFF388E3C),
    inProgressTextColor: Color = Color(0xFFFFA000),
    dividerColor: Color = Color(0xFFE0E0E0)
) {
    // State for expanding/collapsing project details
    var expanded by remember { mutableStateOf(false) }

    val backgroundColor = if (project.completed) completedColor else inProgressColor
    val statusTextColor = if (project.completed) completedTextColor else inProgressTextColor
    val textColor = Color(0xFF000000) // Black

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Project header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = project.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                    Text(
                        text = if (project.completed) "Completed" else "In Progress",
                        fontSize = 14.sp,
                        color = statusTextColor
                    )
                }

                // Expand/collapse icon
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded)
                            Icons.Default.KeyboardArrowUp
                        else
                            Icons.Default.KeyboardArrowDown,
                        contentDescription = if (expanded) "Collapse" else "Expand",
                        tint = textColor
                    )
                }
            }

            // Expandable project details
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(animationSpec = tween(200)),
                exit = shrinkVertically(animationSpec = tween(200))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Divider(color = dividerColor)
                    Spacer(modifier = Modifier.height(8.dp))

                    // Tech stack
                    Row(verticalAlignment = Alignment.Top) {
                        Text(
                            text = "Tech Stack:",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(100.dp),
                            color = textColor
                        )
                        Text(
                            text = project.techStack,
                            color = textColor
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Description
                    Row(verticalAlignment = Alignment.Top) {
                        Text(
                            text = "Description:",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(100.dp),
                            color = textColor
                        )
                        Text(
                            text = project.description,
                            color = textColor
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProjectDialog(
    onDismiss: () -> Unit,
    onAddProject: (String, String, String) -> Unit,
    primaryColor: Color = Color(0xFF2196F3),
    textColor: Color = Color.Black,
    buttonTextColor: Color = Color.White
) {
    var name by remember { mutableStateOf("") }
    var techStack by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Add New Project", color = textColor) },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Project name field
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Project Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Tech stack field
                OutlinedTextField(
                    value = techStack,
                    onValueChange = { techStack = it },
                    label = { Text("Tech Stack") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Description field
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Project Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp),
                    maxLines = 5
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank() && techStack.isNotBlank() && description.isNotBlank()) {
                        onAddProject(name, techStack, description)
                    }
                },
                enabled = name.isNotBlank() && techStack.isNotBlank() && description.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor,
                    contentColor = buttonTextColor,
                    disabledContainerColor = Color(0xFFBDBDBD) // Gray when disabled
                )
            ) {
                Text("Add Project")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = primaryColor
                )
            ) {
                Text("Cancel")
            }
        }
    )
}

// Function to provide sample projects data
fun getSampleProjects(): List<ProjectData> {
    return listOf(
        ProjectData(
            name = "CGPA Calculator",
            techStack = "Kotlin, Jetpack Compose",
            description = "An app to calculate CGPA based on course credits and grades.",
            completed = true
        ),
        ProjectData(
            name = "WishList App",
            techStack = "Kotlin, Firebase, Material Design",
            description = "An app to create and manage your wishlist with cloud synchronization.",
            completed = true
        ),
        ProjectData(
            name = "Portfolio App",
            techStack = "Jetpack Compose, Room Database",
            description = "A portfolio management app to showcase your projects and skills.",
            completed = false
        ),
        ProjectData(
            name = "E-commerce App",
            techStack = "Kotlin, Retrofit, MVVM",
            description = "An online shopping app with product catalog and cart functionality.",
            completed = false
        )
    )
}