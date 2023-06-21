package ch.ffhs.library.library.model;

import ch.ffhs.library.library.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class CustomerTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindByUsername_thenReturnCustomer(){
        //given
        Customer customer = new Customer();
        customer.setUsername("Alex");
        entityManager.persist(customer);
        entityManager.flush();

        //when
        Customer found = customerRepository.findByUsername(customer.getUsername());

        //then
        assertEquals(found.getUsername(), customer.getUsername());
    }

}