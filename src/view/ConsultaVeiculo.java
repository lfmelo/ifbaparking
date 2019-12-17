package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.MaskFormatter;

import model.bean.Veiculo;
import model.dao.VeiculoDAO;
import tableModel.VeiculoTableModel;

public class ConsultaVeiculo extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6369621530674096568L;
	protected JTextField txtModelo;
	protected JTextField txtFabricante;
	protected JFormattedTextField txtPlaca;
	protected JButton btnBuscar = new JButton("Buscar");
	protected ConsultaVeiculo consultaVeiculoTemp;
	protected CadastroVeiculo cadastroVeiculo;

	VeiculoTableModel veiculoTableModel = new VeiculoTableModel();
	private JTable tableListaVeiculos;
	
	/**
	 * Create the frame.
	 */
	public ConsultaVeiculo() {
		((BasicInternalFrameUI)getUI()).setNorthPane(null);
		setResizable(false);
		setBounds(0, 0, 800, 612);
		getContentPane().setLayout(null);

		JPanel panelConsultaVeiculo = new JPanel();
		panelConsultaVeiculo.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelConsultaVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelConsultaVeiculo.setName("");
		panelConsultaVeiculo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultar Veículo", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelConsultaVeiculo.setVisible(true);
		panelConsultaVeiculo.setAutoscrolls(false);
		panelConsultaVeiculo.setBounds(10, 11, 764, 111);
		getContentPane().add(panelConsultaVeiculo);
		panelConsultaVeiculo.setLayout(null);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModelo.setBounds(10, 49, 67, 14);
		panelConsultaVeiculo.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtModelo.setColumns(10);
		txtModelo.setBounds(69, 44, 170, 25);
		panelConsultaVeiculo.add(txtModelo);
		
		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFabricante.setBounds(251, 49, 89, 14);
		panelConsultaVeiculo.add(lblFabricante);
		
		txtFabricante = new JTextField();
		txtFabricante.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFabricante.setColumns(10);
		txtFabricante.setBounds(332, 44, 170, 25);
		panelConsultaVeiculo.add(txtFabricante);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPlaca.setBounds(512, 49, 89, 14);
		panelConsultaVeiculo.add(lblPlaca);
		
		try {
			txtPlaca = new JFormattedTextField(new MaskFormatter("UUU-####"));
			txtPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPlaca.setBounds(564, 44, 80, 25);
		panelConsultaVeiculo.add(txtPlaca);
		txtPlaca.setColumns(10);
		
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscar.setBounds(654, 43, 100, 25);
		panelConsultaVeiculo.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VeiculoDAO veiculoDAO = new VeiculoDAO();
				List<Veiculo> listaVeiculo = veiculoDAO.findByModeloFabricantePlaca(txtModelo.getText(), txtFabricante.getText(), txtPlaca.getText());
				veiculoTableModel.addRows(listaVeiculo);
			}
		});
		
		JScrollPane panelListaResultados = new JScrollPane();
		panelListaResultados.setAutoscrolls(true);
		panelListaResultados.setBounds(10, 133, 764, 362);
		getContentPane().add(panelListaResultados);
		
		tableListaVeiculos = new JTable();
		tableListaVeiculos.setAutoCreateRowSorter(true);
		tableListaVeiculos.setRowHeight(25);
		tableListaVeiculos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableListaVeiculos.setModel(veiculoTableModel);
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		tableListaVeiculos.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableListaVeiculos.getColumnModel().getColumn(0).setCellRenderer(center);
		tableListaVeiculos.getColumnModel().getColumn(1).setPreferredWidth(182);
		tableListaVeiculos.getColumnModel().getColumn(2).setPreferredWidth(182);
		tableListaVeiculos.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableListaVeiculos.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableListaVeiculos.getColumnModel().getColumn(4).setCellRenderer(center);
		panelListaResultados.setViewportView(tableListaVeiculos);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluir.setBounds(624, 522, 150, 50);
		getContentPane().add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableListaVeiculos.getSelectedRow() != -1) {
					int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir registro selecionado?", "Confirmar Excluir", JOptionPane.OK_OPTION);
					if(resposta == 0) {
						Veiculo veiculoRemovido = veiculoTableModel.removeRow(tableListaVeiculos.getSelectedRow());
						if(veiculoRemovido != null && veiculoRemovido.getId() != null) {
							JOptionPane.showMessageDialog(null, "Veículo removido com sucesso.");
							txtModelo.setText("");
							txtFabricante.setText("");
							txtPlaca.setText("");
							btnBuscar.doClick();
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao excluir veículo.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
							txtModelo.setText("");
							txtFabricante.setText("");
							txtPlaca.setText("");
							btnBuscar.doClick();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada.", "Atenção!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		consultaVeiculoTemp = this;
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditar.setBounds(464, 522, 150, 50);
		getContentPane().add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableListaVeiculos.getSelectedRow() != -1) {
					Veiculo veiculoSelecionado = veiculoTableModel.getDadosRow(tableListaVeiculos.getSelectedRow());
					cadastroVeiculo = new CadastroVeiculo(veiculoSelecionado, consultaVeiculoTemp);
					cadastroVeiculo.setVisible(true);
					getParent().add(cadastroVeiculo);
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
				cadastroVeiculo = new CadastroVeiculo(null, consultaVeiculoTemp);
				cadastroVeiculo.setVisible(true);
				getParent().add(cadastroVeiculo);
				hide();
			}
		});
	}
}
