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

    public List<Patient> getPatientList() {
        return patientRepository.findAll();
    }

    public Patient getPatient(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient replacePatient(Integer id, Patient newPatient) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setName(newPatient.getName());
                    patient.setProject(newPatient.getProject());
                    patient.setArchive(newPatient.getArchive());
                    return patientRepository.save(patient);
                })
                .orElseGet(() -> {
                    newPatient.setId(id);
                    return patientRepository.save(newPatient);
                });
    }

    public void deletePatient(Integer id) {
        try {
            patientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PatientNotFoundException(id);
        }
    }
}
