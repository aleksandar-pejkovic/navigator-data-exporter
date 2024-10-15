package aleksandarpejkovic.navigator_data_exporter.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final ProducerTemplate producerTemplate;

    /**
     * Trigger export of all candidates to a CSV file.
     *
     * @return Message indicating that export has been triggered.
     */
    @GetMapping("/export")
    public String exportCandidatesToCsv() {
        producerTemplate.sendBody("direct:exportCandidates", null);
        return "Export to CSV has been triggered.";
    }
}
