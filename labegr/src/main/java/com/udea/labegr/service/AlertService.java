package com.udea.labegr.service;

import com.udea.labegr.entity.Alert;
import com.udea.labegr.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {
    private final AlertRepository alertRepository;

    public Alert crearAlerta(Alert alerta) {
        return alertRepository.save(alerta);
    }

    public List<Alert> obtenerAlertas() {
        return alertRepository.findAll();
    }

    public Alert obtenerAlertaPorId(Long id) {
        return alertRepository.findById(id).orElse(null);
    }
}
