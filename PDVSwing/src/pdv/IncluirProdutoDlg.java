package pdv;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IncluirProdutoDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txNomeProduto;
	private JTextField txPreco;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IncluirProdutoDlg dialog = new IncluirProdutoDlg();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public IncluirProdutoDlg() {
		setBounds(100, 100, 542, 196);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome do Produto");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(8, 13, 155, 13);
		getContentPane().add(lblNewLabel);
		
		txNomeProduto = new JTextField();
		txNomeProduto.setBounds(171, 7, 347, 26);
		getContentPane().add(txNomeProduto);
		txNomeProduto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pre\u00E7o");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(84, 49, 79, 13);
		getContentPane().add(lblNewLabel_1);
		
		txPreco = new JTextField();
		txPreco.setBounds(171, 43, 138, 26);
		getContentPane().add(txPreco);
		txPreco.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirProduto();
			}
		});
		btnSalvar.setBounds(172, 79, 137, 26);
		getContentPane().add(btnSalvar);

	}

	protected void incluirProduto() {
		EntityManager em = null;
		
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();

			Produto produto = new Produto();
			produto.setNome(txNomeProduto.getText());
			produto.setPreco(Double.valueOf(txPreco.getText()));
			em.persist(produto);
			
			em.getTransaction().commit();

			txNomeProduto.setText("");
			txPreco.setText("");
			
			JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!");
			
		} finally {
			em.close();
		}
	}
}




