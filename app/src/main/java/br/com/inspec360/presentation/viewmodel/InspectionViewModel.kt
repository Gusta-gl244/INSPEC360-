package br.com.inspec360.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.inspec360.domain.model.Inspection
import br.com.inspec360.domain.usecase.CreateInspectionUseCase
import br.com.inspec360.domain.usecase.FinalizeInspectionUseCase
import br.com.inspec360.domain.usecase.GetInspectionHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InspectionViewModel @Inject constructor(
    private val createInspectionUseCase: CreateInspectionUseCase,
    private val finalizeInspectionUseCase: FinalizeInspectionUseCase,
    private val getInspectionHistoryUseCase: GetInspectionHistoryUseCase
) : ViewModel() {

    private val _currentInspection = MutableStateFlow<Inspection?>(null)
    val currentInspection: StateFlow<Inspection?> = _currentInspection.asStateFlow()

    private val _uiState = MutableStateFlow<InspectionUiState>(InspectionUiState.Idle)
    val uiState: StateFlow<InspectionUiState> = _uiState.asStateFlow()

    private val _historyState = MutableStateFlow<InspectionHistoryState>(InspectionHistoryState.Loading)
    val historyState: StateFlow<InspectionHistoryState> = _historyState.asStateFlow()

    fun startInspection(structureId: Long, inspectorId: Long) {
        viewModelScope.launch {
            _uiState.value = InspectionUiState.Loading
            val result = createInspectionUseCase(structureId, inspectorId)
            result.onSuccess { inspectionId ->
                _uiState.value = InspectionUiState.InspectionStarted(inspectionId)
            }.onFailure { error ->
                _uiState.value = InspectionUiState.Error(error.message ?: "Erro ao iniciar inspeção")
            }
        }
    }

    fun setCurrentInspection(inspection: Inspection) {
        _currentInspection.value = inspection
    }

    fun finalize(inspectionId: Long) {
        viewModelScope.launch {
            _uiState.value = InspectionUiState.Loading
            val result = finalizeInspectionUseCase(inspectionId)
            result.onSuccess {
                _uiState.value = InspectionUiState.InspectionFinalized
            }.onFailure { error ->
                _uiState.value = InspectionUiState.Error(error.message ?: "Erro ao finalizar")
            }
        }
    }

    fun loadHistory(inspectorId: Long) {
        viewModelScope.launch {
            _historyState.value = InspectionHistoryState.Loading
            try {
                val inspections = getInspectionHistoryUseCase(inspectorId)
                _historyState.value = InspectionHistoryState.Success(inspections)
            } catch (e: Exception) {
                _historyState.value = InspectionHistoryState.Error(e.message ?: "Erro ao carregar histórico")
            }
        }
    }
}

sealed class InspectionUiState {
    data object Idle : InspectionUiState()
    data object Loading : InspectionUiState()
    data class InspectionStarted(val inspectionId: Long) : InspectionUiState()
    data object InspectionFinalized : InspectionUiState()
    data class Error(val message: String) : InspectionUiState()
}

sealed class InspectionHistoryState {
    data object Loading : InspectionHistoryState()
    data class Success(val inspections: List<Inspection>) : InspectionHistoryState()
    data class Error(val message: String) : InspectionHistoryState()
}
