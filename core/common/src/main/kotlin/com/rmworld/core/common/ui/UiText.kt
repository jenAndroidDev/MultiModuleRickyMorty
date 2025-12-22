package com.rmworld.core.common.ui

import android.content.Context
import androidx.annotation.StringRes
import com.rmworld.core.common.R

sealed class UiText{
    data class DynamicString(val value:String):UiText()
    class StringResource(
        @StringRes val resId:Int,
        vararg val args:Any
    ):UiText()

    fun asString(context: Context):String{
        return when(this){
            is DynamicString->value
            is StringResource->context.getString(resId,*args)
        }
    }
     companion object{
         val unKnownError:UiText = StringResource(R.string.unknown_error)
         val someThingWentWrong:UiText = StringResource(R.string.something_went_wrong_try_later)
         val noInternet:UiText = StringResource(R.string.check_your_internet)
    }
}