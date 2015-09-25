package oleg.osipenko.mai.data.repository.specification;

/**
 * Created by olegosipenko on 25.09.15.
 */
public class NewsContentSpecification implements RequestSpecification<String> {

    private String id;

    public NewsContentSpecification(String id) {
        this.id = id;
    }

    @Override
    public boolean specified(String item) {
        return item.equals(id);
    }

    @Override
    public String getItem() {
        return id;
    }
}
