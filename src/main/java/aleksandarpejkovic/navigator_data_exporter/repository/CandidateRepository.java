package aleksandarpejkovic.navigator_data_exporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aleksandarpejkovic.navigator_data_exporter.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
