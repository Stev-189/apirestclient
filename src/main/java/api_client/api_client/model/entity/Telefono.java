package api_client.api_client.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;
@Entity
@Table(name = "telefonos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Telefono extends RepresentationModel<Telefono> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(50)")
    private String telefono_id;
    @Column
    private String numero;
    @Column
    private String codCiudad;
    @Column
    private String codPais;
    @Column
    private LocalDateTime created_at;
    @Column
    private LocalDateTime edited_at;
    @Column
    private boolean isactive;
    @Column
    private String usuario_id;

    @PrePersist
    protected void create(){
        created_at = LocalDateTime.now();
        edited_at = LocalDateTime.now();
        isactive = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Telefono telefono)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(telefono_id, telefono.telefono_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), telefono_id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Telefono{");
        sb.append("telefono_id='").append(telefono_id).append('\'');
        sb.append(", numero='").append(numero).append('\'');
        sb.append(", codCiudad='").append(codCiudad).append('\'');
        sb.append(", codPais='").append(codPais).append('\'');
        sb.append(", created_at=").append(created_at);
        sb.append(", edited_at=").append(edited_at);
        sb.append(", isactive=").append(isactive);
        sb.append(", usuario_id='").append(usuario_id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
