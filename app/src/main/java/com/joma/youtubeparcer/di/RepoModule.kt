package com.joma.youtubeparcer.di

import com.joma.youtubeparcer.data.repo.PlaylistRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModule: Module = module {
    factory { PlaylistRepository(get()) }


}