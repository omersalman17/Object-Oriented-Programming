package filesprocessing.orders.alphabetOrders;

import filesprocessing.orders.SuperOrder;
import filesprocessing.orders.comparators.TypeComparator;

import java.io.File;
import java.util.Comparator;

public class AlphabetOrderByType implements SuperOrder {

    private Comparator<File> orderComparator = new TypeComparator();

    @Override
    public Comparator<File> getOrderComparator(){
        return this.orderComparator;
    }
}
