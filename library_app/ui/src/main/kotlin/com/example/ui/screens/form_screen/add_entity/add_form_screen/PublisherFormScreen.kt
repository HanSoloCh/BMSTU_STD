package com.example.ui.screens.form_screen.add_entity.add_form_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui.screens.form_screen.add_entity.AddEntityViewModel

@Composable
fun PublisherFormScreen(viewModel: AddEntityViewModel) {
    val form = viewModel.publisherForm
    Column {
        OutlinedTextField(
            value = form.name,
            onValueChange = { viewModel.publisherForm = form.copy(name = it) },
            label = { Text("Название*") },
            isError = form.name.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = form.description,
            onValueChange = { viewModel.publisherForm = form.copy(description = it) },
            label = { Text("Описание") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = form.email,
            onValueChange = { viewModel.publisherForm = form.copy(email = it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = form.phoneNumber,
            onValueChange = { viewModel.publisherForm = form.copy(phoneNumber = it) },
            label = { Text("Телефон") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
