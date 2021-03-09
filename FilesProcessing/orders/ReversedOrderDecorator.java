package filesprocessing.orders;

import java.io.File;
import java.util.Comparator;

public class ReversedOrderDecorator implements SuperOrder {
    private final SuperOrder currentOrder;

    public ReversedOrderDecorator(SuperOrder order){
        this.currentOrder = order;
    }

    @Override
    public Comparator<File> getOrderComparator(){
        return this.currentOrder.getOrderComparator().reversed();
    }
}
