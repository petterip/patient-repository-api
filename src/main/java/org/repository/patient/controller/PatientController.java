package org.repository.patient.controller;

import org.repository.patient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/", produces = {"application/json", "application/xml"})
    public List<Patient> getPatients() {
        return patientService.getPatientList();
    }

    @GetMapping("/{patientId}")
    public Patient getPatient(@PathVariable(name = "patientId") Integer patientId) {
        return patientService.getPatient(patientId);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Patient postPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @DeleteMapping("/delete/{patientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable(name = "patientId") Integer patientId) {
        patientService.deletePatient(patientId);
    }

    @PutMapping(value = "/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Patient updatePatient(@RequestBody Patient patient,
                                 @PathVariable(name = "patientId") Integer id) {
        return patientService.replacePatient(id, patient);
    }
}
