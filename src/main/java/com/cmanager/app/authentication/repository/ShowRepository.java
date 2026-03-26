package com.cmanager.app.authentication.repository;

import com.cmanager.app.application.domain.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, String> {
    List<Show> findByIdIntegrationAndName(Integer idIntegration, String name);

    List<Show> findByName(String name);
}
