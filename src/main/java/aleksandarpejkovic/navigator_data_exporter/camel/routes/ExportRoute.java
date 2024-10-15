package aleksandarpejkovic.navigator_data_exporter.camel.routes;

import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import aleksandarpejkovic.navigator_data_exporter.model.Candidate;
import aleksandarpejkovic.navigator_data_exporter.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExportRoute extends RouteBuilder {

    private final CandidateRepository candidateRepository;

    @Override
    public void configure() throws Exception {
        from("direct:exportCandidates")
                .process(exchange -> {
                    List<Candidate> candidates = candidateRepository.findAll();
                    exchange.getIn().setBody(convertToCsvData(candidates));
                })
                .marshal().csv()  // Convert to CSV format
                .to("file://{{output.directory}}?fileName=candidates.csv");  // Save to CSV file
    }

    private List<String[]> convertToCsvData(List<Candidate> candidates) {
        return candidates.stream()
                .map(candidate -> new String[]{
                        candidate.getFirstName(),
                        candidate.getLastName(),
                        candidate.getJmbg(),
                        String.valueOf(candidate.getBirthYear()),
                        candidate.getEmail(),
                        candidate.getPhone(),
                        candidate.getNote(),
                        String.valueOf(candidate.isHired()),
                        candidate.getLastModified().toString()
                })
                .toList();
    }
}

