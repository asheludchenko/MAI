package oleg.osipenko.mai.data.repository.specification;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class StaticContentSpecification implements RequestSpecification<String> {
    private String item;

    public StaticContentSpecification(String item) {
        this.item = item;
    }

    @Override
    public boolean specified(String s)  {
        return s.equals(item);
    }

    public String getItem() {
        return item;
    }
}
