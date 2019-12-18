open module pl.edu.pja.prz.receivables {
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires org.slf4j;
    requires java.annotation;
    requires java.persistence;
    requires commons.csv;
    requires java.sql;
    requires org.apache.commons.lang3;
    requires spring.tx;
    requires spring.data.jpa;
    requires spring.orm;
    requires spring.beans;

    exports pl.edu.pja.prz.receivables;
}