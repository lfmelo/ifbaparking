package model.bean;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String cpf;
	private String nome;
	private Date nascimento;
	private String matricula;
	private String senha;
	
	public Usuario() {
	}
	
	public Usuario(Integer id) {
		this.id = id;
	}
	
	public Usuario(Integer id, String cpf, String nome, Date nascimento, String matricula, String senha) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.nascimento = nascimento;
		this.matricula = matricula;
		this.senha = senha;
	}
	
	public Integer getId() {
		return id;
	}
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public String getMatricula() {
		return matricula;
	}
	public String getSenha() {
		return senha;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
