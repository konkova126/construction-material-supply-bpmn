package com.example.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MailRequest implements Serializable {
    @JsonProperty("done")
    String done;
}
