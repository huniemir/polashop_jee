package polashop_history;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import polashop.dao.History;

public class LazySorter implements Comparator<History> {

    private String sortField;
    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(History history1, History history2) {
        try {
            Object value1 = history1.getClass().getField(sortField).get(history1);
            Object value2 = history2.getClass().getField(sortField).get(history2);

            int value = ((Comparable)value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}