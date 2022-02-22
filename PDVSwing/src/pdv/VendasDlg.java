package pdv;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class VendasDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel panel_1 = new JPanel();
	private JTextField txValorTotal;
	private JTable tabCarrinho;
	private JTable tabVendas;
	private DefaultTableModel carrinhoModel = new DefaultTableModel();
	private DefaultTableModel vendasModel = new DefaultTableModel();
	

	
	public VendasDlg(boolean modal) {
		this();
		
		tabCarrinho.setModel(carrinhoModel);
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
		
		carrinhoModel.addColumn("Produto");
		carrinhoModel.addColumn("Qtd.");
		carrinhoModel.addColumn("Valor Total");
		
		preencherVendas();
		
		setModal(modal);
	}
	
	protected void vendasRowSelection() {
		int row    = tabVendas.getSelectedRow();
		Integer id = Integer.valueOf(tabVendas.getValueAt(row, 0).toString());

		EntityManager em = null;

		try {
			em = JPAUtil.getEntityManager();
			Venda venda = em.find(Venda.class, id);
			List<ItemVenda>carrinho = venda.getCarrinho();

			carrinhoModel.setRowCount(0); 

			for (ItemVenda item: carrinho) {
				Vector<String>dados = new Vector<String>();
				dados.add(item.getProduto().getNome());
				dados.add(item.getQuantidade().toString());
				dados.add(item.getValorTotal().toString());
				carrinhoModel.addRow(dados);
			}
			txValorTotal.setText(venda.getValorTotal().toString());
			
		} finally {
			em.close();
		}
	}

	private void preencherVendas() {
		EntityManager em = null;
		
		try {
			em = JPAUtil.getEntityManager();
			List<Venda> vendas = em.createQuery("select v from Venda v")
								   .getResultList();
			
			for (Venda venda : vendas) {
				Vector<String>dados = new Vector<String>();
				dados.add(venda.getId().toString());
				SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
				dados.add(fmt.format(venda.getDataVenda()));
				vendasModel.addRow(dados);
			}
		} finally {
			em.close();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VendasDlg() {
		setBounds(100, 100, 627, 480);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Vendas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 597, 154);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tabVendas = new JTable();
		scrollPane.setViewportView(tabVendas);
		panel_1.setBorder(new TitledBorder(null, "Carrinho de Compras", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 166, 597, 206);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		tabCarrinho = new JTable();
		scrollPane_1.setViewportView(tabCarrinho);
		
		JLabel lblValorTotal = new JLabel("Valor Total");
		lblValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorTotal.setBounds(285, 390, 167, 15);
		getContentPane().add(lblValorTotal);
		
		txValorTotal = new JTextField();
		txValorTotal.setBounds(457, 384, 152, 28);
		getContentPane().add(txValorTotal);
		txValorTotal.setColumns(10);

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
