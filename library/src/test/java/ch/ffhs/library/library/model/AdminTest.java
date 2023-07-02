package ch.ffhs.library.library.model;

import ch.ffhs.library.library.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class AdminTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void whenFindByUsername_thenReturnAdmin(){
        //given
        Admin admin = new Admin();
        admin.setUsername("Alex");
        entityManager.persist(admin);
        entityManager.flush();

        //when
        Admin found = adminRepository.findByUsername(admin.getUsername());

        //then
        assertEquals(found.getUsername(), admin.getUsername());
    }

}