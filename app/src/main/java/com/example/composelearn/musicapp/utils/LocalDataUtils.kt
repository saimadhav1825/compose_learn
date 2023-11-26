package com.example.composelearn.musicapp.utils

import com.example.composelearn.R
import com.example.composelearn.musicapp.basemodels.OnBoardingItem

object LocalDataUtils {
    fun getOnBoardingData(): List<OnBoardingItem> {
        return listOf(
            OnBoardingItem(
                R.drawable.ic_launcher_background,
                R.string.user_friendly_mp3_music_player_for_your_device
            ),
            OnBoardingItem(
                R.drawable.ic_launcher_background,
                R.string.we_provide_better_audio_expericence_than_others
            ),
            OnBoardingItem(
                R.drawable.ic_launcher_background,
                R.string.listen_to_the_best_audio_music_with_us
            ),
        )
    }

}