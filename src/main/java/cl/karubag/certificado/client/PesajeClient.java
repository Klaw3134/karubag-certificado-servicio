package cl.karubag.certificado.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Map;

@Component
public class PesajeClient {

    private final WebClient webClient;

    public PesajeClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://karubag-pesaje-servicio.onrender.com")
                .build();
    }

    public Double obtenerTotalKilosPorRetiro(Long retiroId) {
        try {
            Double total = webClient.get()
                    .uri("/api/pesajes/retiro/" + retiroId + "/total-kilos")
                    .retrieve()
                    .bodyToMono(Double.class)
                    .block();
            return total != null ? total : 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }
}
