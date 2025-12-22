package com.rmworld.core.common.apiresult

import com.rmworld.core.common.ui.UiText

/**
 * Extension to safely map a [Throwable] to a [UiText].
 * Handles nullability and potential exceptions during mapping.
 */
fun Throwable?.toUiText(): UiText {
    return runCatching {
        when (this) {
            is NoInternetException -> UiText.noInternet
            is ApiException -> UiText.someThingWentWrong
            else -> UiText.unKnownError
        }
    }.getOrDefault(UiText.unKnownError)
}
class NoInternetException : Exception("No Internet Available")
class ApiException : Exception("Something Went Wrong")
