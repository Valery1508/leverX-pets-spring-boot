package ru.leverx.pets.pets;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "dev")
class PetsApplicationTests {

    @Test
    void contextLoads() {
    }

}
