package com.poomani.tv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poomani.tv.data.local.SettingsDataStore
import com.poomani.tv.data.remote.*
import com.poomani.tv.repository.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val repository: TvRepository,
    val settingsDataStore: SettingsDataStore
) : ViewModel() {

    private val _providers = MutableStateFlow<List<Provider>>(emptyList())
    val providers: StateFlow<List<Provider>> = _providers

    private val _liveChannels = MutableStateFlow<List<LiveChannel>>(emptyList())
    val liveChannels: StateFlow<List<LiveChannel>> = _liveChannels

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _series = MutableStateFlow<List<Series>>(emptyList())
    val series: StateFlow<List<Series>> = _series

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getProviders().onSuccess { _providers.value = it }
            repository.getLiveChannels().onSuccess { _liveChannels.value = it }
            repository.getMovies().onSuccess { _movies.value = it }
            repository.getSeries().onSuccess { _series.value = it }
            _isLoading.value = false
        }
    }

    fun updateUrl(url: String) {
        viewModelScope.launch {
            settingsDataStore.saveBackendUrl(url)
        }
    }
}
