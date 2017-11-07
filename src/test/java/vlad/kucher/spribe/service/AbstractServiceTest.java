package vlad.kucher.spribe.service;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Vlad on 07.11.2017.
 */
@ContextConfiguration("classpath:spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
}
