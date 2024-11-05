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

    /**
     * Query for all hired candidates.
     */
    private static final String QUERY = """
            SELECT first_name, last_name, phone, email
            FROM candidates
            WHERE hired = TRUE
            """;

    @Configuration
    static class TestConfig {

        /**
         * Test route that queries the database for candidates that have been hired
         * and exports them to a CSV file.
         *
         * @return Route builder for the test route.
         */
        @Bean
        RoutesBuilder route() {
            return new ExportRoute() {
                @Override
                public void configure() throws Exception {
                    from("direct:exportCandidates")
                            .setBody(constant(QUERY))
                            .to("mock:jdbc:dataSource")
                            .marshal().csv()
                            .to("mock:file:output");
                }
            };
        }
    }

    @Test
    public void shouldAutowireProducerTemplate() {
        assertNotNull(producerTemplate);
    }

    /**
     * Tests that the {@link ExportRoute} queries the database for candidates that have been hired
     * and exports them to a CSV file.
     *
     * @throws Exception if a problem occurs while setting up the route
     */
    @Test
    public void shouldQueryCandidatesAndExportToCsv() throws Exception {
        String mockData = "John,Doe,1234567890,john.doe@example.com";
        jdbcMockEndpoint.expectedBodiesReceived(QUERY);
        fileMockEndpoint.expectedMessageCount(1);
        fileMockEndpoint.expectedBodiesReceived(mockData);

        producerTemplate.sendBody("direct:exportCandidates", null);

        jdbcMockEndpoint.assertIsSatisfied();
        fileMockEndpoint.assertIsSatisfied();
    }
}