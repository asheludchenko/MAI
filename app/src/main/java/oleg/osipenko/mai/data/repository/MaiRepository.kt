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
public class MaiRepository : DataRepository {
    override fun getStaticContent(specification: StaticContentSpecification): List<StaticContent>? {
        return null
    }

    override fun getListContent(specification: ListContentSpecification): List<ListContent>? {
        return null
    }

    override fun getStaticListContent(specification: StaticListContentSpecification): List<StaticListContent>? {
        return null
    }
}
