package ru.mai.app.data.dto

import com.parse.ParseClassName
import com.parse.ParseObject

/**
 * Created by olegosipenko on 26.04.16.
 */
@ParseClassName("Shedule_courses")
class ScheduleCourses: ParseObject() {
    fun getFacultyId() = getString("facultyId")
    fun getCourseId() = getString("courseId")
    fun getUrl() = getString("url")
    fun getName() = getString("name")
}