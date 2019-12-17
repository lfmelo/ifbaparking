package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;

import model.bean.Usuario;
import model.dao.UsuarioDAO;
import tableModel.UsuarioTableModel;

public class ConsultaUsuario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8328552221216693455L;
	protected JTextField txtNome;
	protected JTextField txtMatricula;
	protected JButton btnBuscar = new JButton("Buscar");
	protected ConsultaUsuario consultaUsuarioTemp;
	protected CadastroUsuario cadastroUsuario;
	
	UsuarioTableModel usuarioTableModel = new UsuarioTableModel();
	private JTable tableListaUsuarios;
	
	/**
	 * Create the frame.
	 */
	public ConsultaUsuario() {
		((BasicInternalFrameUI)getUI()).setNorthPane(null);
		setResizable(false);
		setBounds(0, 0, 800, 612);
		getContentPane().setLayout(null);
		
		JPanel panelConsultaUsuario = new JPanel();
		panelConsultaUsuario.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelConsultaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelConsultaUsuario.setName("");
		panelConsultaUsuario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultar Usuário", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelConsultaUsuario.setVisible(true);
		panelConsultaUsuario.setAutoscrolls(false);
		panelConsultaUsuario.setBounds(10, 11, 764, 111);
		getContentPane().add(panelConsultaUsuario);
		panelConsultaUsuario.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(10, 49, 67, 14);
		panelConsultaUsuario.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setColumns(10);
		txtNome.setBounds(69, 44, 301, 25);
		panelConsultaUsuario.add(txtNome);
		
		JLabel lblMatricula = new JLabel("Matr\u00EDcula:");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMatricula.setBounds(380, 49, 67, 14);
		panelConsultaUsuario.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(457, 44, 187, 25);
		panelConsultaUsuario.add(txtMatricula);
		
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscar.setBounds(654, 43, 100, 25);
		panelConsultaUsuario.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				List<Usuario> listaUsuario = usuarioDAO.findByNomeMatricula(txtNome.getText(), txtMatricula.getText());
				usuarioTableModel.addRows(listaUsuario);
			}
		});
		
		JScrollPane panelListaResultados = new JScrollPane();
		panelListaResultados.setAutoscrolls(true);
		panelListaResultados.setBounds(10, 133, 764, 362);
		getContentPane().add(panelListaResultados);
		
		tableListaUsuarios = new JTable();
		tableListaUsuarios.setAutoCreateRowSorter(true);
		tableListaUsuarios.setRowHeight(25);
		tableListaUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableListaUsuarios.setModel(usuarioTableModel);
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		tableListaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableListaUsuarios.getColumnModel().getColumn(0).setCellRenderer(center);
		tableListaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(514);
		tableListaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(150);
		panelListaResultados.setViewportView(tableListaUsuarios);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluir.setBounds(624, 522, 150, 50);
		getContentPane().add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableListaUsuarios.getSelectedRow() != -1) {
					int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir registro selecionado?", "Confirmar Excluir", JOptionPane.OK_OPTION);
					if(resposta == 0) {
						Usuario usuarioRemovido = usuarioTableModel.removeRow(tableListaUsuarios.getSelectedRow());
						if(usuarioRemovido != null && usuarioRemovido.getId() != null) {
							JOptionPane.showMessageDialog(null, "Usuário " + usuarioRemovido.getNome() + " removido com sucesso.");
							txtNome.setText("");
							txtMatricula.setText("");
							btnBuscar.doClick();
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao excluir usuário.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
							txtNome.setText("");
							txtMatricula.setText("");
							btnBuscar.doClick();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada.", "Atenção!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		consultaUsuarioTemp = this;

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditar.setBounds(464, 522, 150, 50);
		getContentPane().add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableListaUsuarios.getSelectedRow() != -1) {
					Usuario usuarioSelecionado = usuarioTableModel.getDadosRow(tableListaUsuarios.getSelectedRow());
					cadastroUsuario = new CadastroUsuario(usuarioSelecionado, consultaUsuarioTemp);
					cadastroUsuario.setVisible(true);
					getParent().add(cadastroUsuario);
					hide();
				} else {
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada.", "Atenção!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNovo.setBounds(304, 522, 150, 50);
		getContentPane().add(btnNovo);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastroUsuario = new CadastroUsuario(null, consultaUsuarioTemp);
				cadastroUsuario.setVisible(true);
				getParent().add(cadastroUsuario);
				hide();
			}
		});
	}
}
