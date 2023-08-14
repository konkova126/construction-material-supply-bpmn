package com.example.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MailResponse implements Serializable {
    @JsonProperty("email")
    String email;

    @JsonProperty("message")
    String message;
}
