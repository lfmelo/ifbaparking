package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.commons.lang3.StringUtils;

import arduino.util.ArduinoSerial;
import model.dao.MotoristaDAO;
import model.dao.MovimentoDAO;
import model.dao.UsuarioDAO;
import model.dao.VeiculoDAO;

public class PrincipalFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8121312471875993104L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	protected JPanel panelConteudo = new JPanel();
	protected ConsultaUsuario consultaUsuario = new ConsultaUsuario();
	protected ConsultaMotorista consultaMotorista = new ConsultaMotorista();
	protected ConsultaVeiculo consultaVeiculo = new ConsultaVeiculo();
	protected ConsultaMovimento consultaMovimento = new ConsultaMovimento();
	protected JToggleButton btnPrincipal = new JToggleButton("Principal");
	protected TelaPrincipal telaPrincipal;
	protected ArduinoSerial arduino = new ArduinoSerial("COM5");
	protected String etiqueta = "";
	protected Boolean etiquetaLida;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalFrame frame = new PrincipalFrame();
					frame.arduino.initialize();
					frame.setVisible(true);
					
					//ADD BY LUCAS
				
					//ADD BY LUCAS
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void lerEtiqueta() {
		String leitorAux = arduino.read();
		System.out.println(leitorAux);
		if(StringUtils.isBlank(etiqueta) && StringUtils.isNotBlank(leitorAux)) {
			etiqueta = leitorAux;
			//JOptionPane.showMessageDialog(null, "Etiqueta Reconhecida!");
			abrirTelaPrincipal(etiqueta);
		} else if(StringUtils.isNotBlank(leitorAux)){
			if(!leitorAux.equals(etiqueta)) {
				etiqueta = leitorAux;
				//JOptionPane.showMessageDialog(null, "Etiqueta Reconhecida!");
				abrirTelaPrincipal(etiqueta);
			}
		} else if(StringUtils.isBlank(leitorAux)) {
			etiqueta = "";
		}
	}
	
	public void abrirTelaPrincipal(String etiqueta) {
		if(telaPrincipal != null)
			telaPrincipal.dispose();
		telaPrincipal = new TelaPrincipal(etiqueta, this);
		panelConteudo.add(telaPrincipal);
		telaPrincipal.setVisible(true);
		consultaUsuario.dispose();
		if(consultaUsuario.cadastroUsuario != null)
			consultaUsuario.cadastroUsuario.dispose();
		consultaMotorista.dispose();
		if(consultaMotorista.cadastroMotorista != null)
			consultaMotorista.cadastroMotorista.dispose();
		consultaVeiculo.dispose();
		if(consultaVeiculo.cadastroVeiculo != null)
			consultaVeiculo.cadastroVeiculo.dispose();
		consultaMovimento.dispose();
	}
	
	/**
	 * Create the frame.
	 */
	public PrincipalFrame() {
		JLabel txtHora = new JLabel(new SimpleDateFormat("HH:mm:ss").format(new Date()));
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(
          new Runnable() {
              public void run() {
            	  txtHora.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
            	  lerEtiqueta();
              }
        }, 1, 1, TimeUnit.SECONDS);
		
		
		setResizable(false);
		setTitle("Ifba Parking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelConteudo.setLayout(null);
		panelConteudo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelConteudo.setBounds(184, 49, 800, 612);
		contentPane.add(panelConteudo);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(null);
		panelMenu.setBounds(0, 0, 174, 661);
		contentPane.add(panelMenu);
		
		//Tela Principal
		buttonGroup.add(btnPrincipal);
		btnPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPrincipal.setToolTipText("Tela Inicial");
		btnPrincipal.setBounds(10, 132, 154, 50);
		panelMenu.add(btnPrincipal);
		btnPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				abrirTelaPrincipal("");
			}
		});
		
		//Tela Histórico
		JToggleButton btnHistorico = new JToggleButton("Hist\u00F3rico");
		buttonGroup.add(btnHistorico);
		btnHistorico.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnHistorico.setToolTipText("Tela de Hist\u00F3rico");
		btnHistorico.setBounds(10, 193, 154, 50);
		panelMenu.add(btnHistorico);
		btnHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaMovimento.txtDataInicio.setValue(null);
				consultaMovimento.txtDataFim.setValue(null);
				consultaMovimento.txtPlaca.setValue(null);
				consultaMovimento.movimentoTableModel.addRows(new MovimentoDAO().findAll(false));
				panelConteudo.add(consultaMovimento);
				consultaMovimento.setVisible(true);
				
				if(telaPrincipal != null)
					telaPrincipal.dispose();
				consultaUsuario.dispose();
				if(consultaUsuario.cadastroUsuario != null)
					consultaUsuario.cadastroUsuario.dispose();
				consultaMotorista.dispose();
				if(consultaMotorista.cadastroMotorista != null)
					consultaMotorista.cadastroMotorista.dispose();
				consultaVeiculo.dispose();
				if(consultaVeiculo.cadastroVeiculo != null)
					consultaVeiculo.cadastroVeiculo.dispose();
			}
		});
		
		//Tela Veículos
		JToggleButton btnVeiculos = new JToggleButton("Ve\u00EDculos");
		buttonGroup.add(btnVeiculos);
		btnVeiculos.setToolTipText("Cadastro de Ve\u00EDculos");
		btnVeiculos.setAlignmentX(0.5f);
		btnVeiculos.setBounds(10, 254, 154, 50);
		panelMenu.add(btnVeiculos);
		btnVeiculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaVeiculo.txtModelo.setText("");
				consultaVeiculo.txtFabricante.setText("");
				consultaVeiculo.txtPlaca.setText("");
				consultaVeiculo.veiculoTableModel.addRows(new VeiculoDAO().findAll());
				panelConteudo.add(consultaVeiculo);
				consultaVeiculo.setVisible(true);
				if(telaPrincipal != null)
					telaPrincipal.dispose();
				consultaUsuario.dispose();
				if(consultaUsuario.cadastroUsuario != null)
					consultaUsuario.cadastroUsuario.dispose();
				consultaMotorista.dispose();
				if(consultaMotorista.cadastroMotorista != null)
					consultaMotorista.cadastroMotorista.dispose();
				consultaMovimento.dispose();
			}
		});
		
		
		//Tela Motoristas
		JToggleButton btnMotoristas = new JToggleButton("Motoristas");
		buttonGroup.add(btnMotoristas);
		btnMotoristas.setToolTipText("Cadastro de Motoristas");
		btnMotoristas.setAlignmentX(0.5f);
		btnMotoristas.setBounds(10, 315, 154, 50);
		panelMenu.add(btnMotoristas);
		btnMotoristas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaMotorista.txtNome.setText("");
				consultaMotorista.txtHabilitacao.setText("");
				consultaMotorista.motoristaTableModel.addRows(new MotoristaDAO().findAll());
				panelConteudo.add(consultaMotorista);
				consultaMotorista.setVisible(true);
				if(telaPrincipal != null)
					telaPrincipal.dispose();
				consultaUsuario.dispose();
				if(consultaUsuario.cadastroUsuario != null)
					consultaUsuario.cadastroUsuario.dispose();
				consultaVeiculo.dispose();
				if(consultaVeiculo.cadastroVeiculo != null)
					consultaVeiculo.cadastroVeiculo.dispose();
				consultaMovimento.dispose();
			}
		});
		
		//Tela Usuários
		JToggleButton btnUsuarios = new JToggleButton("Usu\u00E1rios");
		buttonGroup.add(btnUsuarios);
		btnUsuarios.setToolTipText("Cadastro de Usu\u00E1rios");
		btnUsuarios.setAlignmentX(0.5f);
		btnUsuarios.setBounds(10, 376, 154, 50);
		panelMenu.add(btnUsuarios);
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaUsuario.txtNome.setText("");
				consultaUsuario.txtMatricula.setText("");
				consultaUsuario.usuarioTableModel.addRows(new UsuarioDAO().findAll());
				panelConteudo.add(consultaUsuario);
				consultaUsuario.setVisible(true);
				if(telaPrincipal != null)
					telaPrincipal.dispose();
				consultaMotorista.dispose();
				if(consultaMotorista.cadastroMotorista != null)
					consultaMotorista.cadastroMotorista.dispose();
				consultaVeiculo.dispose();
				if(consultaVeiculo.cadastroVeiculo != null)
					consultaVeiculo.cadastroVeiculo.dispose();
				consultaMovimento.dispose();
			}
		});
		
		//Sair
		JToggleButton btnSair = new JToggleButton("Sair");
		buttonGroup.add(btnSair);
		btnSair.setToolTipText("Sair");
		btnSair.setAlignmentX(0.5f);
		btnSair.setBounds(10, 437, 154, 50);
		panelMenu.add(btnSair);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 11, 154, 92);
		panelMenu.add(lblLogo);
		lblLogo.setIcon(new ImageIcon(PrincipalFrame.class.getResource("/images/logo150.jpg")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JPanel panelUsuarioData = new JPanel();
		panelUsuarioData.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelUsuarioData.setBounds(184, 11, 800, 30);
		contentPane.add(panelUsuarioData);
		panelUsuarioData.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsurio.setBounds(10, 7, 58, 15);
		panelUsuarioData.add(lblUsurio);
		
		JLabel txtUsuario = new JLabel(System.getProperty("usuarioLogado"));
		txtUsuario.setHorizontalTextPosition(SwingConstants.CENTER);
		txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUsuario.setBounds(63, 7, 568, 15);
		panelUsuarioData.add(txtUsuario);
		
		txtHora.setBounds(732, 7, 58, 15);
		panelUsuarioData.add(txtHora);
		txtHora.setHorizontalTextPosition(SwingConstants.LEFT);
		txtHora.setHorizontalAlignment(SwingConstants.LEFT);
		txtHora.setFont(new Font("Tahoma", Font.PLAIN, 12));

		Date dataAtual = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		JLabel txtData = new JLabel(sdf.format(dataAtual) + " - ");
		txtData.setBounds(641, 7, 92, 15);
		txtData.setHorizontalTextPosition(SwingConstants.RIGHT);
		txtData.setHorizontalAlignment(SwingConstants.RIGHT);
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelUsuarioData.add(txtData);
	}
}
