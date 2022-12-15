package mx.com.practica.fotogramach.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.practica.fotogramach.repository.AuthRepository
import mx.com.practica.fotogramach.repository.AuthTask

@Module
@InstallIn(
    SingletonComponent::class
)
abstract class AuthTasksModule {

    @Binds
    abstract fun bindAuthTasks(
        authRepository: AuthRepository
    ): AuthTask
}