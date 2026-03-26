package com.cmanager.app.integration.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "ShowsDTO", description = "Objeto do Episodio de Shows")
@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeRequestDTO(
        @JsonProperty("id")
        @Schema(name = "id", description = "Id")
        Integer id,
        @JsonProperty("url")
        @Schema(name = "url", description = "Url")
        String url,
        @JsonProperty("name")
        @Schema(name = "name", description = "Nome")
        String name,
        @JsonProperty("season")
        @Schema(name = "season", description = "Temporada")
        Integer season,
        @JsonProperty("number")
        @Schema(name = "number", description = "Episódio")
        Integer number,
        @JsonProperty("type")
        @Schema(name = "type", description = "Tipo")
        String type,
        @JsonProperty("airdate")
        @Schema(name = "airdate", description = "Lançamento")
        String airdate,
        @JsonProperty("airtime")
        @Schema(name = "airtime", description = "Hora de lançamento")
        String airtime,
        @JsonProperty("airstamp")
        @Schema(name = "airstamp", description = "Airstamp")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
        LocalDateTime airstamp,
        @JsonProperty("runtime")
        @Schema(name = "runtime", description = "Duração")
        Integer runtime,
        @JsonProperty("rating")
        @Schema(name = "rating", description = "Nota")
        RatingDTO rating,
        @JsonProperty("image")
        @Schema(name = "image", description = "Imagem")
        ImageDTO image,
        @JsonProperty("summary")
        @Schema(name = "summary", description = "Resumo")
        String summary,
        @JsonProperty("_links")
        @Schema(name = "_links", description = "Links")
        LinksDTO _links
) {

}
