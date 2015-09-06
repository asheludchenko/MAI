package oleg.osipenko.mai.data.repository.specification

import oleg.osipenko.mai.data.repository.RequestSpecification

/**
 * Created by olegosipenko on 05.09.15.
 */
public class StaticContentSpecification : RequestSpecification<String> {
    override fun specified(s: String): Boolean {
        return false
    }
}
