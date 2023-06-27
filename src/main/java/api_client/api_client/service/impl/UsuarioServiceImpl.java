package api_client.api_client.service.impl;

import api_client.api_client.model.DTO.TelefonoDTO;
import api_client.api_client.model.DTO.UsuarioDTO;
import api_client.api_client.model.Repository.TelefonoRepository;
import api_client.api_client.model.Repository.UsuarioRepository;
import api_client.api_client.model.entity.Telefono;
import api_client.api_client.model.entity.Usuario;
import api_client.api_client.service.IUsuarioService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    UsuarioRepository usuarioRepository;
    TelefonoRepository telefonoRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, TelefonoRepository telefonoRepository){
        this.usuarioRepository = usuarioRepository;
        this.telefonoRepository = telefonoRepository;
    }

    @Override
    public List<UsuarioDTO> getUsuarios(){
        List<Usuario> usuarios = usuarioRepository.getAllUsuarios();
        List<UsuarioDTO> usuarioDTO = new ArrayList<>();

        for(Usuario usuario : usuarios){
            usuarioDTO.add(UsuarioDTO.mapToDTO(usuario));
        }
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO postUsuario(UsuarioDTO usuario){
        Usuario usuarioEntity = UsuarioDTO.mapToEntity(usuario);
        List<TelefonoDTO> telefonoDTOs = new ArrayList<>();
        usuarioRepository.save(usuarioEntity);
        List<Telefono> telefonosEntity = TelefonoDTO.mapToListEntity(usuario.getTelefonos(), usuarioEntity.getUsuario_id());
        telefonoRepository.saveAll(telefonosEntity);
        for(Telefono telefono : telefonosEntity){
            TelefonoDTO telefonoDTO = new TelefonoDTO();
            telefonoDTO.setNumero(telefono.getNumero());
            telefonoDTO.setCodPais(telefono.getCodPais());
            telefonoDTO.setCodCiudad(telefono.getCodCiudad());
            telefonoDTOs.add(telefonoDTO);
        }
        usuario.setTelefonos(telefonoDTOs);
        return usuario;
    }

    @Override
    public UsuarioDTO getOneUsuario(String usuario_id){
        Usuario usuario = usuarioRepository.getOneUsuario(usuario_id);
        if(usuario !=null ){
            UsuarioDTO usuarioDTO = UsuarioDTO.mapToDTO(usuario);
            return usuarioDTO;
        }
        return null;
    }

    @Override
    public UsuarioDTO deleteUsuario(String usuario_id){
        Usuario usuario = usuarioRepository.getOneUsuario(usuario_id);
        if(usuario != null){
            usuario.setIsactive(false);
            int result = usuarioRepository.deleteUsuario(usuario_id);
            if(result == 1) {
                UsuarioDTO usuarioDTO = UsuarioDTO.mapToDTO(usuario);
                return usuarioDTO;
            }
        }
        return null;
    }

    @Override
    public UsuarioDTO putUsuario(UsuarioDTO usuario){
        List<TelefonoDTO> telefonos = new ArrayList<>();
        List<Telefono> telofonosEntity = TelefonoDTO.mapToListEntity(usuario.getTelefonos(), usuario.getId());
        Usuario usuarioEntity = UsuarioDTO.mapToEntity(usuario);
        usuarioEntity.setUsuario_id(usuario.getId());
        usuarioEntity.setTelefonos(telofonosEntity);

        Usuario user = usuarioRepository.updateUsuario(usuarioEntity);
        UsuarioDTO usuarioDTO = UsuarioDTO.mapToDTO(user);
        return usuarioDTO;
    }
}
