package pdv;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class ProdutosDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable tabProdutos;
	private DefaultTableModel tabModel = new DefaultTableModel();
	private PDVForm pdv;

	
	public ProdutosDlg(PDVForm pdv) {
		this();
		
		this.pdv = pdv;
	}
	
	public void configTableProdutos() {
		tabProdutos.setModel(tabModel);
		
		tabModel.addColumn("Produto");
		tabModel.addColumn("Preco");
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutosDlg dialog = new ProdutosDlg();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.configTableProdutos();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public ProdutosDlg() {
		setBounds(100, 100, 549, 374);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(8, 10, 517, 253);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tabProdutos = new JTable();
		scrollPane.setViewportView(tabProdutos);
		
		JButton btnSelecionar = new JButton("Selecionar Produto");
		btnSelecionar.setBounds(345, 277, 180, 30);
		getContentPane().add(btnSelecionar);
		
		JButton btnNovoProduto = new JButton("Novo Produto");
		btnNovoProduto.setBounds(8, 277, 156, 30);
		getContentPane().add(btnNovoProduto);
		
		JButton btnListarProdutos = new JButton("Listar Produtos");
		btnListarProdutos.setBounds(173, 277, 164, 30);
		getContentPane().add(btnListarProdutos);

	}
}
