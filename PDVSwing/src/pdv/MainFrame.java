package pdv;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import framework.persistence.jpa.PersistenceServiceUtil;
import framework.presentation.swing.Window;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;


	
	void showVendas() {
		VendasDlg vendas = new VendasDlg(true);
		vendas.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Window.centralizeWindow(vendas);
		vendas.setVisible(true);
	}
	
	void showPDVForm() {
		PDVForm frame = new PDVForm();
		Window.centralizeWindow(frame);
		frame.configTableCarrinho();
		frame.setVisible(true);
	}

	void showProdForm() {
		CadProdutoDlg prodDlg = new CadProdutoDlg(true);
		Window.centralizeWindow(prodDlg);
		prodDlg.setVisible(true);
	}
	
	public void init() {
		PersistenceServiceUtil persis = new PersistenceServiceUtil();
		try {
			persis.connect();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			persis.close();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIniciarVenda = new JButton("Iniciar Venda");
		btnIniciarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPDVForm();
			}
		});
		btnIniciarVenda.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIniciarVenda.setBounds(10, 11, 195, 47);
		contentPane.add(btnIniciarVenda);
		
		JButton btCadProdutos = new JButton("Cadastro de Produtos");
		btCadProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showProdForm();
			}
		});
		btCadProdutos.setFont(new Font("Tahoma", Font.BOLD, 11));
		btCadProdutos.setBounds(215, 11, 189, 47);
		contentPane.add(btCadProdutos);
		
		JButton btnRelatrioDeVendas = new JButton("Relat\u00F3rio de Vendas");
		btnRelatrioDeVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showVendas();
			}
		});
		btnRelatrioDeVendas.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRelatrioDeVendas.setBounds(414, 11, 189, 47);
		contentPane.add(btnRelatrioDeVendas);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 69, 594, 2);
		contentPane.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 82, 593, 253);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/img/car.png")));
		panel.add(lblNewLabel, BorderLayout.CENTER);
	}

}
