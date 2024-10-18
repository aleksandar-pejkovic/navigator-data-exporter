package aleksandarpejkovic.navigator_data_exporter.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExportRoute extends RouteBuilder {

    private static final String DIRECT_EXPORT_CANDIDATES = "direct:exportCandidates";
    private static final String JDBC_DATA_SOURCE = "jdbc:dataSource";
    private static final String FILE_NAME = "hired_candidates.csv";
    private static final String FILE_OUTPUT_DIRECTORY = "file://{{output.directory}}?fileName=" + FILE_NAME;
    private static final String QUERY = """
            SELECT first_name, last_name, phone, email
            FROM candidates
            WHERE hired = TRUE
            """;

    @Override
    public void configure() throws Exception {
        from(DIRECT_EXPORT_CANDIDATES)
                .setBody(constant(QUERY))
                .to(JDBC_DATA_SOURCE)
                .marshal().csv()
                .to(FILE_OUTPUT_DIRECTORY);
    }
}

