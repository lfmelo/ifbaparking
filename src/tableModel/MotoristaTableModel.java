package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.bean.Motorista;
import model.dao.MotoristaDAO;

public class MotoristaTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8928503718660787069L;
	private List<Motorista> listaMotorista = new ArrayList<>();
	private String[] colunas = {"ID", "Nome", "Habilitação"};

	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}
	
	@Override
	public int getRowCount() {
		return listaMotorista.size();
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
			case 0:
				return listaMotorista.get(linha).getId();
			case 1:
				return listaMotorista.get(linha).getNome();
			case 2:
				return listaMotorista.get(linha).getHab_num();
		}
		return null;
	}
	
	public void addRows(List<Motorista> listaMotoristaBanco) {
		this.listaMotorista.clear();
		this.listaMotorista.addAll(listaMotoristaBanco);
		this.fireTableDataChanged();
	}
	
	public Motorista removeRow(int linha) {
		MotoristaDAO motoristaDAO = new MotoristaDAO();
		Motorista motorista = motoristaDAO.delete(this.listaMotorista.get(linha).getId());
		return motorista;
	}
	
	public Motorista getDadosRow(int linha) {
		return this.listaMotorista.get(linha);
	}

}
