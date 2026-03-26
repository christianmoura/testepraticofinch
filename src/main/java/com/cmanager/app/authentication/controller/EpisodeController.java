package com.cmanager.app.authentication.controller;


import com.cmanager.app.authentication.service.EpisodeService;
import com.cmanager.app.integration.dto.RatingDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/episodes")
@Tag(
        name = "EpisodeController",
        description = "API de gerenciamento de episodios"
)
public class EpisodeController {

    private final EpisodeService service;

    public EpisodeController(EpisodeService service) {
        this.service = service;
    }

    @GetMapping("/api/episodes/average/{seasonId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RatingDTO> get(@PathVariable Integer seasonId) {
        return service.findEpisodesBySeasonId(seasonId);
    }
}
