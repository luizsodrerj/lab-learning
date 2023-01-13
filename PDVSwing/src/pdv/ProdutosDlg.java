package pdv;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import framework.presentation.swing.Window;
import persistence.ProdutosRepo;
import java.awt.Font;

public class ProdutosDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	
	
	private ProdutosRepo produtosRepo = new ProdutosRepo();
	
	private JTable tabProdutos;
	private DefaultTableModel tabModel = new DefaultTableModel();
	private PDVForm pdv;

	
	public ProdutosDlg(PDVForm pdv) {
		this();
		configTableProdutos();
		setModal(true);
		
		this.pdv = pdv;
	}
	
	public void configTableProdutos() {
		tabProdutos.setModel(tabModel);
		tabModel.addColumn("Id");
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
		btnSelecionar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSelecionar.setBounds(345, 277, 180, 30);
		getContentPane().add(btnSelecionar);
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionarProduto();
			}
		});
		
		JButton btnNovoProduto = new JButton("Novo Produto");
		btnNovoProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNovoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showIncluirProdutoDlg();
			}
		});
		btnNovoProduto.setBounds(8, 277, 156, 30);
		getContentPane().add(btnNovoProduto);
		
		JButton btnListarProdutos = new JButton("Listar Produtos");
		btnListarProdutos.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnListarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarProdutos();
			}
		});
		btnListarProdutos.setBounds(173, 277, 164, 30);
		getContentPane().add(btnListarProdutos);
	}

	protected void selectionarProduto() {
		int row = tabProdutos.getSelectedRow();
		String nomeProd = tabProdutos.getValueAt(row, 1).toString();
		Double preco = Double.valueOf(tabProdutos.getValueAt(row, 2).toString());
		Integer id = Integer.valueOf(tabProdutos.getValueAt(row, 0).toString());
		
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome(nomeProd);
		produto.setPreco(preco);
		pdv.selecionarProduto(produto);
		dispose();
	}

	protected void listarProdutos() {
		try {
			List<Produto>list = produtosRepo.getProdutos();
			
			tabModel.setRowCount(0);
			
			for (Produto produto : list) {
				tabModel.addRow(
					new Object[] {
						produto.getId(),	
						produto.getNome(),
						produto.getPreco()
					}
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void showIncluirProdutoDlg() {
		IncluirProdutoDlg dialog = new IncluirProdutoDlg();
		Window.centralizeWindow(dialog);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	

	
}



