package gKVServer.database.impl;

import gKVServer.database.CellarDB;
import gKVServer.database.UserDB;
import org.h2.engine.User;
//Autor Richard Moeckel
public interface Data {


    CellarDB getCellarData();
    UserDB getUserData();

}
