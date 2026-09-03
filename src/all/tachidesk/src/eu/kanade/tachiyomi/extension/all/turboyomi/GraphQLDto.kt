package eu.kanade.tachiyomi.extension.all.turboyomi

import kotlinx.serialization.Serializable

@Serializable
data class Error(val message: String)

@Serializable
data class Outer(val errors: List<Error>)
