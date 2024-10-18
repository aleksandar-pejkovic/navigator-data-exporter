package aleksandarpejkovic.navigator_data_exporter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aleksandarpejkovic.navigator_data_exporter.model.Candidate;
import aleksandarpejkovic.navigator_data_exporter.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private static final String DIRECT_EXPORT_CANDIDATES = "direct:exportCandidates";
    private static final String EXPORT_DATA_RETURN_MESSAGE = "Export to CSV has been triggered.";

    private final ProducerTemplate producerTemplate;
    private final CandidateRepository repository;

    /**
     * Trigger export of all candidates to a CSV file.
     *
     * @return Message indicating that export has been triggered.
     */
    @GetMapping("/export")
    public ResponseEntity<Map<String, String>> exportCandidatesToCsv() {
        producerTemplate.sendBody(DIRECT_EXPORT_CANDIDATES, null);
        Map<String, String> response = new HashMap<>();
        response.put("message", EXPORT_DATA_RETURN_MESSAGE);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all candidates.
     *
     * @return List of all candidates.
     */
    @GetMapping
    public List<Candidate> getAllCandidates() {
        return repository.findAll();
    }

    /**
     * Get a candidate by ID.
     *
     * @param id Candidate ID.
     * @return Candidate with the specified ID.
     */
    @GetMapping("/{id}")
    public Candidate getCandidateById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    /**
     * Create a new candidate.
     *
     * @param candidate Candidate to be created.
     * @return Created candidate.
     */
    @PostMapping
    public Candidate createCandidate(@RequestBody Candidate candidate) {
        return repository.save(candidate);
    }

    /**
     * Update an existing candidate.
     *
     * @param id               Candidate ID.
     * @param updatedCandidate Candidate data to update.
     * @return Updated candidate.
     */
    @PutMapping("/{id}")
    public Candidate updateCandidate(@PathVariable Long id, @RequestBody Candidate updatedCandidate) {
        return repository.findById(id)
                .map(candidate -> {
                    candidate.setFirstName(updatedCandidate.getFirstName());
                    candidate.setLastName(updatedCandidate.getLastName());
                    candidate.setJmbg(updatedCandidate.getJmbg());
                    candidate.setBirthYear(updatedCandidate.getBirthYear());
                    candidate.setEmail(updatedCandidate.getEmail());
                    candidate.setPhone(updatedCandidate.getPhone());
                    candidate.setNote(updatedCandidate.getNote());
                    candidate.setHired(updatedCandidate.isHired());
                    candidate.setLastModified(updatedCandidate.getLastModified());
                    return repository.save(candidate);
                })
                .orElseThrow();
    }

    /**
     * Delete a candidate by ID.
     *
     * @param id Candidate ID.
     * @return Response entity indicating the outcome of the operation.
     */
    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
