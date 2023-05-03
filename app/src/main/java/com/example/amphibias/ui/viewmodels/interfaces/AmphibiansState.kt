package com.example.amphibias.ui.viewmodels.interfaces

import com.example.amphibias.data.AmphibiansModel

sealed interface AmphibiansState{
    data class Success(val amphibians: List<AmphibiansModel>):AmphibiansState
    object Error: AmphibiansState
    object Loading: AmphibiansState
}
