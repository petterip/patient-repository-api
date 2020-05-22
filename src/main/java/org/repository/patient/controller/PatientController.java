package org.repository.patient.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.repository.patient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@Tag(name = "patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Operation(
            summary = "List all patients",
            description = "Use this endpoint to fetch a list of all the patients",
            responses = {
                    @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = Patient.class))),
                            responseCode = "200")})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> getPatients() {
        return patientService.getPatientList();
    }

    @Operation(
            summary = "Find a patient",
            description = "Use this endpoint to find an existing patient by id",
            responses = {
                    @ApiResponse(content = @Content(schema = @Schema(implementation = Patient.class)), responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "Patient with given id does not exist")})
    @GetMapping(value = "/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient getPatient(@Parameter(description = "Id of the patient record to be fetched", required = true)
                              @PathVariable(name = "patientId") Integer patientId) {
        return patientService.getPatient(patientId);
    }

    @Operation(
            summary = "Create a new patient",
            description = "Use this endpoint to create a new patient record",
            responses = {
                    @ApiResponse(content = @Content(schema = @Schema(implementation = Patient.class)),
                            responseCode = "201", description = "Success")})
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient postPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @Operation(
            summary = "Delete a patient",
            description = "Delete an existing patient",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Patient has been deleted"),
                    @ApiResponse(responseCode = "404", description = "Patient with given id does not exist")})
    @DeleteMapping("/delete/{patientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable(name = "patientId") Integer patientId) {
        patientService.deletePatient(patientId);
    }

    @Operation(
            summary = "Update a patient",
            description = "Use this endpoint to update an existing patient record",
            responses = {
                    @ApiResponse(content = @Content(schema = @Schema(implementation = Patient.class)),
                            responseCode = "201", description = "Success")})
    @PutMapping(value = "/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient updatePatient(@RequestBody Patient patient,
                                 @Parameter(description = "Id of the patient record to be updated", required = true)
                                 @PathVariable(name = "patientId") Integer id) {
        return patientService.updatePatient(id, patient);
    }
}
