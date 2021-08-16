package gKVServer.aplicationlogic.impl;

import data.PersonTO;
import gKVServer.aplicationlogic.ManagementExcange;
import gKVServer.aplicationlogic.Usermanagement;

/**
 * Autor: Brandes Lukas
 */
public class ManagementExcangeImpl implements ManagementExcange {

    private Usermanagement usermanagement = null;
    public ManagementExcangeImpl(Usermanagement usermanagement){this.usermanagement = usermanagement;}
    @Override
    public boolean checkUser(String userID, String userPW) {
        PersonTO user = usermanagement.getUser(userID);
        return user.password.equals(userPW);
    }

    @Override
    public boolean checkAdmin(String adminID, String adminPW) {
        PersonTO admin = usermanagement.getUser(adminID);
        if(admin.password.equals(adminPW)){
            return admin.admin;
        }
        return false;
    }

    @Override
    public boolean createOrder(String userID,String userPSW,String drinkName,int amount,String bottleTpye) {
        return usermanagement.createOrderUser(userID,userPSW,drinkName,amount,bottleTpye);
    }

    @Override
    public int getCellarID(String userID) {
        return usermanagement.getUser(userID).cellerId;
    }
}
