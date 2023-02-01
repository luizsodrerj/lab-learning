package pdv;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import framework.presentation.swing.Calendar;
import framework.presentation.swing.Window;
import framework.util.DateUtil;
import util.CalendarUtil;

import javax.swing.event.ChangeEvent;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VendasConsolidadasDlg extends JDialog {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel tabModel = new DefaultTableModel();
	
	private ButtonGroup grupo = new ButtonGroup();
	private JPanel pnlMesAno;
	private JPanel pnlPeriodo;
	private JPanel pnlAno;
	private JPanel pnlDia;
	private JLabel lblDataInicial;
	private JTextField dtIni;
	private JLabel lblDataFinal;
	private JButton btIni;
	private JButton btFim;
	private JTextField dtFim;
	private JComboBox cbVendasAno;
	JComboBox cbMes;
	JComboBox cbAno;
	private JTextField txDia;
	private JButton btDia;
	private JTable tabVendas;

	
	void vendasPorMsanoStateChanged(ChangeEvent e) {
		hidePanels(true, false, false, false, e);	
	}
	
	void vendasPorPeriodoStateChanged(ChangeEvent e) {
		hidePanels(false, true, false, false, e);
	}

	void vendasPorAnoStateChanged(ChangeEvent e) {
		hidePanels(false, false, true, false, e);
	}

	void vendasPorDiaStateChanged(ChangeEvent e) {
		hidePanels(false, false, false, true, e);
	}

	void hidePanels(
			boolean mesAno, 
			boolean periodo, 
			boolean ano, 
			boolean dia,
			ChangeEvent e
		) {
		if (((JRadioButton)e.getSource()).isSelected()) {
			if (pnlMesAno != null) {
				pnlMesAno.setVisible(mesAno);	
			}
			if (pnlPeriodo != null) {
				pnlPeriodo.setVisible(periodo);	
			}
			if (pnlAno != null) {
				pnlAno.setVisible(ano);	
			}
			if (pnlDia != null) {
				pnlDia.setVisible(dia);	
			}
		}
	}
	
	public void init() {
        addDateBtListener(btDia, txDia);
        addDateBtListener(btIni, dtIni);
        addDateBtListener(btFim, dtFim);
        initTabVendas();
		initCombos();
	}

	private void initTabVendas() {
		tabVendas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabVendas.setModel(tabModel);
		
		tabModel.addColumn("Data");
		tabModel.addColumn("Total de Vendas");
		
		TableColumnModel columnModel = tabVendas.getColumnModel();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		columnModel.getColumn(0).setCellRenderer(centerRenderer);
		columnModel.getColumn(0).setPreferredWidth(200);
		
		columnModel.getColumn(1).setCellRenderer(new DefaultTableCellRenderer());
		((DefaultTableCellRenderer)columnModel.getColumn(1).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		columnModel.getColumn(1).setPreferredWidth(333);
	}
	
	private void initCombos() {
		DefaultComboBoxModel vndAnoModel = new DefaultComboBoxModel();
		DefaultComboBoxModel mesModel 	 = new DefaultComboBoxModel();
		DefaultComboBoxModel anoModel 	 = new DefaultComboBoxModel();
		this.cbVendasAno.setModel(vndAnoModel);
		this.cbMes.setModel(mesModel);
		this.cbAno.setModel(anoModel);
		
		vndAnoModel.addElement("");
		anoModel.addElement("");
		mesModel.addElement("");
		
		String[] meses = CalendarUtil.getMonthNames();
		
		for (String mes: meses) {
			mesModel.addElement(mes);
		}
        int anoAtual = new DateUtil().getYear();
        int anoMin 	 = 1980;
        
        while (anoAtual >= anoMin) {
        	vndAnoModel.addElement(String.valueOf(anoAtual));
			anoModel.addElement(String.valueOf(anoAtual));
			anoAtual--;
		}
	}
	
	private void addDateBtListener(JButton bt, JTextField txt) {
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
	
	public VendasConsolidadasDlg(boolean init) {
		this();
		init();
	}
	
	/**
	 * Create the dialog.
	 */
	public VendasConsolidadasDlg() {
		setBounds(100, 100, 921, 584);
		getContentPane().setLayout(null);
		
		JRadioButton rdbtnVendasPorMsano = new JRadioButton("Vendas por M\u00EAs/Ano");
		rdbtnVendasPorMsano.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				vendasPorMsanoStateChanged(e);
			}
		});
		rdbtnVendasPorMsano.setSelected(true);
		rdbtnVendasPorMsano.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnVendasPorMsano.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnVendasPorMsano.setBounds(6, 7, 143, 23);
		getContentPane().add(rdbtnVendasPorMsano);
		grupo.add(rdbtnVendasPorMsano);
		
		JRadioButton rdbtnVendasPorPeriodo = new JRadioButton("Vendas por Per\u00EDodo");
		rdbtnVendasPorPeriodo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				vendasPorPeriodoStateChanged(e);
			}
		});
		rdbtnVendasPorPeriodo.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnVendasPorPeriodo.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnVendasPorPeriodo.setBounds(6, 41, 143, 23);
		getContentPane().add(rdbtnVendasPorPeriodo);
		grupo.add(rdbtnVendasPorPeriodo);
		
		JRadioButton rdbtnVendasPorAno = new JRadioButton("Vendas por Ano");
		rdbtnVendasPorAno.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				vendasPorAnoStateChanged(e);
			}
		});
		rdbtnVendasPorAno.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnVendasPorAno.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnVendasPorAno.setBounds(6, 122, 144, 23);
		getContentPane().add(rdbtnVendasPorAno);
		grupo.add(rdbtnVendasPorAno);
		
		JRadioButton rdbtnVendasPorDia = new JRadioButton("Vendas por Dia");
		rdbtnVendasPorDia.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				vendasPorDiaStateChanged(e);
			}
		});
		rdbtnVendasPorDia.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnVendasPorDia.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnVendasPorDia.setBounds(6, 163, 144, 23);
		getContentPane().add(rdbtnVendasPorDia);
		grupo.add(rdbtnVendasPorDia);
		
		JButton btPesquisar = new JButton("Pesquisar");
		btPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btPesquisar.setBounds(726, 7, 169, 39);
		getContentPane().add(btPesquisar);
		
		pnlMesAno = new JPanel();
		pnlMesAno.setBounds(155, 7, 561, 23);
		getContentPane().add(pnlMesAno);
		pnlMesAno.setLayout(null);
		
		JLabel lblMs = new JLabel("M\u00EAs");
		lblMs.setBounds(0, 3, 36, 14);
		pnlMesAno.add(lblMs);
		lblMs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMs.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		cbMes = new JComboBox();
		cbMes.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbMes.setBounds(46, 0, 133, 20);
		pnlMesAno.add(cbMes);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(205, 3, 39, 14);
		pnlMesAno.add(lblAno);
		lblAno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAno.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		cbAno = new JComboBox();
		cbAno.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbAno.setBounds(256, 0, 76, 20);
		pnlMesAno.add(cbAno);
		
		pnlPeriodo = new JPanel();
		pnlPeriodo.setVisible(false);
		pnlPeriodo.setBounds(155, 41, 561, 70);
		getContentPane().add(pnlPeriodo);
		pnlPeriodo.setLayout(null);
		
		lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataInicial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataInicial.setBounds(0, 6, 78, 14);
		pnlPeriodo.add(lblDataInicial);
		
		dtIni = new JTextField();
		dtIni.setBounds(88, 0, 86, 26);
		pnlPeriodo.add(dtIni);
		dtIni.setColumns(10);
		
		lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFinal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataFinal.setBounds(272, 6, 78, 14);
		pnlPeriodo.add(lblDataFinal);
		
		btIni = new JButton("");
		btIni.setIcon(new ImageIcon(VendasConsolidadasDlg.class.getResource("/resources/img/calendar.png")));
		btIni.setBounds(184, 2, 89, 68);
		pnlPeriodo.add(btIni);
		
		btFim = new JButton("");
		btFim.setIcon(new ImageIcon(VendasConsolidadasDlg.class.getResource("/resources/img/calendar.png")));
		btFim.setBounds(456, 2, 89, 68);
		pnlPeriodo.add(btFim);
		
		dtFim = new JTextField();
		dtFim.setColumns(10);
		dtFim.setBounds(360, 3, 86, 26);
		pnlPeriodo.add(dtFim);
		
		pnlAno = new JPanel();
		pnlAno.setVisible(false);
		pnlAno.setLayout(null);
		pnlAno.setBounds(156, 112, 560, 50);
		getContentPane().add(pnlAno);
		
		cbVendasAno = new JComboBox();
		cbVendasAno.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbVendasAno.setBounds(10, 11, 91, 29);
		pnlAno.add(cbVendasAno);
		
		pnlDia = new JPanel();
		pnlDia.setVisible(false);
		pnlDia.setLayout(null);
		pnlDia.setBounds(156, 163, 560, 70);
		getContentPane().add(pnlDia);
		
		txDia = new JTextField();
		txDia.setColumns(10);
		txDia.setBounds(10, 0, 89, 26);
		pnlDia.add(txDia);
		
		btDia = new JButton("");
		btDia.setIcon(new ImageIcon(VendasConsolidadasDlg.class.getResource("/resources/img/calendar.png")));
		btDia.setBounds(109, 0, 89, 68);
		pnlDia.add(btDia);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 244, 536, 15);
		getContentPane().add(separator);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 251, 536, 283);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tabVendas = new JTable();
		scrollPane.setViewportView(tabVendas);

	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VendasConsolidadasDlg dialog = new VendasConsolidadasDlg(true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
