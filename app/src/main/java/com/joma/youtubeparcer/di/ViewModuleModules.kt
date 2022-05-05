package com.joma.youtubeparcer.di

import com.joma.youtubeparcer.ui.playlist.PlaylistViewModel
import com.joma.youtubeparcer.ui.playlistItem.PlaylistItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel { PlaylistItemViewModel(get()) }
}