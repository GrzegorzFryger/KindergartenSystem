open module groups {
    requires java.persistence;
    requires spring.context;
    requires org.slf4j;
    requires java.annotation;
	requires spring.data.jpa;
	requires spring.tx;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.orm;
	requires spring.beans;
	requires java.sql;

	exports pl.edu.pja.prz.groups;
}