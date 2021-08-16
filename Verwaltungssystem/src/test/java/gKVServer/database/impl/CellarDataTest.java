package gKVServer.database.impl;
//Autor Richard Moeckel
public class CellarDataTest extends CellarBDimplTest {
private final Datainit cdb = new Datainit("applicationContext.xml");

    @Override
    protected Data getData() {
        return cdb;
    }
}


