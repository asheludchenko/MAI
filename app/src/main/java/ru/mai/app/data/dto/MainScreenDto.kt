package ru.mai.app.data.dto

import com.parse.ParseClassName
import com.parse.ParseObject

/**
 * Created by olegosipenko on 29.03.16.
 */
@ParseClassName("MainScreen")
class MainScreenDto: ParseObject() {

    fun getImageUrl(): String {
        return getString("imageUrl")
    }

    fun getLinkUrl(): String {
        return getString("linkUrl")
    }

    fun getButtonText(): String {
        return getString("buttonText")
    }
}