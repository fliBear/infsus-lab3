package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.Role;
import hr.fer.infsus.lab3.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RoleServiceImpl implements RoleService{
    public RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRole(Long id) {
        Optional<Role> optional = roleRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public boolean createRole(Role role) {
        roleRepository.save(role);
        return true;
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public boolean editRole(Long id, Role role) {
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()){
            Role existingRole = optional.get();
            existingRole.setDescription(role.getDescription());

            roleRepository.save(existingRole);
            return true;
        }
        return false;
    }
}
