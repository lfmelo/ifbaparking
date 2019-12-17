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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

import model.bean.Movimento;
import model.dao.MovimentoDAO;
import tableModel.MovimentoTableModel;

public class ConsultaMovimento extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6061669584161598998L;
	protected JFormattedTextField txtDataInicio;
	protected JFormattedTextField txtDataFim;
	protected JFormattedTextField txtPlaca;
	protected JButton btnBuscar = new JButton("Buscar");
	
	MovimentoTableModel movimentoTableModel = new MovimentoTableModel();
	private JTable tableListaMovimentos;

	/**
	 * Create the frame.
	 */
	public ConsultaMovimento() {
		((BasicInternalFrameUI)getUI()).setNorthPane(null);
		setResizable(false);
		setBounds(0, 0, 800, 612);
		getContentPane().setLayout(null);
		
		JPanel panelConsultaMovimento = new JPanel();
		panelConsultaMovimento.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelConsultaMovimento.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelConsultaMovimento.setName("");
		panelConsultaMovimento.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultar Movimentação de Veículo", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelConsultaMovimento.setVisible(true);
		panelConsultaMovimento.setAutoscrolls(false);
		panelConsultaMovimento.setBounds(10, 11, 764, 111);
		getContentPane().add(panelConsultaMovimento);
		panelConsultaMovimento.setLayout(null);
		
		JLabel lblDataInicio = new JLabel("Data Inicial:");
		lblDataInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDataInicio.setBounds(10, 49, 101, 14);
		panelConsultaMovimento.add(lblDataInicio);
		
		try {
			txtDataInicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
			txtDataInicio.setHorizontalAlignment(SwingConstants.CENTER);
			txtDataInicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtDataInicio.setColumns(10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtDataInicio.setBounds(100, 44, 150, 25);
		panelConsultaMovimento.add(txtDataInicio);
		
		JLabel lblDataFim = new JLabel("Data Final:");
		lblDataFim.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDataFim.setBounds(270, 49, 89, 14);
		panelConsultaMovimento.add(lblDataFim);
		
		try {
			txtDataFim = new JFormattedTextField(new MaskFormatter("##/##/####"));
			txtDataFim.setHorizontalAlignment(SwingConstants.CENTER);
			txtDataFim.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtDataFim.setColumns(10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtDataFim.setBounds(352, 44, 150, 25);
		panelConsultaMovimento.add(txtDataFim);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPlaca.setBounds(512, 49, 89, 14);
		panelConsultaMovimento.add(lblPlaca);
		
		try {
			txtPlaca = new JFormattedTextField(new MaskFormatter("UUU-####"));
			txtPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPlaca.setBounds(564, 44, 80, 25);
		panelConsultaMovimento.add(txtPlaca);
		txtPlaca.setColumns(10);
		
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscar.setBounds(654, 43, 100, 25);
		panelConsultaMovimento.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovimentoDAO movimentoDAO = new MovimentoDAO();
				List<Movimento> listaMovimento = movimentoDAO.findByDataPlaca(txtDataInicio.getText(), txtDataFim.getText(), txtPlaca.getText());
				movimentoTableModel.addRows(listaMovimento);
			}
		});
		
		JScrollPane panelListaResultados = new JScrollPane();
		panelListaResultados.setAutoscrolls(true);
		panelListaResultados.setBounds(10, 133, 764, 439);
		getContentPane().add(panelListaResultados);
		
		tableListaMovimentos = new JTable();
		tableListaMovimentos.setAutoCreateRowSorter(true);
		tableListaMovimentos.setRowHeight(25);
		tableListaMovimentos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableListaMovimentos.setModel(movimentoTableModel);
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		tableListaMovimentos.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableListaMovimentos.getColumnModel().getColumn(0).setCellRenderer(center);
		tableListaMovimentos.getColumnModel().getColumn(1).setPreferredWidth(244);
		tableListaMovimentos.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableListaMovimentos.getColumnModel().getColumn(2).setCellRenderer(center);
		tableListaMovimentos.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableListaMovimentos.getColumnModel().getColumn(3).setCellRenderer(center);
		tableListaMovimentos.getColumnModel().getColumn(4).setPreferredWidth(160);
		tableListaMovimentos.getColumnModel().getColumn(4).setCellRenderer(center);
		tableListaMovimentos.getColumnModel().getColumn(5).setPreferredWidth(80);
		panelListaResultados.setViewportView(tableListaMovimentos);
	}

}
