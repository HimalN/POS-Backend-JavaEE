package lk.ijse.posbackend.bo;

import lk.ijse.posbackend.bo.custom.impl.CustomerBOImpl;
import lk.ijse.posbackend.bo.custom.impl.ItemBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory ==null)?boFactory=new BOFactory():boFactory;
    }

    public enum BOTypes {
        CUSTOMER,ITEMS,ORDERS
    }
    public SuperBO getBO(BOTypes boType) {
        switch (boType) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEMS:
                return new ItemBOImpl();
            case ORDERS:
                //return new OrdersBOImpl();
            default:
                return null;
        }
    }
}
