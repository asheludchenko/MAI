package oleg.osipenko.mai.presentation.utils

import android.net.Uri
import android.support.annotation.DrawableRes

/**
 * Created by olegosipenko on 24.04.16.
 */
data class Map(@DrawableRes val mapImage: Int, val yandexUri: Uri, val gmUri: Uri)