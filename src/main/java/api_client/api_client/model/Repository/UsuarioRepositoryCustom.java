package api_client.api_client.model.Repository;

import api_client.api_client.model.entity.Usuario;

import java.util.List;
public interface UsuarioRepositoryCustom {
    List<Usuario> getAllUsuarios();

    Usuario getOneUsuario(String usuario_id);

    int deleteUsuario(String usuario_id);

    Usuario updateUsuario(Usuario usuario);
}
