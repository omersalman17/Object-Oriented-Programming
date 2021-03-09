package filesprocessing.orders.alphabetOrders;

import filesprocessing.orders.SuperOrder;
import filesprocessing.orders.comparators.AbsolutePathComparator;

import java.io.File;
import java.util.Comparator;

public class AlphabetOrderByAbs implements SuperOrder {

    private Comparator<File> orderComparator = new AbsolutePathComparator();

    @Override
    public Comparator<File> getOrderComparator(){
        return this.orderComparator;
    }

}
