package com.cmanager.app.integration.dto;

import java.util.List;

public record ScheduleDTO(String time, List<String> days) {
}
