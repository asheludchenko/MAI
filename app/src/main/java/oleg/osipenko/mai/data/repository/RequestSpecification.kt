package oleg.osipenko.mai.data.repository

/**
 * Created by olegosipenko on 05.09.15.
 */
public interface RequestSpecification<T> {

    public fun specified(t: T): Boolean
}
