package pdv;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import pdv.domain.Produto;
import persistence.ProdutosRepo;

public class IncluirProdutoDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JTextField txNomeProduto;
	private JTextField txPreco;
	private JTextField txCodigo;

	private ProdutosRepo produtosRepo = new ProdutosRepo();

	private Produto produto;

	private boolean update = false;
	

	protected void incluirProduto() {
		if (update) {
			this.produto.setPreco(Double.valueOf(txPreco.getText()));
			this.produto.setNome(txNomeProduto.getText());
			this.produto.setCodigo(txCodigo.getText());

			produtosRepo.updateProduto(this.produto);
		} else {
			Produto produto = new Produto();
			produto.setCodigo(txCodigo.getText());
			produto.setNome(txNomeProduto.getText());
			produto.setPreco(Double.valueOf(txPreco.getText()));

			produtosRepo.insertProduto(produto);
		}
		txNomeProduto.setText("");
		txCodigo.setText("");
		txPreco.setText("");
		
		JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!");
	}
	
	public void dadosProduto(Produto produto) {
		txCodigo.setText(produto.getCodigo());
		txNomeProduto.setText(produto.getNome());
		txPreco.setText(produto.getPreco().toString());
		
		this.produto = produto;
	}
	
	public IncluirProdutoDlg(boolean update) {
		this();
		
		this.update = update;
	}
	
	/**
	 * Create the dialog.
	 */
	public IncluirProdutoDlg() {
		setModal(true);
		setBounds(100, 100, 675, 254);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome do Produto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(8, 13, 155, 13);
		getContentPane().add(lblNewLabel);
		
		txNomeProduto = new JTextField();
		txNomeProduto.setBounds(171, 7, 478, 26);
		getContentPane().add(txNomeProduto);
		txNomeProduto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pre\u00E7o");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(84, 88, 79, 13);
		getContentPane().add(lblNewLabel_1);
		
		txPreco = new JTextField();
		txPreco.setBounds(171, 81, 351, 26);
		getContentPane().add(txPreco);
		txPreco.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirProduto();
			}
		});
		btnSalvar.setBounds(171, 118, 137, 36);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IncluirProdutoDlg.this.dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setBounds(318, 118, 130, 36);
		getContentPane().add(btnCancelar);
		
		JLabel lblCdigoDoProduto = new JLabel("C\u00F3digo do Produto");
		lblCdigoDoProduto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdigoDoProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCdigoDoProduto.setBounds(8, 51, 155, 13);
		getContentPane().add(lblCdigoDoProduto);
		
		txCodigo = new JTextField();
		txCodigo.setBounds(171, 44, 351, 26);
		getContentPane().add(txCodigo);
		txCodigo.setColumns(10);

	}
}




