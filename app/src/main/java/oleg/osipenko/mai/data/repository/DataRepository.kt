package oleg.osipenko.mai.data.repository


import oleg.osipenko.mai.data.dataModel.ListContent
import oleg.osipenko.mai.data.dataModel.StaticContent
import oleg.osipenko.mai.data.dataModel.StaticListContent
import oleg.osipenko.mai.data.repository.specification.ListContentSpecification
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification
import oleg.osipenko.mai.data.repository.specification.StaticListContentSpecification

/**
 * Created by olegosipenko on 05.09.15.
 */
public interface DataRepository {

    public fun getStaticContent(specification: StaticContentSpecification): List<StaticContent>?

    public fun getListContent(specification: ListContentSpecification): List<ListContent>?

    public fun getStaticListContent(specification: StaticListContentSpecification): List<StaticListContent>?
}
