package com.cmanager.app.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "ShowsDTO", description = "Objeto da representação de Shows")
@JsonIgnoreProperties(ignoreUnknown = true)
public record ShowsRequestDTO(
        @JsonProperty("id")
        @Schema(name = "id", description = "Id")
        Integer id,
        @JsonProperty("url")
        @Schema(name = "url", description = "Url")
        String url,
        @JsonProperty("name")
        @Schema(name = "name", description = "Nome")
        String name,
        @JsonProperty("type")
        @Schema(name = "type", description = "Tipo")
        String type,
        @JsonProperty("language")
        @Schema(name = "language", description = "Lingua")
        String language,
        @JsonProperty("genres")
        @Schema(name = "genres", description = "Gêneros")
        List<String> genres,
        @JsonProperty("status")
        @Schema(name = "status", description = "Status")
        String status,
        @JsonProperty("runtime")
        @Schema(name = "runtime", description = "Duração")
        Integer runtime,
        @JsonProperty("averageRuntime")
        @Schema(name = "averageRuntime", description = "Tempo médio")
        Integer averageRuntime,
        @JsonProperty("premiered")
        @Schema(name = "premiered", description = "Data de estreia")
        String premiered,
        @JsonProperty("ended")
        @Schema(name = "ended", description = "Data de término")
        String ended,
        @JsonProperty("officialSite")
        @Schema(name = "officialSite", description = "Site oficial")
        String officialSite,
        @JsonProperty("schedule")
        @Schema(name = "schedule", description = "Horário de exibição")
        ScheduleDTO schedule,
        @JsonProperty("rating")
        @Schema(name = "rating", description = "Nota")
        RatingDTO rating,
        @JsonProperty("weight")
        @Schema(name = "weight", description = "Peso")
        Integer weight,
        @JsonProperty("network")
        @Schema(name = "network", description = "Emissora")
        NetworkDTO network,
        @JsonProperty("webChannel")
        @Schema(name = "webChannel", description = "Canal de streaming")
        Object webChannel,
        @JsonProperty("dvdCountry")
        @Schema(name = "dvdCountry", description = "País de lançamento em DVD")
        Object dvdCountry,
        @JsonProperty("externals")
        @Schema(name = "externals", description = "IDs externos relacionados ao show")
        ExternalsDTO externals,
        @JsonProperty("image")
        @Schema(name = "image", description = "Imagens do show")
        ImageDTO image,
        @JsonProperty("summary")
        @Schema(name = "summary", description = "Resumo")
        String summary,
        @JsonProperty("updated")
        @Schema(name = "updated", description = "Data da última atualização")
        Integer updated,
        @JsonProperty("_links")
        @Schema(name = "_links", description = "Links relacionados ao show")
        LinksDTO _links


) {
}
