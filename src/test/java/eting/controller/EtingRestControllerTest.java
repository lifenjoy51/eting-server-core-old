package eting.controller;

import eting.EtingApplication;
import eting.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.Date;

import static eting.util.Util.APPLICATION_JSON_UTF8;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EtingApplication.class)
@WebAppConfiguration
public class EtingRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void setUp() {
    // We have to reset our mock between tests because the mock objects
    // are managed by the Spring container. If we would not reset them,
    // stubbing and verified behavior would "leak" from one test to another.

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    @Transactional
    public void testRegistration() throws Exception {

        System.err.println(new Date().toString());

        String result = mockMvc.perform(post("/device")
            .param("uuid", "16b72c7c-6d8d-471d-9615-bb06d40ea748")
            .param("regDt", Util.getDtStr())
            .param("os", "A")
            .param("pushKey", "APA91bFrp1f8U1WfpB62vCVDX3qEv8SThBGng5yfpQwM3jk9pLuSijjMPpejp-1MSSulynAYjjwWkrTHxueS0MH8bMWRf4kIQcMLtW8LHKrH76MachQs_OL7AEE2c-PR0VmnIvnctfZXkTplxj69I0LuEBYB5Ch2vg")
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$uuid", is("16b72c7c-6d8d-471d-9615-bb06d40ea748")))
        .andReturn().getResponse().getContentAsString();

        System.err.println(result);

    }
}