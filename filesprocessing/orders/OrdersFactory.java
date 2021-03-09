package filesprocessing.orders;

import filesprocessing.exceptions.ErrorTypeOneException;
import filesprocessing.orders.alphabetOrders.AlphabetOrderByAbs;
import filesprocessing.orders.alphabetOrders.AlphabetOrderByType;
import filesprocessing.orders.numericalOrders.NumericalOrderBySize;

public class OrdersFactory {

    public static SuperOrder createOrder(String [] orderCommandLineStrings ,
                                         int orderCommandLineNumber)throws ErrorTypeOneException {
        if(orderCommandLineStrings.length > 2){
            throw new ErrorTypeOneException(orderCommandLineNumber);
        }
        else if(orderCommandLineStrings.length >= 1) {
            String orderName = orderCommandLineStrings[0];
            String lastParameter = orderCommandLineStrings[orderCommandLineStrings.length - 1];
            SuperOrder order;
            switch (orderName) {
                case "abs":
                    order = new AlphabetOrderByAbs();
                    if(lastParameter.equals("REVERSE"))
                        order = new ReversedOrderDecorator(order);
                    return order;
                case "type":
                    order = new AlphabetOrderByType();
                    if(lastParameter.equals("REVERSE"))
                        order = new ReversedOrderDecorator(order);
                    return order;
                case "size":
                    order = new NumericalOrderBySize();
                    if(lastParameter.equals("REVERSE"))
                        order = new ReversedOrderDecorator(order);
                    return order;
                case "FILTER":
                    order = new AlphabetOrderByAbs();
                    return order;
            }
        }
        throw new ErrorTypeOneException(orderCommandLineNumber);
    }
}
