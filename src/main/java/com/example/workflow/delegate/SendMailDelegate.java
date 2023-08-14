package com.example.workflow.delegate;

import com.example.workflow.model.MailRequest;
import com.example.workflow.model.MailResponse;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SendMailDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        val client = WebClient.create();
        val email = (String) delegateExecution.getVariable("email");
        val message = MailResponse.builder()
                .email(email)
                .message("Ваш заказ собран")
                .build();
        val requestMono = client.post()
                .uri("http://localhost:8081/mail/send")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(message)
                .retrieve()
                .bodyToMono(MailRequest.class)
                .block();

        delegateExecution.setVariable("mail-answer", requestMono.getDone());
    }
}
