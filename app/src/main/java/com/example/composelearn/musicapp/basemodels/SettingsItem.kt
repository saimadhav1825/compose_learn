package com.example.composelearn.musicapp.basemodels

data class SettingsItem(
    val icon: Int,
    val title: String,
    val content: String="",
    val isArrowVisible: Boolean = true, val isCheckBox: Boolean = false
)