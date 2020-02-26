package hk.org.hkbh.cms.im.models.eos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "CMSAPPS.OP_USER")
public class OpUserEo
{
	protected Integer id;
	protected String name;
	protected String address;
	protected Long age;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "NUMBER", nullable = false)
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	@Column(name = "NAME", length = 100,nullable = true)
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	@Column(name = "ADDRESS", length = 200,nullable = true)
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	@Column(name = "AGE", nullable = true)
	public Long getAge(){
		return age;
	}
	public void setAge(Long age){
		this.age = age;
	}
}
