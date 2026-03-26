package com.cmanager.app.authentication.service;

import com.cmanager.app.application.data.ShowCreateRequest;
import com.cmanager.app.application.data.ShowDTO;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.authentication.repository.ShowRepository;
import com.cmanager.app.core.exception.AlreadyExistsException;
import com.cmanager.app.core.utils.Util;
import com.cmanager.app.integration.client.RequestService;

import com.cmanager.app.integration.dto.ShowsRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    private final ShowRepository repository;

    private final RequestService requestService;
    
    private final ObjectMapper objectMapper;


    public ShowService(ShowRepository repository
            , RequestService requestService
            , ObjectMapper objectMapper) {
        this.repository = repository;
        this.requestService = requestService;
        this.objectMapper = objectMapper;

    }

    public ResponseEntity<List<ShowDTO>> create(ShowCreateRequest showName) {

        List<Show> shows = repository.findByNameIgnoreCase(showName.name());

        if (!shows.isEmpty()) {
            throw new AlreadyExistsException("Show with the same idIntegration and name already exists");
        }

        ResponseEntity<List<ShowsRequestDTO>> showsIntegrations = requestService.getShowByName(showName.name());

        List<ShowDTO> response = new ArrayList<>();

        if (showsIntegrations.getBody() == null || showsIntegrations.getBody().isEmpty()) {
            throw new AlreadyExistsException("Show not found in the external API");
        } else {

            showsIntegrations.getBody().forEach(item -> {
                ShowsRequestDTO showRequest = objectMapper.convertValue(item, ShowsRequestDTO.class);
                Show showEntity = Util.showRequestDTOToShow(showRequest);
                repository.save(showEntity);
                response.add(ShowDTO.convertEntity(showEntity));
            });
        }

         return ResponseEntity.ok(response);

    }

    public ShowDTO findById(String id) {
        final var show = repository.findById(id).orElse(null);

        return ShowDTO.convertEntity(show);

    }

    public Page<Show> findByName(String name, Pageable pageable) {

        if (name == null || name.trim().isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.findByNameContainingIgnoreCase(name, pageable);
    }


    
    

}
