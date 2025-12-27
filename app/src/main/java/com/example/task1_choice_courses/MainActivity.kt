package com.example.task1_choice_courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.task1_choice_courses.ui.theme.Task1_choice_coursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task1_choice_coursesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier =
                            Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
                                .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val courses = listOf(
            Course(
                id = 0,
                name = stringResource(R.string.nothing_selected),
                description = null,
            ),
            Course(
                id = 1,
                name = stringResource(R.string.course_1),
                description = stringResource(R.string.course_1_description),
            ),
            Course(
                id = 2,
                name = stringResource(R.string.course_2),
                description = stringResource(R.string.course_2_description),
            ),
            Course(
                id = 3,
                name = stringResource(R.string.course_3),
                description = stringResource(R.string.course_3_description),
            ),
            Course(
                id = 4,
                name = stringResource(R.string.course_4),
                description = stringResource(R.string.course_4_description),
            ),
            Course(
                id = 5,
                name = stringResource(R.string.course_5),
                description = stringResource(R.string.course_5_description),
            ),
            Course(
                id = 6,
                name = stringResource(R.string.course_6),
                description = stringResource(R.string.course_6_description),
            ),
            Course(
                id = 7,
                name = stringResource(R.string.course_7),
                description = stringResource(R.string.course_7_description),
            ),
            Course(
                id = 8,
                name = stringResource(R.string.course_8),
                description = stringResource(R.string.course_8_description),
            ),
            Course(
                id = 9,
                name = stringResource(R.string.course_9),
                description = stringResource(R.string.course_9_description),
            ),
            Course(
                id = 10,
                name = stringResource(R.string.course_10),
                description = stringResource(R.string.course_10_description),
            )
        )
    var selectedItem by remember { mutableStateOf(courses[0]) }
    var currentDescription by remember { mutableStateOf(courses[0].description) }

    Column(modifier = modifier){
        DropdownMenuSample(courses){ selected ->
            selectedItem = selected
        }

        Button(
            onClick = {
                currentDescription = selectedItem.description
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.select)
            )
        }
        currentDescription?.let {
            Text(
                text = it,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuSample(courses: List<Course>, onSelect: (Course) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(courses[0].name) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = stringResource(R.string.select_course)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            courses.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item.name) },
                    onClick = {
                        selectedItem = item.name
                        expanded = false
                        onSelect(item)
                    }
                )
            }
        }
    }
}

data class Course(
    val id: Int,
    val name: String,
    val description: String?,
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Task1_choice_coursesTheme {
        MainScreen()
    }
}