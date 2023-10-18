package br.com.microsservice.pagamentoms.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@Embeddable
@Data
public class DataEmbeddable {

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataInclusao = LocalDateTime.now();

    @Column
    private LocalDateTime dataAtualizacao;

    @PreUpdate
    public void onPreUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

}
