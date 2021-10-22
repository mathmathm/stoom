package com.stoom.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.stoom.dto.EnderecoRequest;
import com.stoom.model.Endereco;
import com.stoom.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public List<Endereco> findAll() {
        return repository.findAll();
    }

    public Endereco findById(Integer id) {
        return repository.findById(id).get();
    }

    public Endereco save(EnderecoRequest endereco) throws InterruptedException, ApiException, IOException {
        if (endereco.getLatitude() != null && endereco.getLongitude() != null) {
            latitudeAndLongitudeNull(endereco);
        }
        Endereco model = endereco.mapToEntity();
        return repository.save(model);
    }

    public Endereco update(Integer id, EnderecoRequest enderecoRequest) throws InterruptedException, ApiException, IOException {
        Endereco endereco = repository.findById(id).get();
        if (endereco == null) {
            return null;
        }
        endereco.setStreetName(enderecoRequest.getStreetName());
        endereco.setNumber(enderecoRequest.getNumber());
        endereco.setComplement(enderecoRequest.getComplement());
        endereco.setNeighbourhood(enderecoRequest.getNeighbourhood());
        endereco.setCity(enderecoRequest.getCity());
        endereco.setState(enderecoRequest.getState());
        endereco.setCountry(enderecoRequest.getCountry());
        endereco.setZipcode(enderecoRequest.getZipcode());
        endereco.setState(enderecoRequest.getState());
        if (enderecoRequest.getLatitude() == null && enderecoRequest.getLongitude() == null) {
            latitudeAndLongitudeNull(enderecoRequest);
        } else {
            endereco.setLatitude(enderecoRequest.getLatitude());
            endereco.setLongitude(enderecoRequest.getLongitude());
        }

        return repository.save(endereco);
    }

    public void delete(Integer id) {
        Endereco endereco = new Endereco();
        endereco.setId(id);
        repository.delete(endereco);
    }

    private void latitudeAndLongitudeNull(EnderecoRequest endereco) throws ApiException, InterruptedException, IOException {
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw").build();
        GeocodingResult[] results = GeocodingApi.geocode(context, endereco.getStreetName() + endereco.getNeighbourhood() + endereco.getCity() + endereco.getZipcode()).await();

        double latitude = results[0].geometry.viewport.northeast.lat;
        double longitude = results[0].geometry.viewport.northeast.lng;

        endereco.setLatitude(String.valueOf(latitude));
        endereco.setLongitude(String.valueOf(longitude));
    }
}


