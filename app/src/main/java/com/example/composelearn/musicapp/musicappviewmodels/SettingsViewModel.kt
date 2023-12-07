package com.example.composelearn.musicapp.musicappviewmodels

import androidx.lifecycle.ViewModel
import com.example.composelearn.R
import com.example.composelearn.musicapp.basemodels.SettingsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor():ViewModel() {

    private val _uiState = MutableStateFlow(SettingState())
    val uiState = _uiState.asStateFlow()
    init {
        _uiState.update { settingState->
            settingState.copy(
                settingList = getSettingsList()
            )
        }
    }
    private fun getSettingsList() = listOf(
        SettingsItem(icon = R.drawable.backup_icon, title = "Backup"),
        SettingsItem(icon = R.drawable.notifications_icon, title = "Notification"),
        SettingsItem(icon = R.drawable.language_icon, title = "Language"),
        SettingsItem(icon = R.drawable.share_icon, title = "Share App"),
        SettingsItem(icon = R.drawable.backup_icon, title = "Change Log"),
        SettingsItem(icon = R.drawable.privacy_icon, title = "Privacy Policy"),
        SettingsItem(icon = R.drawable.backup_icon, title = "FAQ"),
        SettingsItem(icon = R.drawable.logout_icon, title = "Quit"),
    )
}

data class SettingState(val settingList: List<SettingsItem> = emptyList())