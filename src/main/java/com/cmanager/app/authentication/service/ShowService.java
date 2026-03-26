package com.cmanager.app.authentication.service;

import com.cmanager.app.application.data.ShowDTO;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.authentication.repository.ShowRepository;
import com.cmanager.app.core.exception.AlreadyExistsException;
import com.cmanager.app.integration.client.RequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {
    private final ShowRepository repository;

    private final RequestService requestService;

    private static final String URL = "https://api.tvmaze.com/singlesearch/shows?q=%s&embed=episodes";

    @Value("${tvmaze.api-url-show-search}")
    private String urlRequest;

    public ShowService(ShowRepository repository, RequestService requestService) {
        this.repository = repository;
        this.requestService = requestService;
    }

    public ShowDTO create(String showName) {

        List<Show> shows = repository.findByName(showName);

        if (!shows.isEmpty()) {
            throw new AlreadyExistsException("Show with the same idIntegration and name already exists");
        }

       // final var show = requestService.getShow(urlRequest, showName);


        //final var showEntity = repository.save(show.toEntity());
        //return ShowDTO.fromEntity(showEntity);
            return null;
    }
}
