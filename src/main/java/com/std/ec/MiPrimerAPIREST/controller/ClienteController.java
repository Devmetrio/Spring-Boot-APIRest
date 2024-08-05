package com.std.ec.MiPrimerAPIREST.controller;

import com.std.ec.MiPrimerAPIREST.model.dto.ClienteDTO;
import com.std.ec.MiPrimerAPIREST.model.entity.Cliente;
import com.std.ec.MiPrimerAPIREST.model.payload.MensajeResponse;
import com.std.ec.MiPrimerAPIREST.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;

    @GetMapping("clientes")
    public ResponseEntity<?> showAll(){
        List<Cliente> getList = clienteService.listAll();
        if(getList == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build()
                    , HttpStatus.OK);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(getList)
                        .build()
                , HttpStatus.OK);
    }


    @PostMapping("cliente")
    public ResponseEntity<?> create(@RequestBody ClienteDTO clienteDTO){
        Cliente clienteSave = null;
        try {
            clienteSave = clienteService.save(clienteDTO);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Guardado con exito")
                            .object(ClienteDTO.builder()
                                    .IdCliente(clienteSave.getIdCliente())
                                    .nombre(clienteSave.getNombre())
                                    .apellido(clienteSave.getApellido())
                                    .fechaRegistro(clienteSave.getFechaRegistro())
                                    .correo(clienteSave.getCorreo())
                                    .build())
                            .build()
                    , HttpStatus.CREATED);
        }catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
        Cliente clienteUpdate = null;
        try {
            if(clienteService.existsById(id)){
                clienteDTO.setIdCliente(id);
                clienteUpdate = clienteService.save(clienteDTO);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Guardado con exito")
                                .object(ClienteDTO.builder()
                                        .IdCliente(clienteUpdate.getIdCliente())
                                        .nombre(clienteUpdate.getNombre())
                                        .apellido(clienteUpdate.getApellido())
                                        .fechaRegistro(clienteUpdate.getFechaRegistro())
                                        .correo(clienteUpdate.getCorreo())
                                        .build())
                                .build()
                        , HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro que se trata actualizar no se encuentra la base de datos")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }


        }catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);

        if(cliente == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que se intenta buscar no existe")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(ClienteDTO.builder()
                                .IdCliente(cliente.getIdCliente())
                                .nombre(cliente.getNombre())
                                .apellido(cliente.getApellido())
                                .fechaRegistro(cliente.getFechaRegistro())
                                .correo(cliente.getCorreo())
                                .build())
                        .build()
                , HttpStatus.OK);
    }
    
}
