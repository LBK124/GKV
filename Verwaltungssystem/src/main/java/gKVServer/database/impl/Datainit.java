package gKVServer.database.impl;

import gKVServer.database.UserDB;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.logging.Logger;
//Autor Richard Moeckel
public class Datainit implements Data{
    private static final Logger LOGGER = Logger.getLogger(Datainit.class.getName());

    private final DBimpl cworker;
    private static ApplicationContext applicationContext;


    public Datainit() {
        if (applicationContext == null)
            applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        cworker = applicationContext.getBean(DBimpl.class);
        cworker.createcellars();
        //cworker.fillwithtestdata(test);

    }
    public Datainit(String applicationContextFile) {
        System.out.println((new File(applicationContextFile)).getAbsolutePath());
        System.out.println((new File(applicationContextFile)).exists());
        if (applicationContext == null)
            applicationContext = new ClassPathXmlApplicationContext(applicationContextFile);

        cworker = applicationContext.getBean(DBimpl.class);
        cworker.createcellars();
        //cworker.fillwithtestdata();

    }

  @Override
  public DBimpl getCellarData() {
      return cworker;
  }

  @Override
  public UserDB getUserData() {
        return cworker;
    }

}

