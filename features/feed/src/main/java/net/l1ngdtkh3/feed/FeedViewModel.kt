package net.l1ngdtkh3.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import net.l1ngdtkh3.domain.models.Movie
import net.l1ngdtkh3.domain.usecases.MoviesUseCase
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val useCase: MoviesUseCase) : ViewModel() {

    private val _state = MutableStateFlow(State())

    init {
        viewModelScope.launch {
            useCase.nowPlayingMoviesPaging().cachedIn(viewModelScope).collect {
                _state.value = _state.value.copy(pagingData = it)
            }
        }
    }

    val pagingData = _state.map { it.pagingData }.distinctUntilChanged()

}

data class State(
    val pagingData: PagingData<Movie> = PagingData.empty()
)