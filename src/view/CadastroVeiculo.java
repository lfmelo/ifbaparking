package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;

import model.bean.Motorista;
import model.bean.Veiculo;
import model.dao.MotoristaDAO;
import model.dao.VeiculoDAO;

public class CadastroVeiculo extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4479828360888111773L;
	private JTextField txtID;
	private JTextField txtTipo;
	private JTextField txtFabricante;
	private JTextField txtModelo;
	private JTextField txtCor;
	private JTextField txtProprietario;
	private JTextField txtEtiqueta;
	private JFormattedTextField txtAno;
	private JFormattedTextField txtPlaca;
	private JComboBox<Motorista> comboMotorista;
	
	/**
	 * Create the frame.
	 */
	public CadastroVeiculo(Veiculo veiculoCadastro, ConsultaVeiculo consultaVeiculo) {
		((BasicInternalFrameUI)getUI()).setNorthPane(null);
		setResizable(false);
		setBounds(0, 0, 800, 612);
		getContentPane().setLayout(null);
		
		JPanel panelCadastroVeiculo = new JPanel();
		panelCadastroVeiculo.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelCadastroVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCadastroVeiculo.setName("");
		panelCadastroVeiculo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastrar Veículo", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCadastroVeiculo.setVisible(true);
		panelCadastroVeiculo.setAutoscrolls(false);
		panelCadastroVeiculo.setBounds(10, 11, 764, 561);
		getContentPane().add(panelCadastroVeiculo);
		panelCadastroVeiculo.setLayout(null);
		
		txtID = new JTextField();
		txtID.setVisible(false);
		txtID.setBounds(31, 43, 86, 20);
		panelCadastroVeiculo.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipo.setBounds(31, 100, 67, 20);
		panelCadastroVeiculo.add(lblTipo);
		
		txtTipo = new JTextField();
		txtTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTipo.setBounds(92, 98, 262, 25);
		panelCadastroVeiculo.add(txtTipo);
		txtTipo.setColumns(10);
		
		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFabricante.setBounds(364, 100, 86, 20);
		panelCadastroVeiculo.add(lblFabricante);
		
		txtFabricante = new JTextField();
		txtFabricante.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFabricante.setBounds(460, 98, 294, 25);
		panelCadastroVeiculo.add(txtFabricante);
		txtFabricante.setColumns(10);
		
		//100 400 500
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModelo.setBounds(31, 155, 67, 20);
		panelCadastroVeiculo.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtModelo.setBounds(92, 153, 358, 25);
		panelCadastroVeiculo.add(txtModelo);
		txtModelo.setColumns(10);
		
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCor.setBounds(464, 155, 67, 20);
		panelCadastroVeiculo.add(lblCor);
		
		txtCor = new JTextField();
		txtCor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCor.setBounds(510, 153, 244, 25);
		panelCadastroVeiculo.add(txtCor);
		txtCor.setColumns(10);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAno.setBounds(31, 208, 67, 20);
		panelCadastroVeiculo.add(lblAno);
		
		try {
			txtAno = new JFormattedTextField(new MaskFormatter("####"));
			txtAno.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtAno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAno.setBounds(92, 208, 105, 25);
		panelCadastroVeiculo.add(txtAno);
		txtAno.setColumns(10);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPlaca.setBounds(229, 208, 67, 20);
		panelCadastroVeiculo.add(lblPlaca);
		
		try {
			txtPlaca = new JFormattedTextField(new MaskFormatter("UUU-####"));
			txtPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPlaca.setBounds(283, 208, 105, 25);
		panelCadastroVeiculo.add(txtPlaca);
		txtPlaca.setColumns(10);
		
		JLabel lblEtiqueta = new JLabel("Etiqueta:");
		lblEtiqueta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEtiqueta.setBounds(398, 208, 67, 20);
		panelCadastroVeiculo.add(lblEtiqueta);
		
		txtEtiqueta = new JTextField();
		txtEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEtiqueta.setBounds(475, 208, 279, 25);
		panelCadastroVeiculo.add(txtEtiqueta);
		txtEtiqueta.setColumns(10);
		
		JLabel lblProprietario = new JLabel("Proprietário:");
		lblProprietario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProprietario.setBounds(31, 263, 105, 20);
		panelCadastroVeiculo.add(lblProprietario);
		
		txtProprietario = new JTextField();
		txtProprietario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProprietario.setBounds(146, 261, 608, 25);
		panelCadastroVeiculo.add(txtProprietario);
		txtProprietario.setColumns(10);
		
		
		JLabel lblCondutor = new JLabel("Condutor:");
		lblCondutor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCondutor.setBounds(31, 318, 105, 20);
		panelCadastroVeiculo.add(lblCondutor);
		
		comboMotorista = new JComboBox<Motorista>();
		comboMotorista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboMotorista.setAutoscrolls(true);
		comboMotorista.setBounds(146, 313, 608, 30);
		
		MotoristaDAO motoristaDAO = new MotoristaDAO();
		List<Motorista> listaMotorista = motoristaDAO.findAll();
		if(listaMotorista != null && !listaMotorista.isEmpty()) {
			for(Motorista item : listaMotorista) {
				comboMotorista.addItem(item);
			}
		}
		panelCadastroVeiculo.add(comboMotorista);
		
		JCheckBox permissao = new JCheckBox("Entrada Autorizada");
		permissao.setFont(new Font("Tahoma", Font.BOLD, 14));
		permissao.setBounds(31, 379, 166, 23);
		panelCadastroVeiculo.add(permissao);
		
		JCheckBox local = new JCheckBox("Veículo Estacionado");
		local.setFont(new Font("Tahoma", Font.BOLD, 14));
		local.setBounds(229, 379, 166, 23);
		panelCadastroVeiculo.add(local);
		
		JCheckBox exibirTag = new JCheckBox("Cadastrar Tag");
		exibirTag.setFont(new Font("Tahoma", Font.BOLD, 14));
		exibirTag.setBounds(428, 379, 166, 23);
		panelCadastroVeiculo.add(exibirTag);
		exibirTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(exibirTag.isSelected()) {
					lblEtiqueta.setVisible(true);
					txtEtiqueta.setVisible(true);
				} else {
					lblEtiqueta.setVisible(false);
					txtEtiqueta.setText("");
					txtEtiqueta.setVisible(false);
				}
			}
		});
		
		if(veiculoCadastro != null && veiculoCadastro.getId() != null) {
			if(veiculoCadastro.getCadastrar_tag() != null ? veiculoCadastro.getCadastrar_tag() : false) {
				lblEtiqueta.setVisible(true);
				txtEtiqueta.setVisible(true);
			} else {
				lblEtiqueta.setVisible(false);
				txtEtiqueta.setText("");
				txtEtiqueta.setVisible(false);
			}
			
			txtID.setText(veiculoCadastro.getId().toString());
			txtTipo.setText(veiculoCadastro.getTipo());
			txtFabricante.setText(veiculoCadastro.getFabricante());
			txtModelo.setText(veiculoCadastro.getModelo());
			txtCor.setText(veiculoCadastro.getCor());
			txtProprietario.setText(veiculoCadastro.getProprietario());
			txtAno.setText(veiculoCadastro.getAno());
			txtPlaca.setText(veiculoCadastro.getPlaca());
			txtEtiqueta.setText(veiculoCadastro.getEtiqueta_id());
			permissao.setSelected(veiculoCadastro.getPermissao() != null ? veiculoCadastro.getPermissao() : false);
			local.setSelected(veiculoCadastro.getLocal() != null ? veiculoCadastro.getLocal() : false);
			exibirTag.setSelected(veiculoCadastro.getCadastrar_tag() != null ? veiculoCadastro.getCadastrar_tag() : false);
			if(veiculoCadastro.getMotorista() != null) {
				comboMotorista.setSelectedItem(veiculoCadastro.getMotorista());
			}
		} else {
			exibirTag.setSelected(true);
		}
		
		JButton btnSalvarVeiculo = new JButton("Gravar");
		btnSalvarVeiculo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalvarVeiculo.setBounds(444, 500, 150, 50);
		panelCadastroVeiculo.add(btnSalvarVeiculo);
		btnSalvarVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer idVeiculo = StringUtils.isNotBlank(txtID.getText()) ? Integer.parseInt(txtID.getText()) : null; 
				Veiculo veiculo = new Veiculo(idVeiculo, (Motorista) comboMotorista.getSelectedItem(), txtTipo.getText(), txtFabricante.getText(), txtModelo.getText(), 
												txtCor.getText(), txtAno.getText(), txtPlaca.getText(), txtProprietario.getText(), permissao.isSelected(), 
												new Timestamp(System.currentTimeMillis()), txtEtiqueta.getText().trim(), local.isSelected(), exibirTag.isSelected());
						
				VeiculoDAO veiculoDAO = new VeiculoDAO();
				veiculo = veiculoDAO.saveOrUpdate(veiculo);
				if(veiculo != null && veiculo.getId() != null) {
					JOptionPane.showMessageDialog(null, "Veiculo salvo com sucesso.");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao salvar veículo.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
				consultaVeiculo.txtModelo.setText("");
				consultaVeiculo.txtFabricante.setText("");
				consultaVeiculo.txtPlaca.setText("");
				consultaVeiculo.btnBuscar.doClick();
				consultaVeiculo.show();
				
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(604, 500, 150, 50);
		panelCadastroVeiculo.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				consultaVeiculo.txtModelo.setText("");
				consultaVeiculo.txtFabricante.setText("");
				consultaVeiculo.txtPlaca.setText("");
				consultaVeiculo.btnBuscar.doClick();
				consultaVeiculo.show();
			}
		});
	}
}
