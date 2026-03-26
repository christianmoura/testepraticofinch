package com.cmanager.app.authentication.controller;

import com.cmanager.app.application.data.ShowCreateRequest;
import com.cmanager.app.application.data.ShowDTO;
import com.cmanager.app.application.domain.Show;

import com.cmanager.app.authentication.service.ShowService;
import com.cmanager.app.core.data.PageResultResponse;
import com.cmanager.app.core.utils.Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/shows")
@Tag(
        name = "ShowController",
        description = "API de gerenciamento de shows"
)
public class ShowController {

    private final ShowService service;

    public ShowController(ShowService service) {
        this.service = service;
    }

    @Operation(
            summary = "get",
            description = "Consulta um show pelo id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ShowDTO> get(@PathVariable String id) {
        final ShowDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<ShowDTO>> create(@RequestBody ShowCreateRequest name) {
        final List<ShowDTO> dtos = service.create(name).getBody();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageResultResponse<Show>> list(
            @Parameter(description = "Nome do show para filtro", example = "Sertanejo")
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @Parameter(description = "Número da página (inicia em 0)", example = "0")
            @RequestParam(value = "page", defaultValue = "0") int page,
            @Parameter(description = "Quantidade de registros por página", example = "10")
            @RequestParam(value = "size", defaultValue = "10") int size,
            @Parameter(description = "Campo para ordenação", example = "name")
            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
            @Parameter(description = "Direção da ordenação (ASC ou DESC)", example = "ASC")
            @RequestParam(value = "sortOrder", defaultValue = "ASC") String sortOrder
    ) {
        final var pageable = Util.getPageable(page, size, sortField, sortOrder);
        final var listShows = service.findByName(name, pageable);
        final var pageProducts = new PageImpl<>(
                listShows.getContent(),
                pageable,
                listShows.getTotalElements()
        );
        return ResponseEntity.ok(PageResultResponse.from(pageProducts));
    }



}
