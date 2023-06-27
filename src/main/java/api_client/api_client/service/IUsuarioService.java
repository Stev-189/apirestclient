package api_client.api_client.service;

import api_client.api_client.model.DTO.UsuarioDTO;

import java.util.List;
public interface IUsuarioService {
    List<UsuarioDTO> getUsuarios();

    UsuarioDTO postUsuario(UsuarioDTO usuario);

    UsuarioDTO getOneUsuario(String usuario_id);

    UsuarioDTO deleteUsuario(String usuario_id);

    UsuarioDTO putUsuario(UsuarioDTO usuario);
}
