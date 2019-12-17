package tableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.bean.Movimento;

public class MovimentoTableModel  extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7365481547419843545L;
	private List<Movimento> listaMovimento = new ArrayList<>();
	private String[] colunas = {"ID", "Veículo", "Cor", "Placa", "Horário", "Direção"};
	
	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}
	
	@Override
	public int getRowCount() {
		return listaMovimento.size();
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	
	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
			case 0:
				return listaMovimento.get(linha).getId();
			case 1:
				return listaMovimento.get(linha).getVeiculo().getModelo();
			case 2:
				return listaMovimento.get(linha).getVeiculo().getCor();
			case 3:
				return listaMovimento.get(linha).getVeiculo().getPlaca();
			case 4:
				return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(listaMovimento.get(linha).getData());
			case 5:
				return listaMovimento.get(linha).getMovimento().toString();
		}
		return null;
	}
	
	public void addRows(List<Movimento> listaMovimentoBanco) {
		this.listaMovimento.clear();
		this.listaMovimento.addAll(listaMovimentoBanco);
		this.fireTableDataChanged();
	}
	
	public Movimento getDadosRow(int linha) {
		return this.listaMovimento.get(linha);
	}
	
}
