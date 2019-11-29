package pl.edu.pja.prz.groups;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@SpringBootTest
@ContextConfiguration(classes = {GroupsModule.class})
public class GroupsModuleTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {

    }
}
