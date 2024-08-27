package lk.ijse.posbackend.dao;

import lk.ijse.posbackend.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.posbackend.dao.custom.impl.ItemsDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getDAOFactory(){
       return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,ORDERS
    }

    public static SuperDAO getDAO(DAOTypes boType){
        switch(boType){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemsDAOImpl();
                default:return null;
        }

    }

}
