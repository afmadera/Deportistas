package com.example.Prueba.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.Prueba.dto.UserDTO;
import com.example.Prueba.model.Deporte;
import com.example.Prueba.model.Persona;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class UserController {
    
    List<Deporte> deportes = List.of(
        new Deporte("Futbol", 10),
        new Deporte("Natacion", 8),
        new Deporte("Atletismo", 6)
    );

    List<Persona> users = List.of(
        new Persona("Andres","Madera",22),
        new Persona("Juan","Perez",29),
        new Persona("Pedro Pablo", "Leon Jaramillo", 35)     
    );

    List<UserDTO> usuariosDeportes = List.of(
        new UserDTO(users.get(0).getName() + " " + users.get(0).getLastname(), deportes.get(0).getName(), deportes.get(0).getLevel()),
        new UserDTO(users.get(1).getName() + " " + users.get(1).getLastname(), deportes.get(1).getName(), deportes.get(1).getLevel()),
        new UserDTO(users.get(2).getName() + " " + users.get(2).getLastname(), deportes.get(2).getName(), deportes.get(2).getLevel())
    );

    @GetMapping("/findSport/{name}")
    public ResponseEntity<Deporte> getSport(@PathVariable String name) {
        Optional<Deporte> deporteFinded = this.deportes.stream()
                                                        .filter(deporte -> deporte.getName().toLowerCase().equals(name.toLowerCase()))
                                                        .findFirst();
        Deporte deporte = deporteFinded.isPresent() ? deporteFinded.get() : new Deporte();                                                
        return new ResponseEntity<>(deporte, HttpStatus.OK);
    }
    
    @GetMapping("/findSport")
    public ResponseEntity<List<Deporte>> getSports() {
        return new ResponseEntity<>(this.deportes, HttpStatus.OK);
    }

    @GetMapping("/findSportsPersons")
    public ResponseEntity<List<UserDTO>> findSportsPersons() {
        return new ResponseEntity<>(this.usuariosDeportes,HttpStatus.OK);
    }

    @GetMapping("/findSportsPersons/{namePerson}")
    public ResponseEntity<UserDTO> getAthlete(@PathVariable String namePerson) {
        Optional<UserDTO> athFinded = this.usuariosDeportes.stream()
                                                        .filter(ath -> ath.getNombre().toLowerCase().equals(namePerson.toLowerCase()))
                                                        .findFirst();
        UserDTO athlete = athFinded.isPresent() ? athFinded.get() : new UserDTO();                                                
        return new ResponseEntity<>(athlete, HttpStatus.OK);
    }
    
    
}
