open module receivables {
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires org.slf4j;
    requires java.annotation;
    requires java.persistence;

    exports pl.edu.pja.prz.receivables;
}