package pdv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import framework.presentation.swing.Calendar;
import framework.presentation.swing.Window;
import framework.util.DateUtil;
import framework.util.FormatNumberUtil;
import persistence.VendasRepo;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class VendasDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel carrinhoModel = new DefaultTableModel();
	private DefaultTableModel vendasModel = new DefaultTableModel();
	
	private final JPanel panel_1 = new JPanel();
	private JTextField txValorTotal;
	private JTable tabCarrinho;
	private JTable tabVendas;
	private JComboBox mes;
	private JComboBox ano;
	
	private VendasRepo repository = new VendasRepo();
	private JTextField txDtIni;
	private JTextField txDtFim;

	private JButton btCalIni;
	private JButton btCalFim;
	private JButton btnPesquisar;
	private JLabel lblPesquisaPorMsano;
	
	
	public VendasDlg(boolean modal) {
		this();
		init();
		
		configTabVendas();
		confTabCarrinho();
		preencherVendas();
		
		setModal(modal);
	}

	public void pesquisar() {
		try {
			List<Venda>vendas = null;
			
			if (this.mes.getSelectedIndex() > 0 &&
				this.ano.getSelectedIndex() > 0) {
				int mes = this.mes.getSelectedIndex();
				int ano = Integer.parseInt(
						    this.ano.getModel().getElementAt(
						      this.ano.getSelectedIndex()
						    ).toString()
						  );
				vendas = repository.findByMesAno(mes, ano);
			} else {
				Date ini = DateUtil.parse(txDtIni.getText(), DateUtil.dd_MM_yyyy);
				Date fim = DateUtil.parse(txDtFim.getText(), DateUtil.dd_MM_yyyy);
				vendas = repository.findByPeriodo(ini, fim);
			}
			populateVendas(vendas);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	private void init() {
		addBtListener(btCalIni, txDtIni);
		addBtListener(btCalFim, txDtFim);
		
		initCombos();
	}

	private void initCombos() {
		DefaultComboBoxModel cbMes = new DefaultComboBoxModel();
		DefaultComboBoxModel cbAno = new DefaultComboBoxModel();
		this.mes.setModel(cbMes);
		this.ano.setModel(cbAno);
		
		cbAno.addElement("");
		cbMes.addElement("");
		cbMes.addElement("Janeiro");
		cbMes.addElement("Fevereiro");
		cbMes.addElement("Mar\u00E7o");
		cbMes.addElement("Abril");
		cbMes.addElement("Maio");
        cbMes.addElement("Junho");
        cbMes.addElement("Julho");
        cbMes.addElement("Agosto");
        cbMes.addElement("Setembro");
        cbMes.addElement("Outubro");
        cbMes.addElement("Novembro");
        cbMes.addElement("Dezembro");
		
        int anoAtual = new DateUtil().getYear();
        int anoMin 	 = 1980;
        
        while (anoAtual >= anoMin) {
			cbAno.addElement(String.valueOf(anoAtual));
			anoAtual--;
		}
	}
	
	private void addBtListener(JButton bt, JTextField txt) {
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar cal = new Calendar(txt);
				cal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				cal.populateDaysOfMonth();
				Window.centralizeWindow(cal);
				cal.setModal(true);
				cal.setVisible(true);
			}
		});
	}
	
	private void confTabCarrinho() {
		tabCarrinho.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabCarrinho.setModel(carrinhoModel);
		carrinhoModel.addColumn("Produto");
		carrinhoModel.addColumn("Qtd.");
		carrinhoModel.addColumn("Valor Total");
		
		TableColumnModel columnModel = tabCarrinho.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(470);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(220);
	}

	private void configTabVendas() {
		tabVendas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabVendas.setModel(vendasModel);
		tabVendas.getSelectionModel().addListSelectionListener(
			new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					vendasRowSelection();
				}
			}	
		);
		vendasModel.addColumn("Id");
		vendasModel.addColumn("Data");
		
		TableColumnModel columnModel = tabVendas.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(110);
		columnModel.getColumn(1).setPreferredWidth(315);
	}
	
	protected void vendasRowSelection() {
		int row = tabVendas.getSelectedRow();
		
		if (row < 0) {
			return;
		}
		Integer id = Integer.valueOf(tabVendas.getValueAt(row, 0).toString());

		Venda venda = repository.getVenda(id);
		List<ItemVenda>carrinho = venda.getCarrinhoCompras();

		carrinhoModel.setRowCount(0); 

		for (ItemVenda item: carrinho) {
			Vector<String>dados = new Vector<String>();
			dados.add(
				item.getNome() != null ?
				item.getNome() :
				item.getProduto().getNome()	
			);
			dados.add(item.getQuantidade().toString());
			dados.add(
				FormatNumberUtil.format(
					item.getValorTotal(), 
					FormatNumberUtil.DUAS_CASAS_DECIMAIS
				)
			);
			carrinhoModel.addRow(dados);
		}
		txValorTotal.setText(
			FormatNumberUtil.format(
				venda.getValorTotal(), 
				FormatNumberUtil.DUAS_CASAS_DECIMAIS
			)
		);
	}

	private void preencherVendas() {
		List<Venda> vendas = repository.getVendas(); 
		
		populateVendas(vendas);
	}

	private void populateVendas(List<Venda> vendas) {
		carrinhoModel.setRowCount(0);
		vendasModel.setRowCount(0);
		
		for (Venda venda : vendas) {
			Vector<String>dados = new Vector<String>();
			dados.add(venda.getId().toString());
			dados.add(
			  new SimpleDateFormat("dd/MM/yyyy")
			      .format(venda.getDataVenda())
			);
			vendasModel.addRow(dados);
		}
	}

	/**
	 * Create the dialog.
	 */
	public VendasDlg() {
		setBounds(100, 100, 820, 652);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "VENDAS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 145, 782, 165);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tabVendas = new JTable();
		scrollPane.setViewportView(tabVendas);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CARRINHO DE COMPRAS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(12, 321, 782, 249);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		tabCarrinho = new JTable();
		scrollPane_1.setViewportView(tabCarrinho);
		
		JLabel lblValorTotal = new JLabel("Valor Total");
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorTotal.setBounds(465, 581, 167, 15);
		getContentPane().add(lblValorTotal);
		
		txValorTotal = new JTextField();
		txValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txValorTotal.setBounds(642, 574, 152, 28);
		getContentPane().add(txValorTotal);
		txValorTotal.setColumns(10);
		
		JLabel lblDataInicial = new JLabel("Data Inicial:");
		lblDataInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataInicial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataInicial.setBounds(12, 20, 82, 22);
		getContentPane().add(lblDataInicial);
		
		JLabel lblDataFinal = new JLabel("Data Final:");
		lblDataFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFinal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataFinal.setBounds(334, 20, 74, 22);
		getContentPane().add(lblDataFinal);
		
		txDtIni = new JTextField();
		txDtIni.setFont(new Font("Tahoma", Font.BOLD, 11));
		txDtIni.setBounds(101, 12, 133, 39);
		getContentPane().add(txDtIni);
		txDtIni.setColumns(10);
		
		txDtFim = new JTextField();
		txDtFim.setFont(new Font("Tahoma", Font.BOLD, 11));
		txDtFim.setColumns(10);
		txDtFim.setBounds(416, 12, 133, 39);
		getContentPane().add(txDtFim);
		
		btCalIni = new JButton("");
		btCalIni.setIcon(new ImageIcon(VendasDlg.class.getResource("/resources/img/calendar.png")));
		btCalIni.setBounds(238, 11, 88, 86);
		getContentPane().add(btCalIni);
		
		btCalFim = new JButton("");
		btCalFim.setIcon(new ImageIcon(VendasDlg.class.getResource("/resources/img/calendar.png")));
		btCalFim.setBounds(554, 11, 88, 86);
		getContentPane().add(btCalFim);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisar.setBounds(669, 12, 125, 39);
		getContentPane().add(btnPesquisar);
		
		lblPesquisaPorMsano = new JLabel("Pesquisa por M\u00EAs/Ano");
		lblPesquisaPorMsano.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPesquisaPorMsano.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPesquisaPorMsano.setBounds(12, 114, 141, 14);
		getContentPane().add(lblPesquisaPorMsano);
		
		mes = new JComboBox();
		mes.setFont(new Font("Tahoma", Font.BOLD, 11));
		mes.setBounds(161, 108, 165, 26);
		getContentPane().add(mes);
		
		JLabel lblDataInicial_1 = new JLabel("/");
		lblDataInicial_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataInicial_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataInicial_1.setBounds(334, 112, 13, 22);
		getContentPane().add(lblDataInicial_1);
		
		ano = new JComboBox();
		ano.setFont(new Font("Tahoma", Font.BOLD, 11));
		ano.setBounds(346, 108, 82, 26);
		getContentPane().add(ano);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VendasDlg dialog = new VendasDlg(false);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
