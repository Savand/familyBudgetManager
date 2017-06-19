package org.andsav.family_budget_manager.web.user;

import static org.andsav.family_budget_manager.Profiles.ACTIVE_DB;
import static org.andsav.family_budget_manager.Profiles.DB_IMPLEMENTATION;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.andsav.family_budget_manager.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import javax.annotation.PostConstruct;
import javax.net.ssl.SSLEngineResult.Status;

@ContextConfiguration({
    "classpath:spring/spring-app.xml",
    "classpath:spring/spring-db.xml",
    "classpath:spring/spring-mvc.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles({ACTIVE_DB, DB_IMPLEMENTATION})
public class AdminUserRestControllerTest {

    public static final String REST_URL = "/rest/admin/users";
    
    protected MockMvc mockMvc;
    
    @Autowired
    private UserService service;
   
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @PostConstruct
    void postConcstruct(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Before
    public void setUp(){
        service.evictCache();
    }
    
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//        .andExpect(matcher);
    }

    @Test
    public void testGet() {
        fail("Not yet implemented");
    }

    @Test
    public void testDelete() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetByEmail() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    public void testCreate() {
        fail("Not yet implemented");
    }

}
