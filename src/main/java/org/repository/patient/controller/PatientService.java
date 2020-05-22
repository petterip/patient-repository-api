package org.repository.patient.controller;

import org.repository.patient.exceptions.PatientNotFoundException;
import org.repository.patient.model.Patient;
import org.repository.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Find all patients from the repository.
     *
     * @return list of {@link Patient}
     */
    public List<Patient> getPatientList() {
        return patientRepository.findAll();
    }

    /**
     * Find a {@link Patient} with the given id.
     *
     * @param id of the patient
     * @return {@link Patient} entity
     */
    public Patient getPatient(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    /**
     * Create a new {@link Patient}.
     *
     * @param patient to be added
     * @return the added {@link Patient}
     */
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    /**
     * Update a {@link Patient} entity with the given id. If no patient is found with the id,
     * new is created.
     *
     * @param id            of the patient
     * @param updatePatient patient entity to be replaced with
     * @return updated patient entity {@link Patient}
     */
    public Patient updatePatient(Integer id, Patient updatePatient) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setName(updatePatient.getName());
                    patient.setProject(updatePatient.getProject());
                    patient.setArchive(updatePatient.getArchive());
                    patient.setAttachment(updatePatient.getAttachment());
                    return patientRepository.save(patient);
                })
                .orElseGet(() -> {
                    final Patient patient = new Patient();
                    patient.setId(id);
                    patient.setName(updatePatient.getName());
                    patient.setProject(updatePatient.getProject());
                    patient.setArchive(updatePatient.getArchive());
                    return patientRepository.save(patient);
                });
    }

    /**
     * Remove a {@link Patient} entity having the given id.
     *
     * @param id of the patient entity to be removed
     */
    public void deletePatient(Integer id) {
        try {
            patientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PatientNotFoundException(id);
        }
    }
}
