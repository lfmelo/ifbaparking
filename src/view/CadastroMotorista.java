package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;

import javax.swing.JButton;
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
import model.dao.MotoristaDAO;

public class CadastroMotorista extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3172836835956792620L;
	private JTextField txtNome;
	private JFormattedTextField txtFone;
	private JTextField txtHabNum;
	private JTextField txtHabCat;
	private JTextField txtID;

	/**
	 * Create the frame.
	 */
	public CadastroMotorista(Motorista motoristaCadastro, ConsultaMotorista consultaMotorista) {
		((BasicInternalFrameUI)getUI()).setNorthPane(null);
		setResizable(false);
		setBounds(0, 0, 800, 612);
		getContentPane().setLayout(null);
		
		JPanel panelCadastroMotorista = new JPanel();
		panelCadastroMotorista.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelCadastroMotorista.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCadastroMotorista.setName("");
		panelCadastroMotorista.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastrar Motorista", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCadastroMotorista.setVisible(true);
		panelCadastroMotorista.setAutoscrolls(false);
		panelCadastroMotorista.setBounds(10, 11, 764, 561);
		getContentPane().add(panelCadastroMotorista);
		panelCadastroMotorista.setLayout(null);
		
		txtID = new JTextField();
		txtID.setVisible(false);
		txtID.setBounds(31, 43, 86, 20);
		panelCadastroMotorista.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(31, 100, 67, 14);
		panelCadastroMotorista.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setBounds(128, 95, 626, 25);
		panelCadastroMotorista.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblHabNum = new JLabel("Habilitação:");
		lblHabNum.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHabNum.setBounds(31, 160, 95, 20);
		panelCadastroMotorista.add(lblHabNum);
		
		txtHabNum = new JTextField();
		txtHabNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHabNum.setBounds(128, 155, 412, 25);
		panelCadastroMotorista.add(txtHabNum);
		txtHabNum.setColumns(10);
		
		JLabel lblHabCat = new JLabel("Categoria:");
		lblHabCat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHabCat.setBounds(563, 155, 95, 25);
		panelCadastroMotorista.add(lblHabCat);
		
		txtHabCat = new JTextField();
		txtHabCat.setHorizontalAlignment(SwingConstants.CENTER);
		txtHabCat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHabCat.setBounds(668, 155, 86, 25);
		panelCadastroMotorista.add(txtHabCat);
		txtHabCat.setColumns(10);
		
		JLabel lblFone = new JLabel("Fone:");
		lblFone.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFone.setBounds(31, 220, 67, 14);
		panelCadastroMotorista.add(lblFone);

		try {
			txtFone = new JFormattedTextField(new MaskFormatter("(##) *####-####"));
			txtFone.setColumns(10);
			txtFone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtFone.setBounds(128, 215, 199, 25);
		panelCadastroMotorista.add(txtFone);
		
		if(motoristaCadastro != null && motoristaCadastro.getId() != null) {
			txtID.setText(motoristaCadastro.getId().toString());
			txtNome.setText(motoristaCadastro.getNome());
			txtHabNum.setText(motoristaCadastro.getHab_num());
			txtHabCat.setText(motoristaCadastro.getHab_cat());
			txtFone.setText(motoristaCadastro.getFone());
		}
		
		JButton btnSalvarMotorista = new JButton("Gravar");
		btnSalvarMotorista.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalvarMotorista.setBounds(444, 500, 150, 50);
		panelCadastroMotorista.add(btnSalvarMotorista);
		btnSalvarMotorista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer idMotorista = StringUtils.isNotBlank(txtID.getText()) ? Integer.parseInt(txtID.getText()) : null; 
				Motorista motorista = new Motorista(idMotorista, txtNome.getText(), txtFone.getText(), txtHabNum.getText(), txtHabCat.getText(),
													new Timestamp(System.currentTimeMillis()));
				MotoristaDAO motoristaDAO = new MotoristaDAO();
				motorista = motoristaDAO.saveOrUpdate(motorista);
				if(motorista != null && motorista.getId() != null) {
					JOptionPane.showMessageDialog(null, "Motorista " + motorista.getNome() + " salvo com sucesso.");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao salvar motorista.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
				consultaMotorista.txtNome.setText("");
				consultaMotorista.txtHabilitacao.setText("");
				consultaMotorista.btnBuscar.doClick();
				consultaMotorista.show();
				
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(604, 500, 150, 50);
		panelCadastroMotorista.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				consultaMotorista.txtNome.setText("");
				consultaMotorista.txtHabilitacao.setText("");
				consultaMotorista.btnBuscar.doClick();
				consultaMotorista.show();
			}
		});
	}
}
