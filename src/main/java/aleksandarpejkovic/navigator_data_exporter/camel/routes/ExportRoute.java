package aleksandarpejkovic.navigator_data_exporter.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExportRoute extends RouteBuilder {

    /**
     * name of the route
     */
    private static final String DIRECT_EXPORT_CANDIDATES = "direct:exportCandidates";
    /**
     * name of the data source
     */
    private static final String JDBC_DATA_SOURCE = "jdbc:dataSource";
    /**
     * name of the file
     */
    private static final String FILE_NAME = "hired_candidates.csv";
    /**
     * output directory
     */
    private static final String FILE_OUTPUT_DIRECTORY = "file://{{output.directory}}?fileName=" + FILE_NAME;

    /**
     * query to export candidates who are hired
     */
    private static final String QUERY = """
            SELECT first_name, last_name, phone, email
            FROM candidates
            WHERE hired = TRUE
            """;

    /**
     * Export all hired candidates to a CSV file.
     *
     * @throws Exception if a problem occurs while setting up the route
     */
    @Override
    public void configure() throws Exception {
        from(DIRECT_EXPORT_CANDIDATES)
                .setBody(constant(QUERY))
                .to(JDBC_DATA_SOURCE)
                .marshal().csv()
                .to(FILE_OUTPUT_DIRECTORY);
    }
}

