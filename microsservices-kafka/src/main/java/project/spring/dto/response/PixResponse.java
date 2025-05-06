package project.spring.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
public record PixResponse(@JsonProperty("id") String id, @JsonProperty("customer") String customer,
			@JsonProperty("status") String status,  @JsonProperty("value") Long value,
			@JsonProperty("dueDate") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dueDate,
			@JsonProperty("billingType") String billingType) {
}
