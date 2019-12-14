open module groups {
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires org.slf4j;
    requires java.annotation;
    requires java.persistence;
    requires java.sql;
    requires org.apache.commons.lang3;

    exports pl.edu.pja.prz.groups;
}