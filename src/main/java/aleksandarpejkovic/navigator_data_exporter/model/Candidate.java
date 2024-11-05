package aleksandarpejkovic.navigator_data_exporter.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Model class representing a candidate.
 */
@Entity
@Table(name = "candidates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(name = "jmbg", unique = true, nullable = false, length = 13)
    private String jmbg;

    private int birthYear;

    private String email;

    private String phone;

    private String note;

    private boolean hired;

    private LocalDateTime lastModified;
}

