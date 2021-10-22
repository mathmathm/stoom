package com.stoom.model;

import lombok.*;
import javax.persistence.*;

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
    public String streetName;
    public Integer number;
    public Integer complement;
    public String neighbourhood;
    public String city;
    public String state;
    public String country;
    public int zipcode;
    public String latitude;
    public String longitude;

}
