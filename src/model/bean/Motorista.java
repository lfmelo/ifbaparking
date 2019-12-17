package model.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Motorista {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String fone;
	private String hab_num;
	private String hab_cat;
	private Timestamp data_cad;
	
	public Motorista() {
	}
	
	public Motorista(Integer id) {
		this.id = id;
	}
	
	public Motorista(Integer id, String nome, String fone, String hab_num, String hab_cat, Timestamp data_cad) {
		this.id = id;
		this.nome = nome;
		this.fone = fone;
		this.hab_num = hab_num;
		this.hab_cat = hab_cat;
		this.data_cad = data_cad;
	}
	
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getFone() {
		return fone;
	}
	public String getHab_num() {
		return hab_num;
	}
	public String getHab_cat() {
		return hab_cat;
	}
	public Timestamp getData_cad() {
		return data_cad;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public void setHab_num(String hab_num) {
		this.hab_num = hab_num;
	}
	public void setHab_cat(String hab_cat) {
		this.hab_cat = hab_cat;
	}
	public void setData_cad(Timestamp data_cad) {
		this.data_cad = data_cad;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data_cad == null) ? 0 : data_cad.hashCode());
		result = prime * result + ((fone == null) ? 0 : fone.hashCode());
		result = prime * result + ((hab_cat == null) ? 0 : hab_cat.hashCode());
		result = prime * result + ((hab_num == null) ? 0 : hab_num.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Motorista other = (Motorista) obj;
		if (data_cad == null) {
			if (other.data_cad != null)
				return false;
		} else if (!data_cad.equals(other.data_cad))
			return false;
		if (fone == null) {
			if (other.fone != null)
				return false;
		} else if (!fone.equals(other.fone))
			return false;
		if (hab_cat == null) {
			if (other.hab_cat != null)
				return false;
		} else if (!hab_cat.equals(other.hab_cat))
			return false;
		if (hab_num == null) {
			if (other.hab_num != null)
				return false;
		} else if (!hab_num.equals(other.hab_num))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
