package hk.org.hkbh.cms.im.models.eos;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "USER")
public class UserEo
{
	protected Integer id;
	protected String username;
	protected String password;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "INT", nullable = false)
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	@Column(name = "USERNAME", length = 255,nullable = true)
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	@Column(name = "PASSWORD", length = 255,nullable = true)
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
//	
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
//    private Set<RoleEo> roleEoSet;
//	public Set<RoleEo> getRoleEoSet() {
//		return roleEoSet;
//	}
//	public void setRoleEoSet(Set<RoleEo> roleEoSet) {
//		this.roleEoSet = roleEoSet;
//	}


    
    
}
