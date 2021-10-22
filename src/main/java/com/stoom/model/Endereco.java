package com.stoom.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Endereco")
@Data
@NamedQuery(name = "Endereco.findAll", query = "SELECT c FROM Endereco c")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @NotBlank
    @JsonProperty("streetName")
    public String streetName;

    @NotNull
    @JsonProperty("number")
    public Integer number;
    public Integer complement;

    @NotBlank
    @JsonProperty("neighbourhood")
    public String neighbourhood;

    @NotBlank
    @JsonProperty("city")
    public String city;

    @NotBlank
    @JsonProperty("state")
    public String state;

    @NotBlank
    @JsonProperty("country")
    public String country;

    @NotNull
    @JsonProperty("zipcode")
    public int zipcode;

    public String latitude;
    public String longitude;

}
