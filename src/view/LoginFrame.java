package view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;

import model.bean.Usuario;
import model.dao.UsuarioDAO;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2746438398070897411L;
	private JPanel loginPanel;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setResizable(false);
		setTitle("Ifba Park - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		loginPanel = new JPanel();
		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel labelUsuario = new JLabel("Usu\u00E1rio");
		labelUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelUsuario.setBounds(72, 197, 98, 27);
		loginPanel.add(labelUsuario);
		
		JLabel labelSenha = new JLabel("Senha");
		labelSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelSenha.setBounds(72, 271, 98, 27);
		loginPanel.add(labelSenha);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(72, 235, 300, 25);
		loginPanel.add(txtLogin);
		txtLogin.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(72, 309, 300, 25);
		loginPanel.add(txtSenha);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtLogin.setText("");
				txtSenha.setText("");
			}
		});
		btnLimpar.setBounds(272, 363, 100, 25);
		loginPanel.add(btnLimpar);
		
		JButton btnConfirmar = new JButton("Ok");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = txtLogin.getText();
				String senha = new String(txtSenha.getPassword());
				if(StringUtils.isNotBlank(login) && StringUtils.isNotBlank(senha)) {
					Usuario usuarioLogado = new UsuarioDAO().getUsuarioLogado(login, senha);
					if(usuarioLogado != null && usuarioLogado.getId() != null) {
						System.setProperty("usuarioLogado", usuarioLogado.getNome());
						new PrincipalFrame().setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!", "Atenção", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Os campos Usuário e Senha devem ser preenchidos.", "Atenção!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfirmar.setBounds(162, 363, 100, 25);
		loginPanel.add(btnConfirmar);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setVerticalTextPosition(SwingConstants.TOP);
		lblLogo.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/logo250.jpg")));
		lblLogo.setBounds(10, 11, 424, 168);
		loginPanel.add(lblLogo);
	}
}
