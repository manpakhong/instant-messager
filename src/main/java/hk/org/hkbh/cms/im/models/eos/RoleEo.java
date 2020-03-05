package hk.org.hkbh.cms.im.models.eos;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "ROLE")
public class RoleEo
{
	protected Integer id;
    private String roleName;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "INT", nullable = false)
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}

	@Column(name = "ROLE_NAME", length = 255,nullable = false)
	public String getRoleName(){
		return roleName;
	}
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

//    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
//    private Set<UserEo> userEoList;

}
