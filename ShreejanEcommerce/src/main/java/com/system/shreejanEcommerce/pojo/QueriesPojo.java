package com.system.shreejanEcommerce.pojo;

import com.system.shreejanEcommerce.entity.Queries;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueriesPojo {
    private Integer id;
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotEmpty(message = "E-mail can't be empty")
    private String email;
    @NotEmpty(message = "Subject can't be empty")
    private String subject;
    @NotEmpty(message = "Message can't be empty")
    private String message;

    public QueriesPojo(Queries queries) {
        this.id = queries.getId();
        this.name = queries.getName();
        this.email = queries.getEmail();
        this.subject = queries.getSubject();
        this.message = queries.getMessage();
    }
}
