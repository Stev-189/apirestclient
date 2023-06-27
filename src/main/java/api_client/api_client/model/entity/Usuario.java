package api_client.api_client.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario extends RepresentationModel<Usuario>{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(50)")
    private String usuario_id;
    @Column
    private LocalDateTime created_at;
    @Column
    private LocalDateTime edited_at;
    @Column
    private boolean isactive;
    @Column
    private String nombre;
    @Column
    private String correo;
    @Column
    private String password;

    @PrePersist
    protected void create(){
        created_at = LocalDateTime.now();
        edited_at = LocalDateTime.now();
        isactive = true;
    }

    @PreUpdate
    protected void update(){
        edited_at = LocalDateTime.now();
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private List<Telefono> telefonos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(usuario_id, usuario.usuario_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), usuario_id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("usuario_id='").append(usuario_id).append('\'');
        sb.append(", created_at=").append(created_at);
        sb.append(", edit_at=").append(edited_at);
        sb.append(", isactive=").append(isactive);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", correo='").append(correo).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", telefonos=").append(telefonos);
        sb.append('}');
        return sb.toString();
    }
}
