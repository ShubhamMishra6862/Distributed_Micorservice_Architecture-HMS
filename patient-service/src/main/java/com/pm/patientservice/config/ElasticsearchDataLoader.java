package com.pm.patientservice.config;

import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.search.PatientDocument;
import com.pm.patientservice.search.PatientSearchMapper;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchDataLoader implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public ElasticsearchDataLoader(
            PatientRepository patientRepository,
            ElasticsearchOperations elasticsearchOperations) {

        this.patientRepository = patientRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public void run(String... args) {

        IndexOperations indexOps =
                elasticsearchOperations.indexOps(PatientDocument.class);

        // create index if missing
        if (!indexOps.exists()) {
            indexOps.create();
            indexOps.putMapping();
        }

        // check existing document count
        long existingCount = elasticsearchOperations.count(
                org.springframework.data.elasticsearch.core.query.Query.findAll(),
                PatientDocument.class
        );

        // skip if data already exists
        if (existingCount > 0) {
            System.out.println(
                    "Elasticsearch already contains data. Skipping sync.");
            return;
        }

        List<Patient> patients = patientRepository.findAll();

        List<PatientDocument> documents = patients.stream()
                .map(PatientSearchMapper::toDocument)
                .toList();

        elasticsearchOperations.save(documents);

        System.out.println("Patients synced to Elasticsearch.");
    }
}