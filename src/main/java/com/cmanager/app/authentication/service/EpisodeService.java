package com.cmanager.app.authentication.service;

import com.cmanager.app.integration.client.RequestService;
import com.cmanager.app.integration.dto.EpisodeRequestDTO;
import com.cmanager.app.integration.dto.RatingDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EpisodeService {

    private final RequestService requestService;

    private final ObjectMapper objectMapper;

    public EpisodeService(RequestService requestService
            , ObjectMapper objectMapper) {
        this.requestService = requestService;
        this.objectMapper = objectMapper;

    }

    public ResponseEntity<RatingDTO> findEpisodesBySeasonId(Integer seasonId) {
        ResponseEntity<List<EpisodeRequestDTO>> episodesIntegrations = requestService.getEpisodeBySeasonsId(seasonId);

        if (episodesIntegrations.getBody() == null || episodesIntegrations.getBody().isEmpty()) {
            return ResponseEntity.ok(new RatingDTO(BigDecimal.ZERO));
        }


        RatingDTO averageRating = calculateAverageRating(episodesIntegrations.getBody());

        return ResponseEntity.ok(averageRating);
    }


    private RatingDTO calculateAverageRating(List<EpisodeRequestDTO> episodes) {
        if (episodes == null || episodes.isEmpty()) {
            return new RatingDTO(BigDecimal.ZERO);
        }


        List<BigDecimal> validRatings = episodes.stream()
                .filter(episode -> episode.rating() != null)
                .filter(episode -> episode.rating().average() != null)
                .filter(episode -> episode.rating().average().compareTo(BigDecimal.ZERO) != 0)
                .map(episode -> episode.rating().average())
                .toList();


        if (validRatings.isEmpty()) {
            return new RatingDTO(BigDecimal.ZERO);
        }


        BigDecimal sum = validRatings.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal average = sum.divide(BigDecimal.valueOf(validRatings.size()), 2, java.math.RoundingMode.HALF_UP);

        return new RatingDTO(average);
    }

}
