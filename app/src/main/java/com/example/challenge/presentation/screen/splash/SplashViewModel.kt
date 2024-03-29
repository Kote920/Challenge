package com.example.challenge.presentation.screen.splash

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.domain.usecase.datastore.GetTokenUseCase
import com.example.challenge.presentation.screen.log_in.LogInViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getTokenUseCase: GetTokenUseCase) :
    ViewModel() {

    private val _uiEvent = MutableSharedFlow<SplashUiEvent>()
    val uiEvent: SharedFlow<SplashUiEvent> get() = _uiEvent

    init {
        readSession()
    }

    private fun readSession() {
        viewModelScope.launch {
            getTokenUseCase().collect {
                if (it.isEmpty())
                { _uiEvent.emit(SplashUiEvent.NavigateToLogIn)
                d("here","Done")}
                else{
                    _uiEvent.emit(SplashUiEvent.NavigateToConnections)
                    d("here","Done")}
            }
        }
    }

    sealed interface SplashUiEvent {
        object NavigateToLogIn : SplashUiEvent
        object NavigateToConnections : SplashUiEvent

    }
}