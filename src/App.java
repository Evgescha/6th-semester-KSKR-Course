import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App {
	static Main main = new Main();
	private JFrame frame;
	private JTextField tfEnd;
	private JTextField tfStart;
	private JTextField tfFunc;
	private JTextField tfE;
	private JTextField tfAns;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle(
				"\u0420\u0435\u0448\u0435\u043D\u0438\u0435 \u043C\u0435\u0442\u043E\u0434\u043E\u043C \u0421\u0438\u043C\u043F\u0441\u043E\u043D\u0430");
		frame.setBounds(300, 300, 333, 213);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\u0414\u043E");
		lblNewLabel.setBounds(194, 36, 37, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u0418\u043D\u0442\u0435\u0433\u0440\u0430\u043B \u043E\u0442");
		lblNewLabel_1.setBounds(10, 36, 76, 14);
		frame.getContentPane().add(lblNewLabel_1);

		tfEnd = new JTextField();
		tfEnd.setBounds(221, 33, 91, 20);
		frame.getContentPane().add(tfEnd);
		tfEnd.setColumns(10);

		tfStart = new JTextField();
		tfStart.setColumns(10);
		tfStart.setBounds(96, 33, 76, 20);
		frame.getContentPane().add(tfStart);

		JLabel label = new JLabel("\u0424\u0443\u043D\u043A\u0446\u0438\u044F");
		label.setBounds(10, 64, 76, 14);
		frame.getContentPane().add(label);

		tfFunc = new JTextField();
		tfFunc.setBounds(96, 61, 216, 20);
		frame.getContentPane().add(tfFunc);
		tfFunc.setColumns(10);

		JButton btnNewButton = new JButton("\u041D\u0430\u0439\u0442\u0438");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnStart();
			}
		});
		btnNewButton.setBounds(10, 120, 91, 23);
		frame.getContentPane().add(btnNewButton);

		JButton button = new JButton("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u044C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClear();
			}
		});
		button.setBounds(10, 154, 91, 23);
		frame.getContentPane().add(button);

		JLabel label_1 = new JLabel("\u0422\u043E\u0447\u043D\u043E\u0441\u0442\u044C");
		label_1.setBounds(10, 11, 76, 14);
		frame.getContentPane().add(label_1);

		tfE = new JTextField();
		tfE.setColumns(10);
		tfE.setBounds(96, 8, 76, 20);
		frame.getContentPane().add(tfE);

		JLabel lbAns = new JLabel("\u041E\u0442\u0432\u0435\u0442");
		lbAns.setBounds(10, 95, 46, 14);
		frame.getContentPane().add(lbAns);

		tfAns = new JTextField();
		tfAns.setColumns(10);
		tfAns.setBounds(96, 92, 216, 20);
		frame.getContentPane().add(tfAns);

		JButton button_1 = new JButton("\u041F\u0440\u0438\u043C\u0435\u0440");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExample();
			}
		});
		button_1.setBounds(221, 120, 91, 23);
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton("\u0418\u0437 \u0444\u0430\u0439\u043B\u0430");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOpenFile();
			}
		});
		button_2.setBounds(111, 120, 100, 23);
		frame.getContentPane().add(button_2);

		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHelp();
			}
		});
		btnHelp.setBounds(221, 154, 91, 23);
		frame.getContentPane().add(btnHelp);

		JButton button_3 = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave();
			}
		});
		button_3.setBounds(111, 154, 100, 23);
		frame.getContentPane().add(button_3);
	}

	// метод проверки полей на пустоту
	boolean isEmpty() {
		if (tfStart.getText().length() < 1 || tfEnd.getText().length() < 1 || tfE.getText().length() < 1
				|| tfFunc.getText().length() < 1)
			return true;
		else
			return false;
	}

	// кнопка решать
	void btnStart() {
		if (isEmpty()) {
			JOptionPane.showMessageDialog(frame, "�� ��� ������ ���������");
			return;
		}
		if (Double.parseDouble(tfStart.getText())>Double.parseDouble(tfEnd.getText())) {
			JOptionPane.showMessageDialog(frame, "����� ������� ��������� ������ ������");
			return;
		}
		if (Double.parseDouble(tfStart.getText())==Double.parseDouble(tfEnd.getText())) {
			tfAns.setText("0");;
			return;
		}

		main.Calc(tfStart, tfEnd, tfFunc, tfE, tfAns);
	}

	// кнопка пример
	void btnExample() {
		tfAns.setText("");
		main.Example(tfStart, tfEnd, tfFunc, tfE);
	}

	// кнопка очистить
	void btnClear() {
		tfAns.setText("");
		tfStart.setText("");
		tfEnd.setText("");
		tfE.setText("");
		tfFunc.setText("");
	}

	// кнопка открыть Файл
	void btnOpenFile() {
		JFileChooser fileopen = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("TXT file", new String[] { "txt" });
		fileopen.setAcceptAllFileFilterUsed(false);
		fileopen.setFileFilter(filter);
		int ret = fileopen.showDialog(null, "������� ����");
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fileopen.getSelectedFile();
			main.fromFile(file, tfStart, tfEnd, tfFunc, tfE, tfAns);
		} // else JOptionPane.showMessageDialog(frame, "������ �������� �����.");
	}

	// кнопка помочь с файлом
	void btnHelp() {
		JOptionPane.showMessageDialog(frame,
				"������ � ����� ������ ����������� ��������� �������:\n"
						+ "��������, ������ ���������, ����� ���������, �������\n" + "������ �������� � ����� ������\n"
						+ "������ \n" + "0.001\n" + "0.6\n" + "2.2\n" + "y(x)=cos(x)/(x^2+1)");
	}

	// кнопка сохранить в файл
	void btnSave() {
		if (isEmpty()) {
			JOptionPane.showMessageDialog(frame, "�� ��� ������ ���������");
			return;
		}
		try {
			JFileChooser fileopen = new JFileChooser();
			FileFilter filter = new FileNameExtensionFilter("TXT file", new String[] { "txt" });
			fileopen.setAcceptAllFileFilterUsed(false);
			fileopen.setFileFilter(filter);
			int ret = fileopen.showSaveDialog(frame);
			if (ret == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileopen.getSelectedFile() + ".txt");
				FileWriter fw = new FileWriter(file);
				fw.write(tfE.getText() + "\n");
				fw.write(tfStart.getText() + "\n");
				fw.write(tfEnd.getText() + "\n");
				fw.write(tfFunc.getText() + "\n");
				fw.write(tfAns.getText() + "\n");
				fw.flush();
				fw.close();
				JOptionPane.showMessageDialog(frame, "���� ������� ��������!");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "������ ���������� �����");
		}

	}
}
