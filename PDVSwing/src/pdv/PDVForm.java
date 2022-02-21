package pdv;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class PDVForm extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txProduto;
	private JTextField txQtd;
	private JTable carrinhoCompras;
	private DefaultTableModel tabModel = new DefaultTableModel();
	private JTextField txValorTotal;

	private List<ItemVenda>carrinhoItens = new ArrayList<ItemVenda>();
	private Venda venda = new Venda();
	private Produto produtoSelecionado;
	
	
	public void selecionarProduto(Produto produto) {
		produtoSelecionado = produto;
		txProduto.setText(produtoSelecionado.getNome());
	}
	
	public void configTableCarrinho() {
		carrinhoCompras.setModel(tabModel);
		tabModel.addColumn("Produto");
		tabModel.addColumn("Qtd.");
		tabModel.addColumn("Preco");
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PDVForm frame = new PDVForm();
					frame.setVisible(true);
					frame.configTableCarrinho();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PDVForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(12, 15, 80, 15);
		contentPane.add(lblProduto);
		
		txProduto = new JTextField();
		txProduto.setBounds(89, 8, 386, 29);
		contentPane.add(txProduto);
		txProduto.setColumns(10);
		
		JButton btShowDlgProdutos = new JButton("Selecionar Produto");
		btShowDlgProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDlgProdutos();
			}
		});
		btShowDlgProdutos.setBounds(487, 8, 173, 29);
		contentPane.add(btShowDlgProdutos);
		
		JLabel lblQtd = new JLabel("Qtd.");
		lblQtd.setBounds(22, 49, 70, 15);
		contentPane.add(lblQtd);
		
		txQtd = new JTextField();
		txQtd.setBounds(89, 45, 135, 29);
		contentPane.add(txQtd);
		txQtd.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar Produto");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarProduto();
			}
		});
		btnAdicionar.setBounds(236, 44, 239, 30);
		contentPane.add(btnAdicionar);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 86, 648, 240);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		carrinhoCompras = new JTable();
		scrollPane.setViewportView(carrinhoCompras);
		
		JButton btnRegistrarVenda = new JButton("Registrar Venda");
		btnRegistrarVenda.setBounds(492, 375, 168, 37);
		contentPane.add(btnRegistrarVenda);
		
		JButton btnListarVendas = new JButton("Listar Vendas");
		btnListarVendas.setBounds(299, 375, 185, 37);
		contentPane.add(btnListarVendas);
		
		txValorTotal = new JTextField();
		txValorTotal.setBounds(506, 336, 154, 29);
		contentPane.add(txValorTotal);
		txValorTotal.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Valor Total");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(396, 344, 102, 13);
		contentPane.add(lblNewLabel);
	}

	protected void adicionarProduto() {
		Vector<String>row = new Vector<String>();
		
		row.add(produtoSelecionado.getNome());
		row.add(txQtd.getText());
		row.add(produtoSelecionado.getPreco().toString());
		tabModel.addRow(row);
		
		ItemVenda item = new ItemVenda();
		item.setProduto(produtoSelecionado);
		item.setQuantidade(Integer.valueOf(txQtd.getText()));
		carrinhoItens.add(item);
		venda.getCarrinho().add(item);
		txValorTotal.setText(venda.getValorTotal().toString());
	}

	protected void showDlgProdutos() {
		ProdutosDlg dialog = new ProdutosDlg(this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		dialog.configTableProdutos();
	}
}


