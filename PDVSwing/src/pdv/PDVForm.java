package pdv;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import framework.presentation.swing.Window;
import framework.util.FormatNumberUtil;
import persistence.VendasRepo;

public class PDVForm extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txProduto;
	private JTextField txQtd;
	private JTable carrinhoCompras;
	private DefaultTableModel tabModel = new DefaultTableModel();
	private JTextField txValorTotal;

	private Produto produtoSelecionado;
	
	private Venda venda = new Venda();

	private VendasRepo vendasRepo = new VendasRepo();
	
	
	public void selecionarProduto(Produto produto) {
		produtoSelecionado = produto;
		txProduto.setText(produtoSelecionado.getNome());
	}
	
	public void configTableCarrinho() {
		carrinhoCompras.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		carrinhoCompras.setModel(tabModel);

		tabModel.addColumn("Produto");
		tabModel.addColumn("Qtd.");
		tabModel.addColumn("Pre\u00E7o");
		tabModel.addColumn("Valor Total");
		
		TableColumnModel columnModel = carrinhoCompras.getColumnModel();
		
		columnModel.getColumn(0).setPreferredWidth(490);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(110);
		columnModel.getColumn(3).setPreferredWidth(150);
	}

	public void adicionarProduto() {
		Vector<String>row = new Vector<String>();
		
		ItemVenda item = new ItemVenda();
		item.copy(produtoSelecionado);
		item.setQuantidade(Integer.valueOf(txQtd.getText()));
		venda.getCarrinho().add(item);
		
		txValorTotal.setText(
			FormatNumberUtil.format(
				venda.getValorTotal(), 
				FormatNumberUtil.DUAS_CASAS_DECIMAIS
			)
		);
		row.add(produtoSelecionado.getNome());
		row.add(txQtd.getText());
		row.add(
			FormatNumberUtil.format(
				produtoSelecionado.getPreco(), 
				FormatNumberUtil.DUAS_CASAS_DECIMAIS
			)
		);
		row.add(
			FormatNumberUtil.format(
				item.getValorTotal(), 
				FormatNumberUtil.DUAS_CASAS_DECIMAIS
			)
		);
		tabModel.addRow(row);
		
		txProduto.setText("");
		txQtd.setText("");
	}
	
	/**
	 * Create the frame.
	 */
	public PDVForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 863, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProduto.setBounds(12, 15, 70, 15);
		contentPane.add(lblProduto);
		
		txProduto = new JTextField();
		txProduto.setBounds(89, 8, 575, 29);
		contentPane.add(txProduto);
		txProduto.setColumns(10);
		
		JButton btShowDlgProdutos = new JButton("Selecionar Produto");
		btShowDlgProdutos.setFont(new Font("Tahoma", Font.BOLD, 11));
		btShowDlgProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDlgProdutos();
			}
		});
		btShowDlgProdutos.setBounds(674, 8, 173, 29);
		contentPane.add(btShowDlgProdutos);
		
		JLabel lblQtd = new JLabel("Qtd.");
		lblQtd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQtd.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQtd.setBounds(12, 52, 70, 15);
		contentPane.add(lblQtd);
		
		txQtd = new JTextField();
		txQtd.setBounds(89, 45, 135, 29);
		contentPane.add(txQtd);
		txQtd.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar Produto");
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarProduto();
			}
		});
		btnAdicionar.setBounds(236, 44, 239, 30);
		contentPane.add(btnAdicionar);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 86, 835, 240);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		carrinhoCompras = new JTable();
		scrollPane.setViewportView(carrinhoCompras);
		
		JButton btnRegistrarVenda = new JButton("Registrar Venda");
		btnRegistrarVenda.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarVenda();
			}
		});
		btnRegistrarVenda.setBounds(679, 377, 168, 37);
		contentPane.add(btnRegistrarVenda);
		
		JButton btnListarVendas = new JButton("Listar Vendas");
		btnListarVendas.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnListarVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDlgVendas();
			}
		});
		btnListarVendas.setBounds(486, 377, 185, 37);
		contentPane.add(btnListarVendas);
		
		txValorTotal = new JTextField();
		txValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txValorTotal.setBounds(693, 337, 154, 29);
		contentPane.add(txValorTotal);
		txValorTotal.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Valor Total");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(583, 345, 102, 13);
		contentPane.add(lblNewLabel);
	}

	protected void registrarVenda() {
			venda.setDataVenda(new Date());

			vendasRepo.persistVenda(venda);
			
			JOptionPane.showMessageDialog(this,"Venda Registrada com Sucesso");
			
			tabModel.setRowCount(0);
			txValorTotal.setText("");
			venda = new Venda();
	}

	protected void showDlgVendas() {
		VendasDlg vendas = new VendasDlg(true);
		vendas.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		vendas.setVisible(true);
	}

	protected void showDlgProdutos() {
		ProdutosDlg dialog = new ProdutosDlg(this);
		Window.centralizeWindow(dialog);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
}




