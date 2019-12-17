package model.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Veiculo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_motorista")
	private Motorista motorista;
	private String tipo;
	private String fabricante;
	private String modelo;
	private String cor;
	private String ano;
	private String placa;
	private String proprietario;
	private Boolean permissao = Boolean.TRUE;
	private Timestamp data_cad;
	private String etiqueta_id;
	private Boolean local = Boolean.TRUE;
	private Boolean cadastrar_tag = Boolean.TRUE;
	
	public Veiculo() {
	}
	
	public Veiculo(Integer id) {
		this.id = id;
	}
	
	public Veiculo(Integer id, Motorista motorista, String tipo, String fabricante, String modelo, String cor, String ano,
			String placa, String proprietario, Boolean permissao, Timestamp data_cad, String etiqueta_id, Boolean local, Boolean cadatrar_tag) {
		this.id = id;
		this.motorista = motorista;
		this.tipo = tipo;
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.cor = cor;
		this.ano = ano;
		this.placa = placa;
		this.proprietario = proprietario;
		this.permissao = permissao;
		this.data_cad = data_cad;
		this.etiqueta_id = etiqueta_id;
		this.local = local;
		this.cadastrar_tag = cadatrar_tag;
	}

	public Integer getId() {
		return id;
	}
	public Motorista getMotorista() {
		return motorista;
	}
	public String getTipo() {
		return tipo;
	}
	public String getFabricante() {
		return fabricante;
	}
	public String getModelo() {
		return modelo;
	}
	public String getCor() {
		return cor;
	}
	public String getAno() {
		return ano;
	}
	public String getPlaca() {
		return placa;
	}
	public String getProprietario() {
		return proprietario;
	}
	public Boolean getPermissao() {
		return permissao;
	}
	public Timestamp getData_cad() {
		return data_cad;
	}
	public String getEtiqueta_id() {
		return etiqueta_id;
	}
	public Boolean getLocal() {
		return local;
	}
	public Boolean getCadastrar_tag() {
		return cadastrar_tag;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
	public void setPermissao(Boolean permissao) {
		this.permissao = permissao;
	}
	public void setData_cad(Timestamp data_cad) {
		this.data_cad = data_cad;
	}
	public void setEtiqueta_id(String etiqueta_id) {
		this.etiqueta_id = etiqueta_id;
	}
	public void setLocal(Boolean local) {
		this.local = local;
	}
	public void setCadastrar_tag(Boolean cadastrar_tag) {
		this.cadastrar_tag = cadastrar_tag;
	}
	
}
