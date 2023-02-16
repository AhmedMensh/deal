package com.example.core

import androidx.navigation.NavOptionsBuilder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NavigatorImpl @Inject constructor() : Navigator {

    private val navigationEvents = Channel<NavigatorEvent>()
    override val destinations = navigationEvents.receiveAsFlow()

    override fun navigateUp(): Boolean =
        navigationEvents.trySend(NavigatorEvent.NavigateUp).isSuccess

    override fun popBackStack() {
        navigationEvents.trySend(NavigatorEvent.PopBackStack)
    }

    override fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit): Boolean =
        navigationEvents.trySend(NavigatorEvent.Directions(route, builder)).isSuccess

}


@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigatorModule {

    @Binds
    abstract fun navigator(navigator: NavigatorImpl): Navigator
}