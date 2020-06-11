package `in`.music.extras

import `in`.music.repository.MusicRepository
import `in`.music.viewmodel.MusicViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MusicFactory(
    private var musicRepository: MusicRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MusicViewModel(musicRepository) as T
    }
}