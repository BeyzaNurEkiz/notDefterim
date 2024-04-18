package com.example.notdefterim.di

import com.example.notdefterim.data.repository.NoteRepositoryImpl
import com.example.notdefterim.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
 abstract class RepositoryModule {
     @Binds
     @Singleton
     abstract fun bindRepository(repositoryImpl: NoteRepositoryImpl):Repository
}