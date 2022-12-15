package mx.com.practica.fotogramach.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.practica.fotogramach.repository.TiendasRepository
import mx.com.practica.fotogramach.repository.TiendasTask

@Module
@InstallIn(SingletonComponent::class)
abstract class TiendasAsigTasksModule {
    @Binds
    abstract fun bindTiendasTasks(tiendasRepository: TiendasRepository): TiendasTask
}