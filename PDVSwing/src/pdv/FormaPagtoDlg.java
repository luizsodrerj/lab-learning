package pdv;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import pdv.domain.FormaPagamento;
import persistence.FormaRepo;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class FormaPagtoDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JTextField txForma;
	private JList formasPagto;

	private List<FormaPagamento>formas = new ArrayList<FormaPagamento>();
	private FormaPagamento formaPagamento;
	private FormaRepo repo = new FormaRepo();
	
	

	void formasPgValueChanged(ListSelectionEvent e) {
		if (formasPagto.getSelectedIndex() > -1) {
			formaPagamento = formas.get(formasPagto.getSelectedIndex());
			txForma.setText(formaPagamento.getForma());
		}
	}
	
	public void confirmar() {
		if (formasPagto.getSelectedIndex() < 0) {
			FormaPagamento forma = new FormaPagamento();
			forma.setForma(txForma.getText());
			
			repo.persist(forma);
			
		} else {
			formaPagamento.setForma(txForma.getText());
			repo.update(formaPagamento);
			
			formasPagto.setSelectedIndex(-1);
		}
		((DefaultListModel)(formasPagto.getModel())).removeAllElements();
		txForma.setText("");
		formas = repo.getAll();
		init();
	}

	private void init() {
		for (FormaPagamento forma: formas) {
			((DefaultListModel)(formasPagto.getModel()))
				.addElement(
					forma.getForma()
				);
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormaPagtoDlg formaPgDlg = new FormaPagtoDlg(true);
					formaPgDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					formaPgDlg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormaPagtoDlg(boolean init) {
		this();
		
		formasPagto.setModel(new DefaultListModel());
		formas = repo.getAll();
		
		init();
	}
	
	/**
	 * Create the dialog.
	 */
	public FormaPagtoDlg() {
		setBounds(100, 100, 512, 355);
		getContentPane().setLayout(null);
		
		JLabel lblFormaPagamento = new JLabel("Forma de Pagamento");
		lblFormaPagamento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFormaPagamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaPagamento.setBounds(10, 14, 156, 14);
		getContentPane().add(lblFormaPagamento);
		
		txForma = new JTextField();
		txForma.setBounds(176, 8, 310, 27);
		getContentPane().add(txForma);
		txForma.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfirmar.setBounds(176, 46, 156, 35);
		getContentPane().add(btnConfirmar);
		
		JPanel panel = new JPanel();
		panel.setBounds(176, 92, 310, 213);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		formasPagto = new JList();
		formasPagto.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				formasPgValueChanged(e);
			}
		});
		scrollPane.setViewportView(formasPagto);
		
		JButton btnLImpar = new JButton("Limpar");
		btnLImpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLImpar.setBounds(342, 46, 144, 35);
		getContentPane().add(btnLImpar);

	}

}
