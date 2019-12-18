open module pl.edu.pja.prz.payments {

	requires java.persistence;
	requires java.sql;
	requires java.xml.bind;
	requires net.bytebuddy;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.core;
	requires spring.data.jpa;
	requires com.fasterxml.classmate;
	requires org.hibernate.orm.core;
	requires spring.tx;
	requires spring.orm;
	requires spring.beans;
	requires org.slf4j;
	requires java.annotation;
	requires spring.security.core;
	requires spring.data.commons;


	exports pl.edu.pja.prz.payments;
}