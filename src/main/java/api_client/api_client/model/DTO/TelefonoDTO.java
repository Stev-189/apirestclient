package api_client.api_client.model.DTO;

import api_client.api_client.model.entity.Telefono;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TelefonoDTO {
    private String numero;
    private String codCiudad;
    private String codPais;
    private LocalDateTime created_at;
    private LocalDateTime edited_at;
    private boolean isactive;

    public static TelefonoDTO mapToDTO(Telefono entity){
        TelefonoDTO dto = new TelefonoDTO();
        dto.setNumero(entity.getNumero());
        dto.setCodCiudad(entity.getCodCiudad());
        dto.setCodPais(entity.getCodPais());
        dto.setCreated_at(entity.getCreated_at());
        dto.setEdited_at(entity.getEdited_at());
        dto.setIsactive(entity.isIsactive());
        return dto;
    }

    public static Telefono mapToEntity(TelefonoDTO dto, String usuario_id){
        Telefono telefonoEntity = new Telefono();
        telefonoEntity.setNumero(dto.getNumero());
        telefonoEntity.setCodCiudad(dto.getCodCiudad());
        telefonoEntity.setCodPais(dto.getCodPais());
        telefonoEntity.setCreated_at(dto.getCreated_at());
        telefonoEntity.setEdited_at(dto.getEdited_at());
        telefonoEntity.setIsactive(dto.isIsactive());
        telefonoEntity.setUsuario_id(usuario_id);
        return telefonoEntity;

    }

    public static List<Telefono> mapToListEntity(List<TelefonoDTO> dto , String usuario_id){
        List<Telefono> telefonos = new ArrayList<>();

        for(TelefonoDTO telefonoDTO: dto){
            Telefono telefonoEntity = mapToEntity(telefonoDTO, usuario_id);
            telefonos.add(telefonoEntity);
        }
        return telefonos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TelefonoDTO that)) return false;
        return Objects.equals(numero, that.numero) && Objects.equals(codCiudad, that.codCiudad) && Objects.equals(codPais, that.codPais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, codCiudad, codPais);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TelefonoDTO{");
        sb.append("numero='").append(numero).append('\'');
        sb.append(", codCiudad='").append(codCiudad).append('\'');
        sb.append(", codPais='").append(codPais).append('\'');
        sb.append(", created_at=").append(created_at);
        sb.append(", edited_at=").append(edited_at);
        sb.append(", isactive=").append(isactive);
        sb.append('}');
        return sb.toString();
    }
}
