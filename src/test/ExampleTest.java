import com.sun.security.auth.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.itmo.SpringBootSecurityJwtApplication;
import ru.itmo.dto.OrderDto;
import ru.itmo.services.OrderService;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootSecurityJwtApplication.class)
public class ExampleTest {
    @Autowired
    OrderService orderService;

    @Before
    public void setUp() {

    }

    @Test
    public void testSaveBank() {
        Iterable<OrderDto> orders = orderService.getAllOrders(new UserPrincipal("user"));
        assertNotNull(orders);
    }
}
