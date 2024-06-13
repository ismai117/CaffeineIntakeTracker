package main.screens.input.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import main.screens.input.domain.model.Intake
import main.screens.input.domain.repository.IntakeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IntakeViewModel(
    private val repository: IntakeRepository
) : ViewModel(){

    private val _caffeineIntake = MutableStateFlow<List<Intake>>(emptyList())
    val caffeineIntake = _caffeineIntake.asStateFlow()

    init {
        getTotalCaffeineIntake()
    }

    fun onEvent(event: IntakeEvent){
        when(event){
            is IntakeEvent.INSERT -> {
                insertCaffeineIntake(event.caffeineIntake)
            }
        }
    }

    private fun getTotalCaffeineIntake(){
        viewModelScope.launch {
            repository.getTotalCaffeineIntake()
                .collect { result ->
                    _caffeineIntake.value = result
                }
        }
    }

    private fun insertCaffeineIntake(caffeineIntake: Intake){
        viewModelScope.launch {
            repository.insertCaffeineIntake(caffeineIntake)
        }
    }


}