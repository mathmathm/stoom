package com.stoom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stoom.model.Endereco;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EnderecoRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotBlank
    @JsonProperty("street_name")
    public String streetName;

    @NotNull
    @JsonProperty("number")
    public Integer number;

    public Integer complement;

    @NotBlank
    @JsonProperty("neighbourhood")
    public String neighbourhood;

    public String city;

    public String state;

    public String country;
    public int zipcode;
    public String latitude;
    public String longitude;

    public Endereco mapToEntity() {
        return Endereco.builder()
                .id(this.id)
                .streetName(this.streetName)
                .number(this.number)
                .complement(this.complement)
                .neighbourhood(this.neighbourhood)
                .city(this.city)
                .state(this.state)
                .country(this.country)
                .zipcode(this.zipcode)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }
}
