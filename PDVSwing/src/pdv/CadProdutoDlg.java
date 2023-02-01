package pdv;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import framework.presentation.swing.Window;
import pdv.domain.Produto;
import persistence.ProdutosRepo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadProdutoDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel tabModel = new DefaultTableModel();
	private JTable tabProdutos;
	private JTextPane txNome;
	
	private ProdutosRepo produtosRepo = new ProdutosRepo();
	
	List<Produto> produtos;
	

	
	void pesquisar() {
		String nome = txNome.getText();
		
		produtos = produtosRepo.findProduto(nome);
		
		populateTable();
	}

	void showDlgProd(boolean update) {
		IncluirProdutoDlg dialog = new IncluirProdutoDlg(update);
		Window.centralizeWindow(dialog);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		if (update) {
			int seletedRow  = tabProdutos.getSelectedRow();		
			Produto produto = produtos.get(seletedRow);
			dialog.dadosProduto(produto);	
		}
		dialog.setVisible(true);
		
		listProdutos();
	}
	
	void listProdutos() {
		try {
			produtos = produtosRepo.getProdutos();
			
			populateTable();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void populateTable() {
		tabModel.setRowCount(0);
		
		for (Produto produto : produtos) {
			tabModel.addRow(
				new Object[] {
					produto.getCodigo(),	
					produto.getNome(),
					produto.getPreco()
				}
			);
		}
	}	
	
	public CadProdutoDlg(boolean defaultConfig) {
		this();
		
		if (defaultConfig) {
			tabProdutos.setModel(tabModel);
			tabModel.addColumn("C\u00F3digo");
			tabModel.addColumn("Produto");
			tabModel.addColumn("Pre\u00E7o");
			
			listProdutos();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public CadProdutoDlg() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 845, 447);
		getContentPane().setLayout(null);
		
		JButton btCadProduto = new JButton("Cadastrar Produto");
		btCadProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDlgProd(false);
			}
		});
		btCadProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btCadProduto.setBounds(10, 11, 182, 36);
		getContentPane().add(btCadProduto);
		
		JButton btnAlterarDadosProduto = new JButton("Alterar Dados do Produto");
		btnAlterarDadosProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDlgProd(true);
			}
		});
		btnAlterarDadosProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAlterarDadosProduto.setBounds(202, 11, 195, 36);
		getContentPane().add(btnAlterarDadosProduto);
		
		txNome = new JTextPane();
		txNome.setBounds(407, 11, 301, 36);
		getContentPane().add(txNome);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisar.setBounds(714, 11, 115, 36);
		getContentPane().add(btnPesquisar);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 58, 819, 349);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tabProdutos = new JTable();
		scrollPane.setViewportView(tabProdutos);

	}

}
