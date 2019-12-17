package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.bean.Veiculo;
import model.dao.VeiculoDAO;

public class VeiculoTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5577591364508184270L;
	private List<Veiculo> listaVeiculo = new ArrayList<>();
	private String[] colunas = {"ID", "Fabricante", "Modelo", "Cor", "Placa"};

	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}
	
	@Override
	public int getRowCount() {
		return listaVeiculo.size();
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
			case 0:
				return listaVeiculo.get(linha).getId();
			case 1:
				return listaVeiculo.get(linha).getFabricante();
			case 2:
				return listaVeiculo.get(linha).getModelo();
			case 3:
				return listaVeiculo.get(linha).getCor();
			case 4:
				return listaVeiculo.get(linha).getPlaca();
		}
		return null;
	}
	
	public void addRows(List<Veiculo> listaVeiculoBanco) {
		this.listaVeiculo.clear();
		this.listaVeiculo.addAll(listaVeiculoBanco);
		this.fireTableDataChanged();
	}
	
	public Veiculo removeRow(int linha) {
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		Veiculo veiculo = veiculoDAO.delete(this.listaVeiculo.get(linha).getId());
		return veiculo;
	}
	
	public Veiculo getDadosRow(int linha) {
		return this.listaVeiculo.get(linha);
	}
	
}
