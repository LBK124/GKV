package gKVServer.aplicationlogic.impl;

import gKVServer.aplicationlogic.Cellarmanagement;
import gKVServer.aplicationlogic.Usermanagement;
import gKVServer.database.CellarDB;
/**
 * Autor: Brandes Lukas
 */

public class CellarmanagementTestWithDummy extends CellarmanagemenTest{

    @Override
    public Cellarmanagement getCellarmanagement(){

        Usermanagement usermanagement = new UsermanagementImpl();
        usermanagement.setUserDB(new UserDBDummy());
        Cellarmanagement cellarmanagement = new CellarmanagementImpl(usermanagement);
        CellarDB cellarDB = new CellarDBDummy();
        cellarmanagement.setCellarDB(cellarDB);

        return cellarmanagement;


    }

}
