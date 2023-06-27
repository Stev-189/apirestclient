package api_client.api_client.model.DTO;

import api_client.api_client.model.entity.Telefono;
import api_client.api_client.model.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    private String id;

    @NotEmpty(message = "el nombre es obligatorio")
    private String nombre;

    @Email(message = "debe ser una email valido")
    private String correo;

    private LocalDateTime created_at;
    private LocalDateTime edited_at;
    private boolean isactive;

    @NotEmpty(message = "Password es obligatorio")
    @Size(min = 4, max = 10, message = "debe ser entre 4 y 10 caracteres")
    private String password;

    private List<TelefonoDTO> telefonos;

    public static UsuarioDTO mapToDTO(Usuario entity){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getUsuario_id());
        dto.setIsactive(entity.isIsactive());
        dto.setCorreo(entity.getCorreo());
        dto.setNombre(entity.getNombre());
        dto.setCreated_at(entity.getCreated_at());
        dto.setEdited_at(entity.getEdited_at());
        dto.setPassword(entity.getPassword());
        List<TelefonoDTO> telefonos = new ArrayList<>();
        for(Telefono telefono : entity.getTelefonos()){
            TelefonoDTO telefonoDTO = TelefonoDTO.mapToDTO(telefono);
            telefonos.add(telefonoDTO);
        }
        dto.setTelefonos(telefonos);
        return dto;
    }

    public static Usuario mapToEntity(UsuarioDTO dto){
        List<Telefono> telefonos = new ArrayList<>();
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setNombre(dto.getNombre());
        usuarioEntity.setCorreo(dto.getCorreo());
        usuarioEntity.setPassword(dto.getPassword());
        usuarioEntity.setIsactive(dto.isIsactive());
        usuarioEntity.setTelefonos(telefonos);
        return usuarioEntity;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioDTO that)) return false;
        return isactive == that.isactive && Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(correo, that.correo) && Objects.equals(created_at, that.created_at) && Objects.equals(edited_at, that.edited_at) && Objects.equals(password, that.password) && Objects.equals(telefonos, that.telefonos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, correo, created_at, edited_at, isactive, password, telefonos);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UsuarioDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", correo='").append(correo).append('\'');
        sb.append(", created_at=").append(created_at);
        sb.append(", edited_at=").append(edited_at);
        sb.append(", isactive=").append(isactive);
        sb.append(", password='").append(password).append('\'');
        sb.append(", telefonos=").append(telefonos);
        sb.append('}');
        return sb.toString();
    }
}
