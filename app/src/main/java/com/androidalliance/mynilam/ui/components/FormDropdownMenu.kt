package com.androidalliance.mynilam.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androidalliance.mynilam.data.models.ReadingMaterial

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormDropdownMenu(
    selectedValue: String?,
    options: List<ReadingMaterial>,
    onValueChangeEvent: (String) -> Unit,
    modifier: Modifier = Modifier
){
    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier
    ) {
        OutlinedTextField(
                value = selectedValue ?: "",
                onValueChange = {  },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                singleLine = true,
                maxLines = 1,
                isError = false,
                readOnly = true,
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
        )

        // MenuList
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { item: ReadingMaterial ->
                DropdownMenuItem(
                    text = { Text(text = item.title) },
                    onClick = {
                        onValueChangeEvent(item.title)
                        expanded = false
                    }
                )
            }
        }
    }
}



//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FormDropdownMenu(
//    items: Flow<List<ReadingMaterial>>,
//    selectedItem: ReadingMaterial? = null,
//    onItemSelected: (ReadingMaterial) -> Unit,
//    ) {
//    var expanded by remember { mutableStateOf(false) }
//
//    val materialsLists by items.collectAsState(initial = emptyList())
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Reading Material",
//            modifier = Modifier
//                .align(Alignment.Start)
//                .padding(bottom = 5.dp),
//            style = MaterialTheme.typography.titleSmall,
//            fontWeight = FontWeight.Bold
//        )
//        ExposedDropdownMenuBox(
//            expanded = expanded,
//            onExpandedChange = { expanded = !expanded }
//        ) {
//            OutlinedTextField(
//                value = selectedItem?.title ?: "",
//                onValueChange = {  },
//                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
//                singleLine = true,
//                maxLines = 1,
//                isError = false,
//                readOnly = true,
//                modifier = Modifier.menuAnchor(),
//                shape = RoundedCornerShape(12.dp)
//            )
//
//            ExposedDropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false }
//            ) {
//                materialsLists.forEach { item ->
//                    DropdownMenuItem(
//                        text = { item.title },
//                        onClick = {
//                            onItemSelected(item)
//                            expanded = false
//                        },
//                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
//                    )
//                }
//            }
//        }
//    }
//}