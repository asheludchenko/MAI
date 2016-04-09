package ru.mai.app.data.api

/**
 * Created by olegosipenko on 03.09.15.
 */
class SingleNews {
    val header: String? = null
    val photo: String? = null
    val date: String? = null
    val author: String? = null
    val body: String? = null

    override fun toString(): String {
        return "SingleNews{header='$header', photo='$photo', date='$date', author='$author', body='$body'}"
    }
}
