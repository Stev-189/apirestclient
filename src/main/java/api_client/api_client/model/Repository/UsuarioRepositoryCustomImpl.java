package api_client.api_client.model.Repository;

import api_client.api_client.model.entity.Telefono;
import api_client.api_client.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> getAllUsuarios(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> usuarios = criteriaQuery.from(Usuario.class);
        Join<Usuario, Telefono> telefonos = usuarios.join("telefonos");
        criteriaQuery.select(usuarios).where(criteriaBuilder.and(
                criteriaBuilder.equal(usuarios.get("isactive"), true),
                criteriaBuilder.equal(telefonos.get("isactive"), true)
        ));
        try {
            List<Usuario> resultados = entityManager.createQuery(criteriaQuery).getResultList();
            return resultados;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Usuario getOneUsuario(String usuario_id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> usuario = criteriaQuery.from(Usuario.class);
        Join<Usuario, Telefono> telefonos = usuario.join("telefonos");
        Predicate con_usuario_id = criteriaBuilder.equal(usuario.get("usuario_id"), usuario_id);
        Predicate con_isactive_usuario = criteriaBuilder.equal(usuario.get("isactive"), true);
        Predicate con_isactive_telefono = criteriaBuilder.equal(telefonos.get("isactive"), true);
        criteriaQuery.select(usuario).where(criteriaBuilder.and(con_usuario_id, con_isactive_usuario, con_isactive_telefono));
        try {
            Usuario resultado = entityManager.createQuery(criteriaQuery).getSingleResult();
            return resultado;
        } catch (Exception e){
            return null;
        }

    }

    @Override
    @Transactional
    public int deleteUsuario(String usuario_id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Usuario> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Usuario.class);
        Root<Usuario> usuario = criteriaUpdate.from(Usuario.class);
        criteriaUpdate.set(usuario.get("isactive"), false);
        criteriaUpdate.where(criteriaBuilder.equal(usuario.get("usuario_id"), usuario_id));
        try {
            int resultado = entityManager.createQuery(criteriaUpdate).executeUpdate();
            return resultado;
        } catch (Exception e){
            return 0;
        }
    }

    @Override
    @Transactional
    public Usuario updateUsuario(Usuario usuario){
        //if (usuario == null || usuario.getUsuario_id() == null){
        //    throw new IllegalAccessException("Usuario y usuario_id son requeridos");
        //}
        Usuario thisUsuario = entityManager.find(Usuario.class, usuario.getUsuario_id());
        if(thisUsuario != null){
            entityManager.merge(usuario);
            for(Telefono telefono : usuario.getTelefonos()){
                Telefono thisTelefono = entityManager.find(Telefono.class, telefono.getTelefono_id());
                if(thisTelefono != null){
                    entityManager.merge(telefono);
                }
            }
        }
        return thisUsuario;
    }
}
