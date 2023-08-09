package es.sdos.android.project.home.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import es.sdos.android.project.common.ui.BaseViewModel
import es.sdos.android.project.common.util.lifecycle.MutableSourceLiveData
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.repository.util.AppDispatchers
import es.sdos.android.project.data.repository.util.AsyncResult
import es.sdos.android.project.home.domain.CreateGameUseCase
import es.sdos.android.project.home.domain.GetGamesUseCase
import es.sdos.android.project.home.ui.fragment.HomeFragmentDirections
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val createGameUseCase: CreateGameUseCase,
    private val getGamesUseCase: GetGamesUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val pendingGameLiveData = MutableSourceLiveData<AsyncResult<List<GameBo>>>()

    init {
        requestPendingGame()
    }

    private fun requestPendingGame() {
        viewModelScope.launch {
            val newSource = getGamesUseCase.invoke()
            pendingGameLiveData.changeSource(newSource)
            // TODO(DONE)

        }
    }

    fun createGame() : LiveData<AsyncResult<GameBo>> {
        val createGameLiveData = MutableSourceLiveData<AsyncResult<GameBo>>()
        viewModelScope.launch(dispatchers.io) {
            createGameLiveData.changeSource(createGameUseCase())
        }
        return createGameLiveData.liveData()
    }

    fun goToScores() {
        navigate(HomeFragmentDirections.goToScoreHistory())
    }

    fun goToGame(gameId: Long) {
        navigate(HomeFragmentDirections.goToGame())
        // TODO(DONE)

    }

    fun getPendingGameLiveData() = pendingGameLiveData.liveData()
}