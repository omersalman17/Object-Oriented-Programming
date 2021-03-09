package filesprocessing.orders.numericalOrders;

import filesprocessing.orders.SuperOrder;
import filesprocessing.orders.comparators.SizeComparator;

import java.io.File;
import java.util.Comparator;

public class NumericalOrderBySize implements SuperOrder {
    private Comparator<File> orderComparator = new SizeComparator();

    @Override
    public Comparator<File> getOrderComparator(){
        return this.orderComparator;
    }
}
