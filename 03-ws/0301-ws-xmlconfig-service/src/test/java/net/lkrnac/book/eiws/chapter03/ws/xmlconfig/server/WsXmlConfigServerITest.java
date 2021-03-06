package net.lkrnac.book.eiws.chapter03.ws.xmlconfig.server;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseActions;
import org.springframework.ws.test.server.ResponseMatchers;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:web-service-config.xml" })
public class WsXmlConfigServerITest extends AbstractTestNGSpringContextTests {
  @Autowired
  private ApplicationContext applicationContext;

  @Test
  public void testGetUserDetails() throws IOException {
    // GIVEN
    MockWebServiceClient wsClient =
        MockWebServiceClient.createClient(applicationContext);

    RequestCreator requestCreator =
        RequestCreators.withPayload(new ClassPathResource(
            "testRequest-success.xml"));

    // WHEN
    ResponseActions response = wsClient.sendRequest(requestCreator);

    // THEN
    response.andExpect(ResponseMatchers.noFault()).andExpect(
        ResponseMatchers.payload(new ClassPathResource("testResponse.xml")));
  }
}
