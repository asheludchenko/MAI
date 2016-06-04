package oleg.osipenko.mai.data.dto

import com.parse.ParseClassName
import com.parse.ParseObject

/**
 * Created by olegosipenko on 26.04.16.
 */
@ParseClassName("Shedule_courses")
class ScheduleCourses: ParseObject() {
    fun getFacultyId(): String? = getString("facultyId")
    fun getCourseId(): String? = getString("courseId")
    fun getUrl(): String? = getString("url")
    fun getName(): String? = getString("name")
}