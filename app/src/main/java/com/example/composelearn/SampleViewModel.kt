package com.example.composelearn

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.composelearn.ui.sampleScreens.WellnessTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor() : ViewModel() {
    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        tasks.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }
}


private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }