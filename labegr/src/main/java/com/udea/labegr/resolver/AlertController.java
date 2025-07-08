package com.udea.labegr.resolver;

import com.udea.labegr.entity.Alert;
import com.udea.labegr.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AlertController {
    @Autowired
    private AlertService alertService;

    @MutationMapping
    public Alert crearAlerta(
            @Argument String titulo,
            @Argument String mensaje,
            @Argument String tipo,
            @Argument String destinatario
    ) {
        Alert alerta = Alert.builder()
                .titulo(titulo)
                .mensaje(mensaje)
                .tipo(tipo)
                .destinatario(destinatario)
                .fechaCreacion(LocalDateTime.now())
                .build();
        return alertService.crearAlerta(alerta);
    }

    @QueryMapping
    public List<Alert> obtenerAlertas() {
        return alertService.obtenerAlertas();
    }

    @QueryMapping
    public Alert obtenerAlertaPorId(@Argument Long id) {
        return alertService.obtenerAlertaPorId(id);
    }
}
