package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Cliente;
import persistence.ClienteDao;

public class ClienteController implements ActionListener {

	private JTextField tfNome;
	private JTextField tfTelefone;
	
	public ClienteController(JTextField tfNome, JTextField tfTelefone) {
		this.tfNome = tfNome;
		this.tfTelefone = tfTelefone;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println(e.getActionCommand());
		if(e.getActionCommand().toString().equals("Cadastrar")) {
			try {
				cadastraCliente();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		} 
	}

	private void cadastraCliente() throws ClassNotFoundException, SQLException {
		if (!tfNome.getText().isEmpty() && !tfTelefone.getText().isEmpty()) {
			Cliente cli = new Cliente();
			cli.setNome(tfNome.getText());
			cli.setTelefone(tfTelefone.getText());
			
			ClienteDao cDao = new ClienteDao();
			String retorno = cDao.cadastraCliente(cli);
			
			JOptionPane.showMessageDialog(null, retorno, "ERRO", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Preencha os campos!", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

}
