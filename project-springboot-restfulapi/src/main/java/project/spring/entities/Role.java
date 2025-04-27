package project.spring.entities;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;
	
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private Set<Usuario> usuarios = new HashSet<>();

	public Role(){}
	
	
	
	@Override
	public String getAuthority() {
		return this.name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nomeRole) {
		this.name = nomeRole;
	}
	
	public enum Values{
		BASIC(2L),
		ADMIN(1L);
		
		long roleId;
		
		Values(long roleId) {
			this.roleId = roleId;
		}

		public long getRoleId() {
			return roleId;
		}
		
		
	}
	

}
