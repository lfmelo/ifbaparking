package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.bean.Usuario;
import model.dao.UsuarioDAO;

public class UsuarioTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4006886909177890363L;
	private List<Usuario> listaUsuario = new ArrayList<>();
	private String[] colunas = {"ID", "Nome", "Matrícula"};

	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}
	
	@Override
	public int getRowCount() {
		return listaUsuario.size();
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
			case 0:
				return listaUsuario.get(linha).getId();
			case 1:
				return listaUsuario.get(linha).getNome();
			case 2:
				return listaUsuario.get(linha).getMatricula();
		}
		return null;
	}
	
	public void addRows(List<Usuario> listaUsuarioBanco) {
		this.listaUsuario.clear();
		this.listaUsuario.addAll(listaUsuarioBanco);
		this.fireTableDataChanged();
	}
	
	public Usuario removeRow(int linha) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.delete(this.listaUsuario.get(linha).getId());
		return usuario;
	}
	
	public Usuario getDadosRow(int linha) {
		return this.listaUsuario.get(linha);
	}

}
