package com.bengkelsampah.bengkelsampahapp.ui.main

import com.bengkelsampah.bengkelsampahapp.domain.model.NewsResourceModel

sealed interface NewsUiState{
    data class Success(val news: List<NewsResourceModel>): NewsUiState

    data class Error(val message:String? = null): NewsUiState

    object Loading: NewsUiState
}