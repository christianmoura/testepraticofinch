package com.cmanager.app.core.utils;

import com.cmanager.app.application.domain.Show;
import com.cmanager.app.integration.dto.EpisodeRequestDTO;
import com.cmanager.app.integration.dto.ShowsRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.LinkedHashMap;

public class Util {

    private Util() {
        throw new IllegalStateException("Utility class");
    }

    public static Pageable getPageable(int page, int size, String sortField, String sortOrder) {
        final var sort = sortOrder.equalsIgnoreCase("ASC")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        return PageRequest.of(page, size, sort);
    }

    public static Show showRequestDTOToShow(ShowsRequestDTO showRequest) {

        return Show.builder()
                .idIntegration((showRequest.id() == null) ? null : showRequest.id())
                .name(showRequest.name())
                .type(showRequest.type())
                .language(showRequest.language())
                .status(showRequest.status())
                .runtime(showRequest.runtime())
                .averageRuntime(showRequest.averageRuntime())
                .officialSite(showRequest.officialSite())
                .rating((showRequest.rating() == null) ? null : showRequest.rating().average())
                .summary(showRequest.summary())
                .build();

    }

    public static ShowsRequestDTO objToShowRequestDTO(Object showRequest, ObjectMapper objectMapper) {

        if (!(showRequest instanceof LinkedHashMap)) {
            throw new IllegalArgumentException("showRequest deve ser um LinkedHashMap");
        }

        LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) showRequest;
        Object showObj = map.get("show");

        if (showObj == null) {
            return null;
        }


        return objectMapper.convertValue(showObj, ShowsRequestDTO.class);
    }

    public static EpisodeRequestDTO objToEpisodeRequestDTO(Object item, ObjectMapper objectMapper) {

        if (!(item instanceof LinkedHashMap)) {
            throw new IllegalArgumentException("item deve ser um LinkedHashMap");
        }

        LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) item;

        return objectMapper.convertValue(map, EpisodeRequestDTO.class);

    }
}
