package ru.mai.app.data.api

/**
 * Created by olegosipenko on 03.09.15.
 */
public class SingleNews {
    public val header: String? = null
    public val photo: String? = null
    public val date: String? = null
    public val author: String? = null
    public val body: String? = null

    override fun toString(): String {
        return "SingleNews{header='$header', photo='$photo', date='$date', author='$author', body='$body'}"
    }
}
