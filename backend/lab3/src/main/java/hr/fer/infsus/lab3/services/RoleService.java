package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.Role;

import java.util.List;

public interface RoleService {
    public Role getRole(Long id);
    public List<Role> getAllRoles();
    public boolean createRole(Role role);
    public void deleteRole(Long id);
    public boolean editRole(Long id, Role role);
}
