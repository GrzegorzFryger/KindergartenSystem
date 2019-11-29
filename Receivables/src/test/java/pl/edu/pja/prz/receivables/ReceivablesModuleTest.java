package pl.edu.pja.prz.receivables;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {ReceivablesModule.class})
class ReceivablesModuleTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {
    }
}