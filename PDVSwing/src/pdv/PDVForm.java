package pdv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.apache.commons.lang3.StringUtils;

import framework.presentation.swing.Window;
import framework.util.FormatNumberUtil;
import framework.util.NumberUtil;
import pdv.domain.FormaPagamento;
import pdv.domain.ItemVenda;
import pdv.domain.Produto;
import pdv.domain.Venda;
import persistence.FormaRepo;
import persistence.ProdutosRepo;
import persistence.VendasRepo;

public class PDVForm extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txProduto;
	private JTextField txQtd;
	private JTable carrinhoCompras;
	private DefaultTableModel tabModel = new DefaultTableModel();
	private JTextField txValorTotal;
	private JLabel descProduto;
	private JComboBox cbForma;
	
	private Produto produtoSelecionado;
	
	private Venda venda = new Venda();

	private ProdutosRepo produtosRepo = new ProdutosRepo();
	private VendasRepo vendasRepo = new VendasRepo();
	private JTextField txPreco;
	

	
	protected void adicionarAvulso() {
		Produto avulso = new Produto();
		avulso.setNome(
			!StringUtils.isBlank(txProduto.getText()) ?
			txProduto.getText() : 		
			ItemVenda.DES_ITEM_AVULSO
		);
		avulso.setPreco(
			Double.valueOf(	
				NumberUtil.removeFormat(
					txPreco.getText()
				)
			)
		);
		produtoSelecionado = avulso;
		
		adicionarProduto();
	}

	protected void registrarVenda() {
		venda.setDataVenda(new Date());
	
		if (cbForma.getSelectedIndex() > 0) {
			String forma 			= cbForma.getSelectedItem().toString();
			FormaPagamento formaPg	= new FormaRepo().findByForma(forma);
			venda.setFormaPagto(formaPg);
		} 		
		vendasRepo.persistVenda(venda);
		
		JOptionPane.showMessageDialog(this,"Venda Registrada com Sucesso");

		cbForma.setSelectedIndex(0);
		tabModel.setRowCount(0);
		txValorTotal.setText("");
		venda = new Venda();
	}
	
	public void onKeyPressTxProduto() {
		String prod = txProduto.getText();
		
		Produto produto = produtosRepo.findProdutoByNomeOuCodigo(prod);
		
		if (produto != null) {
			descProduto.setText(produto.getNome());
			txPreco.setText(
				FormatNumberUtil.format(
					produto.getPreco(), 
					FormatNumberUtil.DUAS_CASAS_DECIMAIS
				)	
			);
			produtoSelecionado = produto;
		} else {
			descProduto.setText("");
			produtoSelecionado = null;
		}
	}

	public void adicionarProduto() {
		Vector<String>row = new Vector<String>();
		
		ItemVenda item = new ItemVenda();
		item.copy(produtoSelecionado);
		item.setQuantidade(Integer.valueOf(txQtd.getText()));
		venda.getCarrinho().add(item);
		
		txValorTotal.setText(
			FormatNumberUtil.format(
				venda.getTotalVendas(), 
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
		
		resetFields();
	}

	private void resetFields() {
		this.produtoSelecionado = null;
		
		descProduto.setText("");
		txProduto.setText("");
		txPreco.setText("");
		txQtd.setText("1");
	}
	
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
		
		postConstruct();
	}

	private void postConstruct() {
		populateFormasPagto();
		txQtd.setText("1");
	}
	
	private void populateFormasPagto() {
		cbForma.setModel(new DefaultComboBoxModel());
		((DefaultComboBoxModel)(cbForma.getModel()))
			.addElement("");
		
		List<FormaPagamento>formas = new FormaRepo().getAll();

		for (FormaPagamento forma: formas) {
			((DefaultComboBoxModel)(cbForma.getModel()))
				.addElement(
					forma.getForma()
				);
		}
	}
	
	/**
	 * Create the frame.
	 */
	public PDVForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 863, 503);
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
		txProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				onKeyPressTxProduto();
			}
		});
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
		lblQtd.setBounds(12, 102, 70, 15);
		contentPane.add(lblQtd);
		
		txQtd = new JTextField();
		txQtd.setBounds(89, 95, 91, 29);
		contentPane.add(txQtd);
		txQtd.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar Produto");
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarProduto();
			}
		});
		btnAdicionar.setBounds(378, 94, 239, 30);
		contentPane.add(btnAdicionar);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 135, 835, 240);
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
		btnRegistrarVenda.setBounds(679, 426, 168, 37);
		contentPane.add(btnRegistrarVenda);
		
		JButton btnListarVendas = new JButton("Listar Vendas");
		btnListarVendas.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnListarVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDlgVendas();
			}
		});
		btnListarVendas.setBounds(479, 426, 185, 37);
		contentPane.add(btnListarVendas);
		
		txValorTotal = new JTextField();
		txValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txValorTotal.setBounds(679, 386, 168, 29);
		contentPane.add(txValorTotal);
		txValorTotal.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Valor Total");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(565, 394, 102, 13);
		contentPane.add(lblNewLabel);
		
		descProduto = new JLabel("");
		descProduto.setBorder(new LineBorder(new Color(0, 0, 0)));
		descProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		descProduto.setBounds(89, 48, 758, 36);
		contentPane.add(descProduto);
		
		JButton btnAdicionarProdutoAvulso = new JButton("Adicionar Produto Avulso");
		btnAdicionarProdutoAvulso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarAvulso();
			}
		});
		btnAdicionarProdutoAvulso.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdicionarProdutoAvulso.setBounds(627, 95, 220, 29);
		contentPane.add(btnAdicionarProdutoAvulso);
		
		txPreco = new JTextField();
		txPreco.setBounds(252, 95, 116, 29);
		contentPane.add(txPreco);
		txPreco.setColumns(10);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o");
		lblPreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPreo.setBounds(190, 102, 55, 14);
		contentPane.add(lblPreo);
		
		JLabel lblFormaDePagamento = new JLabel("Forma de Pagamento");
		lblFormaDePagamento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFormaDePagamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaDePagamento.setBounds(12, 393, 168, 15);
		contentPane.add(lblFormaDePagamento);
		
		cbForma = new JComboBox();
		cbForma.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbForma.setBounds(190, 386, 220, 29);
		contentPane.add(cbForma);
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




