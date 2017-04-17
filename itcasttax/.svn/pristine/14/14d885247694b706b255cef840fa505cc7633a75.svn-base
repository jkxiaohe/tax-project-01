package test.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TPrivilege entity. @author MyEclipse Persistence Tools
 */

public class TPrivilege implements java.io.Serializable {

	// Fields

	private String priId;
	private String name;
	private Set rolePrivileges = new HashSet(0);

	// Constructors

	/** default constructor */
	public TPrivilege() {
	}

	/** full constructor */
	public TPrivilege(String name, Set rolePrivileges) {
		this.name = name;
		this.rolePrivileges = rolePrivileges;
	}

	// Property accessors

	public String getPriId() {
		return this.priId;
	}

	public void setPriId(String priId) {
		this.priId = priId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getRolePrivileges() {
		return this.rolePrivileges;
	}

	public void setRolePrivileges(Set rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

}