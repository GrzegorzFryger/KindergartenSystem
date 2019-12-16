open module pl.edu.pja.prz.core {

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
	requires pl.edu.pja.prz.account;
	requires receivables;
	requires payments;
}