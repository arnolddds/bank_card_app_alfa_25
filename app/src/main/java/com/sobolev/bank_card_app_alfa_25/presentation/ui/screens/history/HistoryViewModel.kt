package com.sobolev.bank_card_app_alfa_25.presentation.ui.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobolev.bank_card_app_alfa_25.domain.entitites.BinInfo
import com.sobolev.bank_card_app_alfa_25.domain.usecases.GetSearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryScreenState())
    val state = _state.asStateFlow()

    init {
        loadHistory()
    }

    fun loadHistory() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            getSearchHistoryUseCase()
                .catch {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "Не удалось загрузить историю"
                        )
                    }
                }
                .collect { history ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            history = history
                        )
                    }
                }
        }
    }
}

data class HistoryScreenState(
    val history: List<BinInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)


