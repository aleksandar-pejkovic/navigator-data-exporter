package aleksandarpejkovic.navigator_data_exporter.camel.routes;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest(
        properties = {"camel.springboot.name=customQuery"}
)
class ExportRouteTest {

    @Autowired
    ProducerTemplate producerTemplate;

    @EndpointInject("mock:jdbc:dataSource")
    MockEndpoint jdbcMockEndpoint;

    @EndpointInject("mock:file:output")
    MockEndpoint fileMockEndpoint;

    private static final String QUERY = """
            SELECT first_name, last_name, phone, email
            FROM candidates
            WHERE hired = TRUE
            """;

    @Configuration
    static class TestConfig {

        @Bean
        RoutesBuilder route() {
            return new ExportRoute() {
                @Override
                public void configure() throws Exception {
                    from("direct:exportCandidates")
                            .setBody(constant(QUERY))
                            .to("mock:jdbc:dataSource")  // Replace the real data source with a mock
                            .marshal().csv()
                            .to("mock:file:output");     // Replace file output with a mock endpoint
                }
            };
        }
    }

    @Test
    public void shouldAutowireProducerTemplate() {
        assertNotNull(producerTemplate);
    }

    @Test
    public void shouldQueryCandidatesAndExportToCsv() throws Exception {
        String mockData = "first_name,last_name,phone,email\nJohn,Doe,1234567890,john.doe@example.com";
        jdbcMockEndpoint.expectedBodiesReceived(QUERY); // Check if the query was set correctly
        fileMockEndpoint.expectedMessageCount(1);       // Expect one file to be written
        fileMockEndpoint.expectedBodiesReceived(mockData); // Simulate expected CSV content

        producerTemplate.sendBody("direct:exportCandidates", null); // Trigger the route

        jdbcMockEndpoint.assertIsSatisfied();
        fileMockEndpoint.assertIsSatisfied();
    }
}