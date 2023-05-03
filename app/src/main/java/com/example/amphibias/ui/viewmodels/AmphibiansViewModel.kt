package com.example.amphibias.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibias.AmphibiansApplication
import com.example.amphibias.repository.interfaces.AmphibiansRepository
import com.example.amphibias.ui.viewmodels.interfaces.AmphibiansState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository):ViewModel() {

    var uiState: AmphibiansState by mutableStateOf(AmphibiansState.Loading)
        private set

    init {
        getAmphibiansData()
    }

    fun getAmphibiansData(){
        viewModelScope.launch {
            uiState = AmphibiansState.Loading
            uiState = try {
                AmphibiansState.Success(
                    amphibiansRepository.getAmphibiansData()
                )
            }catch (e:IOException){
                AmphibiansState.Error
            }catch (e:HttpException){
                AmphibiansState.Error
            }
        }
    }

    companion object{
        val Factory:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }

}