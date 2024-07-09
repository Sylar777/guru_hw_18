package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAuth {
    private String userId;
    private String username;
    private String password;
    private String token;
    private Date expires;
    @JsonProperty("created_date")
    private Date createdDate;
    private boolean isActive;
}
