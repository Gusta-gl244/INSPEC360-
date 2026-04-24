package br.com.inspec360.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.inspec360.domain.model.Structure
import br.com.inspec360.domain.usecase.GetAllStructuresUseCase
import br.com.inspec360.domain.usecase.SearchStructuresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StructuresViewModel @Inject constructor(
    private val getAllStructuresUseCase: GetAllStructuresUseCase,
    private val searchStructuresUseCase: SearchStructuresUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<StructuresUiState>(StructuresUiState.Loading)
    val uiState: StateFlow<StructuresUiState> = _uiState.asStateFlow()

    init {
        loadStructures()
    }

    private fun loadStructures() {
        viewModelScope.launch {
            _uiState.value = StructuresUiState.Loading
            val result = getAllStructuresUseCase()
            result.onSuccess { structures ->
                _uiState.value = StructuresUiState.Success(structures)
            }.onFailure { error ->
                _uiState.value = StructuresUiState.Error(error.message ?: "Erro ao carregar estruturas")
            }
        }
    }

    fun search(query: String) {
        if (query.isEmpty()) {
            loadStructures()
            return
        }
        viewModelScope.launch {
            _uiState.value = StructuresUiState.Loading
            val result = searchStructuresUseCase(query)
            result.onSuccess { structures ->
                _uiState.value = StructuresUiState.Success(structures)
            }.onFailure { error ->
                _uiState.value = StructuresUiState.Error(error.message ?: "Erro na busca")
            }
        }
    }

    fun refresh() {
        loadStructures()
    }
}

sealed class StructuresUiState {
    data object Loading : StructuresUiState()
    data class Success(val structures: List<Structure>) : StructuresUiState()
    data class Error(val message: String) : StructuresUiState()
}
