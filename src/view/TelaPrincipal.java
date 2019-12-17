package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import model.bean.Movimento;
import model.bean.Veiculo;
import model.bean.enumeration.MovimentoEnum;
import model.dao.MovimentoDAO;
import model.dao.VeiculoDAO;
import tableModel.MovimentoTableModel;

public class TelaPrincipal extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6254280331408776899L;
	private JTextField txtVeiculo;
	private JTextField txtCor;
	private JTextField txtFabricante;
	private JTextField txtPlaca;
	private JTextField txtProprietario;
	private JTextField txtCondutor;
	private Color autorizado = new Color(0, 128, 0);
	private Color naoAutorizado = Color.RED;
	private JTextField txtAviso;
	protected JButton btnAbrir = new JButton("Abrir Cancela");
	protected JButton btnFechar = new JButton("Fechar Cancela");
	protected Veiculo veiculo = new Veiculo();

	MovimentoTableModel movimentoTableModel = new MovimentoTableModel();
	private JTable tableListaMovimentos;
	
	/**
	 * Create the frame.
	 */
	public TelaPrincipal(String etiqueta, PrincipalFrame principal) {
		((BasicInternalFrameUI)getUI()).setNorthPane(null);
		setResizable(false);
		setBounds(0, 0, 800, 612);
		getContentPane().setLayout(null);
		
		//Panel Identificação
		JPanel panelIdentificacao = new JPanel();
		panelIdentificacao.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelIdentificacao.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelIdentificacao.setName("");
		panelIdentificacao.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ve\u00EDculo Identificado", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelIdentificacao.setVisible(true);
		panelIdentificacao.setAutoscrolls(false);
		panelIdentificacao.setBounds(10, 11, 764, 350);
		getContentPane().add(panelIdentificacao);
		panelIdentificacao.setLayout(null);
		
		JLabel lblVeiculo = new JLabel("Veículo:");
		lblVeiculo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblVeiculo.setBounds(10, 35, 83, 20);
		panelIdentificacao.add(lblVeiculo);
		
		txtVeiculo = new JTextField();
		txtVeiculo.setBorder(null);
		txtVeiculo.setEditable(false);
		txtVeiculo.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtVeiculo.setColumns(10);
		txtVeiculo.setBounds(103, 33, 651, 25);
		panelIdentificacao.add(txtVeiculo);
		
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCor.setBounds(10, 145, 83, 20);
		panelIdentificacao.add(lblCor);
		
		txtCor = new JTextField();
		txtCor.setBorder(null);
		txtCor.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCor.setEditable(false);
		txtCor.setColumns(10);
		txtCor.setBounds(70, 143, 319, 25);
		panelIdentificacao.add(txtCor);
		
		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFabricante.setBounds(10, 90, 131, 20);
		panelIdentificacao.add(lblFabricante);
		
		txtFabricante = new JTextField();
		txtFabricante.setBorder(null);
		txtFabricante.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtFabricante.setEditable(false);
		txtFabricante.setColumns(10);
		txtFabricante.setBounds(138, 88, 616, 25);
		panelIdentificacao.add(txtFabricante);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPlaca.setBounds(411, 145, 83, 20);
		panelIdentificacao.add(lblPlaca);
		
		txtPlaca = new JTextField();
		txtPlaca.setBorder(null);
		txtPlaca.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtPlaca.setEditable(false);
		txtPlaca.setColumns(10);
		txtPlaca.setBounds(504, 143, 250, 25);
		panelIdentificacao.add(txtPlaca);
		
		JLabel lblProprietario = new JLabel("Proprietário:");
		lblProprietario.setBounds(10, 200, 140, 20);
		panelIdentificacao.add(lblProprietario);
		lblProprietario.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		txtProprietario = new JTextField();
		txtProprietario.setBounds(151, 198, 603, 25);
		panelIdentificacao.add(txtProprietario);
		txtProprietario.setBorder(null);
		txtProprietario.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtProprietario.setEditable(false);
		txtProprietario.setColumns(10);
		
		JLabel lblCondutor = new JLabel("Condutor:");
		lblCondutor.setBounds(10, 255, 119, 20);
		panelIdentificacao.add(lblCondutor);
		lblCondutor.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		txtCondutor = new JTextField();
		txtCondutor.setBounds(138, 253, 616, 25);
		panelIdentificacao.add(txtCondutor);
		txtCondutor.setBorder(null);
		txtCondutor.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCondutor.setEditable(false);
		txtCondutor.setColumns(10);
		
		//AKI VAI APARECER O VEÍCULO PESQUISADO PELA ETIQUETA DEPOIS QUE LER NO ARDUÍNO
		if(StringUtils.isNotBlank(etiqueta)) {
			VeiculoDAO veiculoDAO = new VeiculoDAO();
			veiculo = veiculoDAO.findByEtiqueta(etiqueta);
			if(veiculo != null && veiculo.getId() != null) {
				txtVeiculo.setText(veiculo.getModelo());
				txtFabricante.setText(veiculo.getFabricante());
				txtCor.setText(veiculo.getCor());
				txtPlaca.setText(veiculo.getPlaca());
				txtProprietario.setText(veiculo.getProprietario());
				txtCondutor.setText(veiculo.getMotorista().getNome());
				
				txtVeiculo.setForeground(BooleanUtils.isTrue(veiculo.getPermissao()) ? autorizado : naoAutorizado);
				txtCor.setForeground(BooleanUtils.isTrue(veiculo.getPermissao()) ? autorizado : naoAutorizado);
				txtProprietario.setForeground(BooleanUtils.isTrue(veiculo.getPermissao()) ? autorizado : naoAutorizado);
				txtCondutor.setForeground(BooleanUtils.isTrue(veiculo.getPermissao()) ? autorizado : naoAutorizado);
				txtFabricante.setForeground(BooleanUtils.isTrue(veiculo.getPermissao()) ? autorizado : naoAutorizado);
				txtPlaca.setForeground(BooleanUtils.isTrue(veiculo.getPermissao()) ? autorizado : naoAutorizado);
				
				txtAviso = new JTextField();
				txtAviso.setForeground(BooleanUtils.isTrue(veiculo.getPermissao()) ? autorizado : naoAutorizado);
				txtAviso.setText(BooleanUtils.isTrue(veiculo.getPermissao()) ? "Veículo Autorizado!" : "Veículo Não Autorizado");
				txtAviso.setHorizontalAlignment(SwingConstants.CENTER);
				txtAviso.setFont(new Font("Tahoma", Font.BOLD, 28));
				txtAviso.setEditable(false);
				txtAviso.setColumns(10);
				txtAviso.setBorder(null);
				txtAviso.setBounds(10, 289, 744, 53);
				panelIdentificacao.add(txtAviso);
				if(BooleanUtils.isTrue(veiculo.getPermissao())) {
					principal.arduino.send("L");
					MovimentoDAO movimentoDAO = new MovimentoDAO();
					Movimento mov = new Movimento();
					mov.setData(new Timestamp(System.currentTimeMillis()));
					mov.setVeiculo(veiculo);
					if(BooleanUtils.isTrue(veiculo.getLocal())) {
						mov.setMovimento(MovimentoEnum.SAIDA);
					} else {
						mov.setMovimento(MovimentoEnum.ENTRADA);
					}
					movimentoDAO.saveOrUpdate(mov);
				} else {
					principal.arduino.send("B");
				}
			} else {
				principal.arduino.send("B");
				txtAviso = new JTextField();
				txtAviso.setForeground(naoAutorizado);
				txtAviso.setText("Veículo não cadastrado.");
				txtAviso.setHorizontalAlignment(SwingConstants.CENTER);
				txtAviso.setFont(new Font("Tahoma", Font.BOLD, 28));
				txtAviso.setEditable(false);
				txtAviso.setColumns(10);
				txtAviso.setBorder(null);
				txtAviso.setBounds(10, 289, 744, 53);
				panelIdentificacao.add(txtAviso);
			}
		}
		////////////////////////////////////////////////////////////////////////////////
		
		JScrollPane panelListaResultados = new JScrollPane();
		panelListaResultados.setAutoscrolls(true);
		panelListaResultados.setBounds(10, 372, 764, 200);
		getContentPane().add(panelListaResultados);
		
		MovimentoDAO movimentoDAO = new MovimentoDAO();
		List<Movimento> listaMovimento = movimentoDAO.findAll(true);
		movimentoTableModel.addRows(listaMovimento);
		
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