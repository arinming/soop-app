package com.soop.repository

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.soop.repository.navigation.RepositoryRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val owner = savedStateHandle.toRoute<RepositoryRoute>().owner
    val repo = savedStateHandle.toRoute<RepositoryRoute>().repo
}