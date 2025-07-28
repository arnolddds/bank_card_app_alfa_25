package com.sobolev.bank_card_app_alfa_25.presentation.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobolev.bank_card_app_alfa_25.domain.entitites.BinInfo
import com.sobolev.bank_card_app_alfa_25.domain.usecases.GetBinInfoUseCase
import com.sobolev.bank_card_app_alfa_25.domain.usecases.GetSearchHistoryUseCase
import com.sobolev.bank_card_app_alfa_25.domain.usecases.SaveBinInfoToHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinInfoViewModel @Inject constructor(
    private val getBinInfoUseCase: GetBinInfoUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val saveBinInfoToHistoryUseCase: SaveBinInfoToHistoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BinInfoScreenState())
    val state = _state.asStateFlow()

    fun onSearch(bin: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null, binInfo = null) }

            try {
                val result = getBinInfoUseCase(bin)
                saveBinInfoToHistoryUseCase(result)

                _state.update {
                    it.copy(
                        binInfo = result,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                val message = when (e) {
                    is retrofit2.HttpException -> when (e.code()) {
                        404 -> "BIN не найден"
                        429 -> "Превышен лимит запросов"
                        else -> "Ошибка ${e.code()}"
                    }
                    is java.net.UnknownHostException -> "Нет подключения к интернету"
                    else -> "Неизвестная ошибка: ${e.localizedMessage}"
                }

                _state.update { it.copy(error = message, isLoading = false) }
            }
        }
    }

    fun loadHistory() {
        viewModelScope.launch {
            getSearchHistoryUseCase()
                .catch { e ->
                    _state.update { it.copy(error = "Ошибка загрузки истории") }
                }
                .collect { history ->
                    _state.update { it.copy(history = history) }
                }
        }
    }
}

data class BinInfoScreenState(
    val binInfo: BinInfo? = null,
    val history: List<BinInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

