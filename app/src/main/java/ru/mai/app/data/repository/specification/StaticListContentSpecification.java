package ru.mai.app.data.repository.specification;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class StaticListContentSpecification implements RequestSpecification<String> {
    private String item;

    public StaticListContentSpecification(String item) {
        this.item = item;
    }

    @Override
    public boolean specified(String s) {
        return s.equals(item);
    }

    @Override
    public String getItem() {
        return item;
    }
}
