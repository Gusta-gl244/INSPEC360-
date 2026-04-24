package br.com.inspec360.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.inspec360.domain.model.Anomaly
import br.com.inspec360.domain.usecase.SaveAnomalyUseCase
import br.com.inspec360.domain.usecase.ValidateAnomalyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnomalyViewModel @Inject constructor(
    private val saveAnomalyUseCase: SaveAnomalyUseCase,
    private val validateAnomalyUseCase: ValidateAnomalyUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AnomalyUiState>(AnomalyUiState.Idle)
    val uiState: StateFlow<AnomalyUiState> = _uiState.asStateFlow()

    fun saveAnomaly(anomaly: Anomaly) {
        viewModelScope.launch {
            _uiState.value = AnomalyUiState.Loading
            val result = saveAnomalyUseCase(anomaly)
            result.onSuccess { anomalyId ->
                _uiState.value = AnomalyUiState.Success(anomalyId)
            }.onFailure { error ->
                _uiState.value = AnomalyUiState.Error(error.message ?: "Erro ao salvar anomalia")
            }
        }
    }

    fun validateAnomaly(anomaly: Anomaly) {
        viewModelScope.launch {
            val result = validateAnomalyUseCase(anomaly)
            result.onFailure { error ->
                _uiState.value = AnomalyUiState.ValidationError(error.message ?: "Validação falhou")
            }
        }
    }

    fun resetUiState() {
        _uiState.value = AnomalyUiState.Idle
    }
}

sealed class AnomalyUiState {
    data object Idle : AnomalyUiState()
    data object Loading : AnomalyUiState()
    data class Success(val anomalyId: Long) : AnomalyUiState()
    data class Error(val message: String) : AnomalyUiState()
    data class ValidationError(val message: String) : AnomalyUiState()
}
