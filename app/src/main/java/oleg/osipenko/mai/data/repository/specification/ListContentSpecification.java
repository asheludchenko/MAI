package oleg.osipenko.mai.data.repository.specification;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class ListContentSpecification implements RequestSpecification<String> {

    private String menuItem;

    public ListContentSpecification(String menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public boolean specified(String string) {
        return string.equals(menuItem);
    }

    public String getParameter() {
        return menuItem;
    }
}
