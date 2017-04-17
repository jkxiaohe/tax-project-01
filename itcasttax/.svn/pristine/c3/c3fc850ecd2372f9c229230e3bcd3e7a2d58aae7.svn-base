package test.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TRole entity. @author MyEclipse Persistence Tools
 */

public class TRole implements java.io.Serializable {

	// Fields

	private String roleId;
	private String name;
	private Set empRoles = new HashSet(0);
	private Set rolePrivileges = new HashSet(0);

	// Constructors

	/** default constructor */
	public TRole() {
	}

	/** full constructor */
	public TRole(String name, Set empRoles, Set rolePrivileges) {
		this.name = name;
		this.empRoles = empRoles;
		this.rolePrivileges = rolePrivileges;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getEmpRoles() {
		return this.empRoles;
	}

	public void setEmpRoles(Set empRoles) {
		this.empRoles = empRoles;
	}

	public Set getRolePrivileges() {
		return this.rolePrivileges;
	}

	public void setRolePrivileges(Set rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

}