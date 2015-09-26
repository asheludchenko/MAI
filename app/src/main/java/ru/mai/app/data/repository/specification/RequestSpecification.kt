package ru.mai.app.data.repository.specification

/**
 * Created by olegosipenko on 05.09.15.
 */
public interface RequestSpecification<T> {

    public fun specified(t: T): Boolean

    public fun getItem() : T
}
