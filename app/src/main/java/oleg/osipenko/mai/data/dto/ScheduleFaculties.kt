package oleg.osipenko.mai.data.dto

import com.parse.ParseClassName
import com.parse.ParseObject

/**
 * Created by olegosipenko on 25.04.16.
 */
@ParseClassName("Schedule_faculties")
class ScheduleFaculties : ParseObject() {
    fun getFacultyName(): String? = getString("facultyName")

    fun getName(): String? = getString("name")

    fun logo(): String? = getParseFile("facultyLogo").url

    fun isFaculty(): Boolean? = getBoolean("isFaculty")
}
