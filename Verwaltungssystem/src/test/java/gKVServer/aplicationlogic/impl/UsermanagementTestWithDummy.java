package gKVServer.aplicationlogic.impl;

import gKVServer.aplicationlogic.Usermanagement;
import gKVServer.database.UserDB;
/**
 * Autor: Brandes Lukas
 */
public class UsermanagementTestWithDummy extends UsermanagementTest {

    @Override
    public Usermanagement getUsermanagement(){


        Usermanagement usermanagement = new UsermanagementImpl();
        UserDB userDB = new UserDBDummy();
        usermanagement.setUserDB(userDB);

        return usermanagement;
    }
}
