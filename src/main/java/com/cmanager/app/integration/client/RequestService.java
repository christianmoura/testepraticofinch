package com.cmanager.app.integration.client;

import com.cmanager.app.core.utils.Util;
import com.cmanager.app.integration.dto.EpisodeRequestDTO;
import com.cmanager.app.integration.dto.ShowsRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RequestService {

    @Value("${tvmaze.api-url-show-search-id}")
    private String urlSearchShowById;

    @Value("${tvmaze.api-url-show-search-name}")
    private String urlSearchShowByName;

    @Value("${tvmaze.api-url-show-search-episodes}")
    private String urlSearchEpisodesById;

    private final AbstractRequest<ShowsRequestDTO> abstractConnect;

    private final ObjectMapper objectMapper;

    public RequestService(AbstractRequest<ShowsRequestDTO> abstractConnect
            , ObjectMapper objectMapper) {
        this.abstractConnect = abstractConnect;
        this.objectMapper = objectMapper;

    }

    public ResponseEntity<ShowsRequestDTO> getShowById(String id) {
        return abstractConnect.getById(urlSearchShowById, id, ShowsRequestDTO.class);
    }

    public ResponseEntity<List<ShowsRequestDTO>> getShowByName(String name) {

        return convertToShowsRequestDTO(abstractConnect.getByText(urlSearchShowByName, name));
    }

    private ResponseEntity<List<ShowsRequestDTO>> convertToShowsRequestDTO(ResponseEntity<List<Object>> response) {

        if (response.getBody() == null) {
            return ResponseEntity.ok(List.of());
        }

        List<ShowsRequestDTO> shows = response.getBody().stream()
                .map(item -> Util.objToShowRequestDTO(item, objectMapper))
                .toList();


        return ResponseEntity.ok(shows);
    }

    public ResponseEntity<List<EpisodeRequestDTO>> getEpisodeBySeasonsId(Integer id) {
        urlSearchEpisodesById = urlSearchEpisodesById + "/" + id.toString() + "/episodes";
        return convertToEpisodeRequestDTO(abstractConnect.getById(urlSearchEpisodesById));
    }

    private ResponseEntity<List<EpisodeRequestDTO>> convertToEpisodeRequestDTO(ResponseEntity<List<Object>> response) {
        if (response.getBody() == null) {
            return ResponseEntity.ok(List.of());
        }

        List<EpisodeRequestDTO> episodes = response.getBody().stream()
                .map(item -> Util.objToEpisodeRequestDTO(item, objectMapper))
                .toList();

        return ResponseEntity.ok(episodes);
    }

}
