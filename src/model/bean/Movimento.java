package model.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import model.bean.enumeration.MovimentoEnum;

@Entity
public class Movimento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_veiculo")
	private Veiculo veiculo;
	private Timestamp data;
	@Enumerated(EnumType.STRING)
	private MovimentoEnum movimento;
	
	public Integer getId() {
		return id;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public Timestamp getData() {
		return data;
	}
	public MovimentoEnum getMovimento() {
		return movimento;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	public void setMovimento(MovimentoEnum movimento) {
		this.movimento = movimento;
	}
}
