/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.service.user.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.user.security.Role;
import es.damtfg.IndustrialProcessManagement.repository.user.security.RoleRepository;

/**
 * @author Alberto González
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Búsqueda de todos los roles.
	 * 
	 * @return Iterable<Role>
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<Role> findAll() {
		
		return roleRepository.findAll();
	}
	
	/**
	 * Búsqueda de todos los roles.
	 * 
	 * @return List<Role>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Role> listAll() {
		
		return roleRepository.findAll();
	}
	
	/**
	 * Búsqueda de todos los roles con paginación.
	 * 
	 * @param pageable
	 * 
	 * @return Page<Role>
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Role> findAll(Pageable pageable) {
		
		return roleRepository.findAll(pageable);
	}

	/**
	 * Búsqueda de rol por ID.
	 * 
	 * @param id
	 * 
	 * @return Objeto Optional<Role>
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Role> findById(Long id) {
	
		return roleRepository.findById(id);
	}
	
	/**
	 * Búsqueda de rol por nombre.
	 * 
	 * @param name
	 * 
	 * @return Objeto Optional<Role>
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Role> findByName(String name) {
	
		return roleRepository.findByName(name);
	}

	/**
	 * Crea la persistencia del objeto.
	 * 
	 * @param role
	 * 
	 * @return Objeto role guardado
	 */
	@Override
	@Transactional
	public Role save(Role role) {
	
		return roleRepository.save(role);
	}
	
	/**
	 * Crea la persistencia de los objetos.
	 * 
	 * @param ArrayList<Role>
	 * 
	 * @return Lista de objetos guardados
	 */
	@Override
	@Transactional
	public List<Role> saveAll(ArrayList<Role> role) {
				
		int index = 0;
		
		while(index < role.size()) {
			
			if(findByName(role.get(index).getName()).isPresent()) {
				
				role.remove(index);
				
			} else {
				
				index++;
			}
		}
				
		return roleRepository.saveAll(role);
	}

	/**
	 * Elimina un rol por ID.
	 * 
	 * @param id
	 */
	@Override
	@Transactional
	public void deleteById(Long id) {
		
		roleRepository.deleteById(id);
	}
	
}
