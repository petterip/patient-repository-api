package org.repository.patient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Patient {
    @Id
    @JsonProperty(access = Access.READ_ONLY)
    @GeneratedValue
    private Integer id;

    @Schema(description = "Name",
            example = "John Smith", required = true)
    @NotBlank
    @Size(max = 100)
    private String name;

    @Schema(description = "Name of the project",
            example = "Jessica Abigail", required = false)
    @NotBlank
    @Size(max = 100)
    private String project;

    @Schema(description = "Name of the archive",
            example = "Jessica Abigail", required = false)
    @NotBlank
    @Size(max = 100)
    private String archive;

    @Schema(description = "Name of the attachment",
            example = "Jessica Abigail", required = false)
    @Size(max = 100)
    private String attachment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
