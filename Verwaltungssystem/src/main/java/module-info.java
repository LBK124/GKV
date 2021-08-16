module gKVServer {requires java.rmi; requires java.desktop;
    requires java.persistence;
    requires spring.data.jpa;
    requires spring.context;
    requires java.logging;
    requires spring.beans;
    requires com.h2database;
    exports gKVServer.Servercommunication;
    exports data;
}