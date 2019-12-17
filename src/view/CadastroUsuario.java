package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;

import model.bean.Usuario;
import model.dao.UsuarioDAO;

public class CadastroUsuario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2485009407097429104L;
	private JTextField txtNome;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtNascimento;
	private JTextField txtMatricula;
	private JPasswordField txtSenha;
	private JTextField txtID;
	private SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Create the frame.
	 */
	public CadastroUsuario(Usuario usuarioCadastro, ConsultaUsuario consultaUsuario) {
		((BasicInternalFrameUI)getUI()).setNorthPane(null);
		setResizable(false);
		setBounds(0, 0, 800, 612);
		getContentPane().setLayout(null);
		
		JPanel panelCadastroUsuario = new JPanel();
		panelCadastroUsuario.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelCadastroUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCadastroUsuario.setName("");
		panelCadastroUsuario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastrar Usuário", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCadastroUsuario.setVisible(true);
		panelCadastroUsuario.setAutoscrolls(false);
		panelCadastroUsuario.setBounds(10, 11, 764, 561);
		getContentPane().add(panelCadastroUsuario);
		panelCadastroUsuario.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(31, 100, 67, 14);
		panelCadastroUsuario.add(lblNome);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCPF.setBounds(31, 160, 67, 14);
		panelCadastroUsuario.add(lblCPF);
		
		JLabel lblMatricula = new JLabel("Matr\u00EDcula:");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMatricula.setBounds(31, 220, 67, 14);
		panelCadastroUsuario.add(lblMatricula);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(400, 220, 67, 14);
		panelCadastroUsuario.add(lblSenha);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDataDeNascimento.setBounds(400, 160, 171, 14);
		panelCadastroUsuario.add(lblDataDeNascimento);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setBounds(108, 95, 646, 25);
		panelCadastroUsuario.add(txtNome);
		txtNome.setColumns(10);
		
		try {
			txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
			txtCPF.setColumns(10);
			txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtCPF.setBounds(108, 157, 262, 25);
		panelCadastroUsuario.add(txtCPF);
		
		try {
			txtNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
			txtNascimento.setColumns(10);
			txtNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtNascimento.setBounds(573, 157, 181, 25);
		panelCadastroUsuario.add(txtNascimento);
		
		txtMatricula = new JTextField();
		txtMatricula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMatricula.setBounds(108, 217, 262, 25);
		panelCadastroUsuario.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSenha.setBounds(477, 217, 277, 25);
		panelCadastroUsuario.add(txtSenha);
		
		txtID = new JTextField();
		txtID.setVisible(false);
		txtID.setBounds(31, 43, 86, 20);
		panelCadastroUsuario.add(txtID);
		txtID.setColumns(10);
		
		if(usuarioCadastro != null && usuarioCadastro.getId() != null) {
			txtID.setText(usuarioCadastro.getId().toString());
			txtNome.setText(usuarioCadastro.getNome());
			txtMatricula.setText(usuarioCadastro.getMatricula());
			txtCPF.setText(usuarioCadastro.getCpf());
			txtSenha.setText(usuarioCadastro.getSenha());
			txtNascimento.setText(formataData.format(usuarioCadastro.getNascimento()));
		}
		
		JButton btnSalvarUsuario = new JButton("Gravar");
		btnSalvarUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalvarUsuario.setBounds(444, 500, 150, 50);
		panelCadastroUsuario.add(btnSalvarUsuario);
		btnSalvarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataNascimento = null;
				try {
					dataNascimento = new Date(formataData.parse(txtNascimento.getText()).getTime());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				Integer idUsuario = StringUtils.isNotBlank(txtID.getText()) ? Integer.parseInt(txtID.getText()) : null; 
				Usuario usuario = new Usuario(idUsuario, txtCPF.getText(), txtNome.getText(), dataNascimento, 
												txtMatricula.getText(), new String(txtSenha.getPassword()));
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuario = usuarioDAO.saveOrUpdate(usuario);
				if(usuario != null && usuario.getId() != null) {
					JOptionPane.showMessageDialog(null, "Usuario " + usuario.getNome() + " salvo com sucesso.");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao salvar usuário.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
				consultaUsuario.txtNome.setText("");
				consultaUsuario.txtMatricula.setText("");
				consultaUsuario.btnBuscar.doClick();
				consultaUsuario.show();
				
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(604, 500, 150, 50);
		panelCadastroUsuario.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				consultaUsuario.txtNome.setText("");
				consultaUsuario.txtMatricula.setText("");
				consultaUsuario.btnBuscar.doClick();
				consultaUsuario.show();
			}
		});
	}
}
