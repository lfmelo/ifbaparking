package model.bean.enumeration;

public enum MovimentoEnum {

	ENTRADA (1, "Entrada"),
	SAIDA (2, "Saida");
	
	private Integer codigo;
	private String descricao;
	
	private MovimentoEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}
}
