package br.com.escola.escola_api.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name="tb_salas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A identificação da sala é obrigatória")
    @Column(nullable = false)
    private String roomCode;

    @NotBlank(message = "O bloco é obrigatório")
    @Column(nullable = false)
    private String block;

    @NotBlank(message = "O tipo de sala é obrigatório")
    @Column(nullable = false)
    private String roomType;

    @NotNull(message = "A capacidade é obrigatória")
    @Positive(message = "A capacidade deve ser um número maior que zero")
    @Column(nullable = false)
    private Integer capacity;
}
