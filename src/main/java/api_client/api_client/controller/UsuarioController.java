package api_client.api_client.controller;

import api_client.api_client.model.DTO.UsuarioDTO;
import api_client.api_client.service.impl.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    UsuarioServiceImpl usuarioService;
    public UsuarioController(UsuarioServiceImpl usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios(){
        List<UsuarioDTO> usuarios = usuarioService.getUsuarios();
        if(usuarios.size()>0){
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioDTO> postUsuario(@Valid @RequestBody UsuarioDTO usuario){
        UsuarioDTO usuarioDTO = usuarioService.postUsuario(usuario);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario (@PathVariable("id") String id){
        UsuarioDTO usuarioDTO = usuarioService.getOneUsuario(id);
        if(usuarioDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDTO>  deleteUsuario(@PathVariable("id") String id){
        UsuarioDTO usuarioDTO = usuarioService.deleteUsuario(id);
        if(usuarioDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<UsuarioDTO> putUsuario (@Valid @RequestBody UsuarioDTO usuario){
        UsuarioDTO usuarioDTO = usuarioService.putUsuario(usuario);
        if(usuarioDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
    }

}
