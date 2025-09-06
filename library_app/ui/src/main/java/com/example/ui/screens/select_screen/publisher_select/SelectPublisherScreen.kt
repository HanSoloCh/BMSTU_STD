package com.example.ui.screens.select_screen.publisher_select

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ui.common.json.appJson
import com.example.ui.component.SearchBar
import com.example.ui.items.PublisherItem
import com.example.ui.model.PublisherModel
import com.example.ui.screens.select_screen.SearchState


@Composable
fun SelectPublisherScreen(
    navController: NavController,
    viewModel: SelectPublisherViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Поле поиска
        SearchBar(
            query = query,
            placeholder = "Поиск издеателя",
            onQueryChange = { query = it },
            onSearchSubmit = { viewModel.search(query) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )

        // Состояния
        when (state) {
            is SearchState.Empty -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Введите название издателя и нажмите поиск")
                }
            }

            is SearchState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is SearchState.Error -> {
                val error = (state as SearchState.Error).message
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Ошибка: $error", color = MaterialTheme.colorScheme.error)
                }
            }

            is SearchState.Success -> {
                val publishers: List<PublisherModel> = (state as SearchState.Success).results
                if (publishers.isEmpty()) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Издатель не найден")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                    ) {
                        items(publishers) { publisher ->
                            PublisherItem(publisher) {
                                val bbkJson =
                                    appJson.encodeToString(PublisherModel.serializer(), publisher)
                                navController.previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("selectedPublisher", bbkJson)
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}
