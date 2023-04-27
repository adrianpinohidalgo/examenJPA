package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AsignaturaController;
import controller.AsignaturapordocenteController;
import controller.DocenteController;
import model.Asignatura;
import model.Asignaturaspordocente;
import model.Docente;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField jtfFiltrar;
	private static JComboBox<Docente> jcbDocentes;

	private JList jlistAsignaturasIzq;
	private JList jlistAsignaturasDer;
	private DefaultListModel<Asignatura> listModelAsignaturasIzq = null;
	private DefaultListModel<Asignatura> listModelAsignaturasDer = null;
	List<Asignatura> l1;
	List<Asignatura> l3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 1.0 };
//		gbl_contentPane.columnWidths = new int[]{0};
//		gbl_contentPane.rowHeights = new int[]{0};
//		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
//		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("Docentes y asignaturas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		jtfFiltrar = new JTextField();
		GridBagConstraints gbc_jtfFiltrar = new GridBagConstraints();
		gbc_jtfFiltrar.gridwidth = 2;
		gbc_jtfFiltrar.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltrar.gridx = 0;
		gbc_jtfFiltrar.gridy = 1;
		contentPane.add(jtfFiltrar, gbc_jtfFiltrar);
		jtfFiltrar.setColumns(10);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarJcbDocente();
			}
		});
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 5, 0);
		gbc_btnFiltrar.gridx = 2;
		gbc_btnFiltrar.gridy = 1;
		contentPane.add(btnFiltrar, gbc_btnFiltrar);

		jcbDocentes = new JComboBox<Docente>();
		GridBagConstraints gbc_jcbMaterias = new GridBagConstraints();
		gbc_jcbMaterias.gridwidth = 2;
		gbc_jcbMaterias.insets = new Insets(0, 0, 5, 5);
		gbc_jcbMaterias.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbMaterias.gridx = 0;
		gbc_jcbMaterias.gridy = 2;
		contentPane.add(jcbDocentes, gbc_jcbMaterias);

		JButton btnMaterias = new JButton("Cargar materias");
		btnMaterias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarMaterias();
			}
		});
		GridBagConstraints gbc_btnMaterias = new GridBagConstraints();
		gbc_btnMaterias.insets = new Insets(0, 0, 5, 0);
		gbc_btnMaterias.gridx = 2;
		gbc_btnMaterias.gridy = 2;
		contentPane.add(btnMaterias, gbc_btnMaterias);

		JLabel lblNewLabel_1 = new JLabel("Asignaturas no seleccionadas");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Asignaturas seleccionadas");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 3;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JButton btnTodoDerecha = new JButton(">>");
		btnTodoDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				todoDer();
			}
		});
		GridBagConstraints gbc_btnTodoDerecha = new GridBagConstraints();
		gbc_btnTodoDerecha.insets = new Insets(0, 0, 5, 5);
		gbc_btnTodoDerecha.gridx = 1;
		gbc_btnTodoDerecha.gridy = 4;
		contentPane.add(btnTodoDerecha, gbc_btnTodoDerecha);

		jlistAsignaturasIzq = new JList(this.getDefaultListModelIzq());
		this.jlistAsignaturasIzq.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		JScrollPane scrollPaneIzquierda = new JScrollPane(jlistAsignaturasIzq);
		GridBagConstraints gbc_scrollPaneIzquierda = new GridBagConstraints();
		gbc_scrollPaneIzquierda.gridheight = 4;
		gbc_scrollPaneIzquierda.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneIzquierda.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneIzquierda.gridx = 0;
		gbc_scrollPaneIzquierda.gridy = 4;
		contentPane.add(scrollPaneIzquierda, gbc_scrollPaneIzquierda);

		jlistAsignaturasDer = new JList(this.getDefaultListModelDer());
		this.jlistAsignaturasDer.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		JScrollPane scrollPaneDerecha = new JScrollPane(jlistAsignaturasDer);
		GridBagConstraints gbc_scrollPaneDerecha = new GridBagConstraints();
		gbc_scrollPaneDerecha.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneDerecha.gridheight = 4;
		gbc_scrollPaneDerecha.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneDerecha.gridx = 2;
		gbc_scrollPaneDerecha.gridy = 4;
		contentPane.add(scrollPaneDerecha, gbc_scrollPaneDerecha);

		JButton btnDerecha = new JButton(">");
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unoDerecha();
			}
		});
		GridBagConstraints gbc_btnDerecha = new GridBagConstraints();
		gbc_btnDerecha.insets = new Insets(0, 0, 5, 5);
		gbc_btnDerecha.gridx = 1;
		gbc_btnDerecha.gridy = 5;
		contentPane.add(btnDerecha, gbc_btnDerecha);

		JButton btnIzquierda = new JButton("<");
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unoIzquierda();
			}
		});
		GridBagConstraints gbc_btnIzquierda = new GridBagConstraints();
		gbc_btnIzquierda.insets = new Insets(0, 0, 5, 5);
		gbc_btnIzquierda.gridx = 1;
		gbc_btnIzquierda.gridy = 6;
		contentPane.add(btnIzquierda, gbc_btnIzquierda);

		JButton btnTodoIzquierda = new JButton("<<");
		btnTodoIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				todoIzq();
			}
		});
		GridBagConstraints gbc_btnTodoIzquierda = new GridBagConstraints();
		gbc_btnTodoIzquierda.insets = new Insets(0, 0, 5, 5);
		gbc_btnTodoIzquierda.gridx = 1;
		gbc_btnTodoIzquierda.gridy = 7;
		contentPane.add(btnTodoIzquierda, gbc_btnTodoIzquierda);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 1;
		gbc_btnGuardar.gridy = 8;
		contentPane.add(btnGuardar, gbc_btnGuardar);
	}

	private void llenarJcbDocente() {
		try {
			jcbDocentes.removeAllItems();
		} catch (Exception e) {
		}
		for (Docente o : DocenteController.findByLikeNombre(jtfFiltrar.getText())) {
			jcbDocentes.addItem(o);
		}
	}

	private DefaultListModel getDefaultListModelIzq() {
		this.listModelAsignaturasIzq = new DefaultListModel<Asignatura>();
		return this.listModelAsignaturasIzq;
	}

	private DefaultListModel getDefaultListModelDer() {
		this.listModelAsignaturasDer = new DefaultListModel<Asignatura>();
		return this.listModelAsignaturasDer;
	}

	private void cargarMaterias() {
		Docente o = (Docente) jcbDocentes.getSelectedItem();

//		List<Asignaturaspordocente> lrepe = AsignaturapordocenteController.findDistinct(o.getId());
//		for (Asignaturaspordocente asignaturaspordocente : lrepe) {
//			System.out.println(asignaturaspordocente.toString());
//		}

		listModelAsignaturasDer.removeAllElements();
		listModelAsignaturasIzq.removeAllElements();

		for (Docente o1 : DocenteController.findByLikeNombre(o.getNombreCompleto())) {
			List<Asignaturaspordocente> l = AsignaturapordocenteController.findById(o.getId());
			List<Asignaturaspordocente> l2 = AsignaturapordocenteController.findByIdDistinto(o.getId());

			for (Asignaturaspordocente asignaturaspordocente : l) {
				l1 = AsignaturaController.findByAsignatura(asignaturaspordocente.getAsignatura());
				listModelAsignaturasDer.addAll(l1);
			}

			for (Asignaturaspordocente asignaturaspordocente : l2) {
				l3 = AsignaturaController.findByAsignatura(asignaturaspordocente.getAsignatura());
				listModelAsignaturasIzq.addAll(l3);
			}
		}
	}

	private void unoDerecha() {
		listModelAsignaturasDer.addAll(jlistAsignaturasIzq.getSelectedValuesList());

		for (int i = jlistAsignaturasIzq.getSelectedIndices().length - 1; i >= 0; i--) {
			listModelAsignaturasIzq.removeElementAt(jlistAsignaturasIzq.getSelectedIndices()[i]);
		}
	}

	private void unoIzquierda() {
		listModelAsignaturasIzq.addAll(jlistAsignaturasDer.getSelectedValuesList());

		for (int i = jlistAsignaturasDer.getSelectedIndices().length - 1; i >= 0; i--) {
			listModelAsignaturasDer.removeElementAt(jlistAsignaturasDer.getSelectedIndices()[i]);
		}

	}

	private void guardar() {
		List<Asignatura> l = new ArrayList<Asignatura>();
		for (int i = 0; i < listModelAsignaturasDer.size(); i++) {
			l.add(listModelAsignaturasDer.getElementAt(i));
		}
			

		Docente o = (Docente) jcbDocentes.getSelectedItem();
		
		for (Asignatura asignatura : l) {
			Asignaturaspordocente o1 = new Asignaturaspordocente();
			o1.setId(1);
			o1.setAsignatura(asignatura);
			o1.setDocente(o);

			System.out.println(o1.toString());
			if (AsignaturapordocenteController.findByCosas(asignatura.getId(), o.getId()) != null) {
				Asignaturaspordocente o20 = AsignaturapordocenteController.findByCosas(asignatura.getId(), o.getId());

				o1.setId(o20.getId());
				AsignaturapordocenteController.delete(o1);

			}
			o1.setId(0);
			AsignaturapordocenteController.insert(o1);
		}

	}

	private void todoIzq() {
		List<Asignatura> l = new ArrayList<Asignatura>();
		for (int i = 0; i < listModelAsignaturasDer.size(); i++) {
			l.add(listModelAsignaturasDer.getElementAt(i));
		}

		listModelAsignaturasIzq.addAll(l);
		listModelAsignaturasDer.removeAllElements();
	}

	private void todoDer() {
		List<Asignatura> l = new ArrayList<Asignatura>();
		for (int i = 0; i < listModelAsignaturasIzq.size(); i++) {
			l.add(listModelAsignaturasIzq.getElementAt(i));
		}

		listModelAsignaturasDer.addAll(l);
		listModelAsignaturasIzq.removeAllElements();
	}

}
