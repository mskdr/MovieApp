package com.muhammetkudur.common.coroutine

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 *    Created By Muhammet KÜDÜR
 *    17.07.2023
 */

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Dispatcher(DispatcherType.Io)
    @Provides
    @Singleton
    fun provideDispatcheIo(): CoroutineContext = Dispatchers.IO

    @Dispatcher(DispatcherType.Unconfined)
    @Provides
    @Singleton
    fun provideDispatcherUncf(): CoroutineContext = Dispatchers.Unconfined

    @Dispatcher(DispatcherType.Default)
    @Provides
    @Singleton
    fun provideDispatcherDefault(): CoroutineContext = Dispatchers.Default

    @Dispatcher(DispatcherType.Main)
    @Provides
    @Singleton
    fun provideDispatcherMain(): CoroutineContext = Dispatchers.Main

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Dispatcher(val type: DispatcherType)

enum class DispatcherType{
    Main,
    Io,
    Default,
    Unconfined
}