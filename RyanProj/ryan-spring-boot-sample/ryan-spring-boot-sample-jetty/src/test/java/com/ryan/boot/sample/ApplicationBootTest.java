package com.ryan.boot.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-11-19
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan
@SpringApplicationConfiguration(classes = ApplicationBootTest.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
@DirtiesContext
public class ApplicationBootTest {

    @Value("${server.port}")
    private Integer port;

    @Test
    public void testHome(){
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity("http://localhost:"+this.port, String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
    }
}
