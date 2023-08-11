package tracking.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import framework.presentation.swing.Calendar;
import framework.presentation.swing.Window;
import framework.util.DateUtil;
import tracking.app.TimeTrackingService;
import tracking.app.TimeTrk;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTable tracks;
	private JTextField data;
	private DefaultTableModel tabModel = new DefaultTableModel();
	private JTextArea detalhe;
	private JButton calendar;
	
	private TimeTrackingService service = new TimeTrackingService();
	
	
	void salvar() {
		try {
			TimeTrk track = new TimeTrk();
			track.setData(
				DateUtil.parse(
					data.getText(), 
					DateUtil.dd_MM_yyyy
				)	
			);
			track.setDetalhe(detalhe.getText());
			
			service.persist(track);
			
			detalhe.setText("");
			data.setText("");

			populateTable();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void tracksMouseClicked() {
		Integer id   = Integer.valueOf(tracks.getValueAt(tracks.getSelectedRow(),0).toString());
		TimeTrk time = service.getById(id);
		
		detalhe.setText(time.getDetalhe());
	}
	
	public void init() {
		Font f = new Font("Serif", Font.PLAIN, 18);
		
		tracks.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tracks.setModel(tabModel);
		tracks.setFont(f);
		
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);

		tabModel.addColumn("Id");
		tabModel.addColumn("Data");
		
		tracks.getColumnModel().getColumn(0).setPreferredWidth(95);
		tracks.getColumnModel().getColumn(1).setPreferredWidth(175);
		tracks.getColumnModel().getColumn(1).setCellRenderer(cr);

		addBtCalendar();
		populateTable();
	}

	private void addBtCalendar() {
		calendar.setIcon(new ImageIcon(MainFrame.class.getResource("/calendar.png")));
		
		calendar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar cal = new Calendar(data);
				cal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				cal.populateDaysOfMonth();
				Window.centralizeWindow(cal);
				cal.setModal(true);
				cal.setVisible(true);
			}
		});		
	}
	
	private void populateTable() {
		tabModel.setRowCount(0);
		
		List<TimeTrk>timeTrks	= service.getAll();
		
		if (!timeTrks.isEmpty()) {
			for (TimeTrk time : timeTrks) {
				Vector<String>row = new Vector<String>();
				row.add(time.getId().toString());
				row.add(DateUtil.format(time.getData(),DateUtil.dd_MM_yyyy));
				
				tabModel.addRow(row);
			}
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					Window.centralizeWindow(frame);
					frame.init();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setBounds(100, 100, 1028, 745);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 45, 323, 640);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tracks = new JTable();
		tracks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tracksMouseClicked();
			}
		});
		scrollPane.setViewportView(tracks);
		
		JLabel lblData = new JLabel("Data");
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblData.setBounds(415, 54, 43, 16);
		getContentPane().add(lblData);
		
		data = new JTextField();
		data.setFont(new Font("Tahoma", Font.BOLD, 14));
		data.setBounds(470, 45, 182, 36);
		getContentPane().add(data);
		data.setColumns(10);
		
		JLabel lblDetalhamentoDasTarefas = new JLabel("Detalhamento das Tarefas");
		lblDetalhamentoDasTarefas.setHorizontalAlignment(SwingConstants.LEFT);
		lblDetalhamentoDasTarefas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDetalhamentoDasTarefas.setBounds(347, 107, 258, 16);
		getContentPane().add(lblDetalhamentoDasTarefas);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar();
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalvar.setBounds(816, 44, 182, 37);
		getContentPane().add(btnSalvar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(347, 136, 651, 549);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		detalhe = new JTextArea();
		detalhe.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane_1.setViewportView(detalhe);
		
		calendar = new JButton("");
		calendar.setBounds(666, 45, 81, 78);
		getContentPane().add(calendar);

	}
}
