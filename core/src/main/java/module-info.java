open module core {

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
	requires account;


	requires receivables;
	requires groups;
	requires payments;
}