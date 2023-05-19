package hr.fer.infsus.lab3.controllers;

import hr.fer.infsus.lab3.models.Publisher;
import hr.fer.infsus.lab3.models.Role;
import hr.fer.infsus.lab3.services.PublisherService;
import hr.fer.infsus.lab3.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id){
        Role role = roleService.getRole(id);
        if(role == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @PostMapping("/createRole")
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        boolean isCreated = roleService.createRole(role);
        if(!isCreated){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id){
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editRole/{id}")
    public ResponseEntity<Role> editRole(@PathVariable Long id, @RequestBody Role role){
        boolean isEdited = roleService.editRole(id, role);
        if(!isEdited){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }
}
