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

import model.bean.Motorista;
import model.dao.MotoristaDAO;
import tableModel.MotoristaTableModel;

public class ConsultaMotorista extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 589758944785295928L;
	protected JTextField txtNome;
	protected JTextField txtHabilitacao;
	protected JButton btnBuscar = new JButton("Buscar");
	protected ConsultaMotorista consultaMotoristaTemp;
	protected CadastroMotorista cadastroMotorista;
	
	MotoristaTableModel motoristaTableModel = new MotoristaTableModel();
	private JTable tableListaMotoristas;
	
	/**
	 * Create the frame.
	 */
	public ConsultaMotorista() {
		((BasicInternalFrameUI)getUI()).setNorthPane(null);
		setResizable(false);
		setBounds(0, 0, 800, 612);
		getContentPane().setLayout(null);
		
		JPanel panelConsultaMotorista = new JPanel();
		panelConsultaMotorista.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelConsultaMotorista.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelConsultaMotorista.setName("");
		panelConsultaMotorista.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultar Motorista", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelConsultaMotorista.setVisible(true);
		panelConsultaMotorista.setAutoscrolls(false);
		panelConsultaMotorista.setBounds(10, 11, 764, 111);
		getContentPane().add(panelConsultaMotorista);
		panelConsultaMotorista.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(10, 49, 67, 14);
		panelConsultaMotorista.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setColumns(10);
		txtNome.setBounds(69, 44, 301, 25);
		panelConsultaMotorista.add(txtNome);
		
		JLabel lblHabilitacao = new JLabel("Habilitação:");
//		JLabel lblHabilitacao = new JLabel("Matr\u00EDcula:");
		lblHabilitacao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHabilitacao.setBounds(380, 49, 86, 14);
		panelConsultaMotorista.add(lblHabilitacao);
		
		txtHabilitacao = new JTextField();
		txtHabilitacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHabilitacao.setColumns(10);
		txtHabilitacao.setBounds(464, 44, 180, 25);
		panelConsultaMotorista.add(txtHabilitacao);
		
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscar.setBounds(654, 43, 100, 25);
		panelConsultaMotorista.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MotoristaDAO motoristaDAO = new MotoristaDAO();
				List<Motorista> listaMotorista = motoristaDAO.findByNomeHabilitacao(txtNome.getText(), txtHabilitacao.getText());
				motoristaTableModel.addRows(listaMotorista);
			}
		});
		
		JScrollPane panelListaResultados = new JScrollPane();
		panelListaResultados.setAutoscrolls(true);
		panelListaResultados.setBounds(10, 133, 764, 362);
		getContentPane().add(panelListaResultados);
		
		tableListaMotoristas = new JTable();
		tableListaMotoristas.setAutoCreateRowSorter(true);
		tableListaMotoristas.setRowHeight(25);
		tableListaMotoristas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableListaMotoristas.setModel(motoristaTableModel);
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		tableListaMotoristas.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableListaMotoristas.getColumnModel().getColumn(0).setCellRenderer(center);
		tableListaMotoristas.getColumnModel().getColumn(1).setPreferredWidth(514);
		tableListaMotoristas.getColumnModel().getColumn(2).setPreferredWidth(150);
		panelListaResultados.setViewportView(tableListaMotoristas);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluir.setBounds(624, 522, 150, 50);
		getContentPane().add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableListaMotoristas.getSelectedRow() != -1) {
					int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir registro selecionado?", "Confirmar Excluir", JOptionPane.OK_OPTION);
					if(resposta == 0) {
						Motorista motoristaRemovido = motoristaTableModel.removeRow(tableListaMotoristas.getSelectedRow());
						if(motoristaRemovido != null && motoristaRemovido.getId() != null) {
							JOptionPane.showMessageDialog(null, "Motorista " + motoristaRemovido.getNome() + " removido com sucesso.");
							txtNome.setText("");
							txtHabilitacao.setText("");
							btnBuscar.doClick();
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao excluir motorista.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
							txtNome.setText("");
							txtHabilitacao.setText("");
							btnBuscar.doClick();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada.", "Atenção!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		consultaMotoristaTemp = this;
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditar.setBounds(464, 522, 150, 50);
		getContentPane().add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableListaMotoristas.getSelectedRow() != -1) {
					Motorista motoristaSelecionado = motoristaTableModel.getDadosRow(tableListaMotoristas.getSelectedRow());
					cadastroMotorista = new CadastroMotorista(motoristaSelecionado, consultaMotoristaTemp);
					cadastroMotorista.setVisible(true);
					getParent().add(cadastroMotorista);
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
				cadastroMotorista = new CadastroMotorista(null, consultaMotoristaTemp);
				cadastroMotorista.setVisible(true);
				getParent().add(cadastroMotorista);
				hide();
			}
		});
	}
}
