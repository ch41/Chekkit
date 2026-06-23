package com.example.navigation.model

import kotlinx.serialization.Serializable

@Serializable
sealed class AppDestinations {

    @Serializable
    data object Home : AppDestinations()

    @Serializable
    data object Receipts : AppDestinations()

    @Serializable
    data object Insights : AppDestinations()

    @Serializable
    data object Settings : AppDestinations()

    @Serializable
    data object Scanner : AppDestinations()

}
