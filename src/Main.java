import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

public class Main {
	// начальные и коненые значения
	double a, b;
	//  точность и шаг
	double e, h;
	// число делений интервала
	double n;
	// ответ решения симпсона при обыном и двойном шаге
	double In, I2n;
	// массив значений при обыном шаге
	double[][] arrStep;
	// массив значений при двойном шаге
	double[][] arrStep2;
	// кличество символов после запятой у точности
	int dot;
	// функция
	String f;

	// метод нахождения шага
	double CalcH() {
		h = Math.pow(e, 0.25);
		n = (b - a) / h;
		n = Math.ceil(n);
		while (n % 4 != 0)
			n++;
		h = (b - a) / n;
		arrStep = new double[(int) n + 1][2];
		arrStep2 = new double[(int) n / 2 + 1][2];
		return h;
	}

	// метод нахождения значений х и у
	void CalcXandY() {
		for (int i = 0; i < arrStep.length; i++) {
			arrStep[i][0] = a + i * h;
			arrStep[i][1] = CalcFunction(f, "y(" + arrStep[i][0] + ")");
			if (i % 2 == 0) {
				arrStep2[i / 2][0] = arrStep[i][0];
				arrStep2[i / 2][1] = arrStep[i][1];
			}
		}
	}

	// четные значения
	double yChet() {
		double sum = 0;
		for (int i = 1; i < arrStep.length - 1; i++) {
			if (i % 2 == 0)
				sum += arrStep[i][1];
		}
		return sum;
	}

	// нечетные значения
	double yChet2H() {
		double sum = 0;
		for (int i = 1; i < arrStep2.length - 1; i++) {
			if (i % 2 == 0)
				sum += arrStep2[i][1];
		}
		return sum;
	}

	// вычисление методом симпсона
	void CalcSimps() {
		In = (h / 3) * (arrStep[0][1] + arrStep[(int) n][1] + 4 * yNechet() + 2 * yChet());
	}

	// вычисление методом симпсона двойным шагом
	void CalcSimps2H() {
		I2n = 2 * (h / 3) * (arrStep[0][1] + arrStep[(int) n][1] + 4 * yNechet2H() + 2 * yChet2H());
	}

	// нечетные значения двойного шага
	double yNechet2H() {
		double sum = 0;
		for (int i = 1; i < arrStep2.length - 1; i++) {
			if (i % 2 != 0)
				sum += arrStep2[i][1];
		}
		return sum;
	}

	// нечетные значения
	double yNechet() {
		double sum = 0;
		for (int i = 1; i < arrStep.length - 1; i++) {
			if (i % 2 != 0)
				sum += arrStep[i][1];
		}
		return sum;
	}

	// вычисление значения функции
	double CalcFunction(String func, String expr) {
		Function f = new Function(func);
		Expression e = new Expression(expr, f);
		return e.calculate();
	}

	// подстановка значений для примера
	void Example(JTextField start, JTextField stop, JTextField func, JTextField e) {
		start.setText("0.6");
		stop.setText("2.2");
		func.setText("y(x) = cos(x)/(x^2 + 1)");
		e.setText("0.001");
	}

	// вычисление количества знаков у точности
	void Dot(String str) {
		String[] splitter = String.valueOf(str).split("\\.");
		dot = splitter[1].length();
	}

	// обрезание ответа под точность
	String setAns() {
		double pow = Math.pow(10, dot);
		double ans = Math.ceil(In * pow);
		return ans / pow + "";
	}

	// решение уравнения
	void Calc(JTextField a, JTextField b, JTextField func, JTextField ex, JTextField ans) {
		try {
			this.e = Double.valueOf(ex.getText());
			this.a = Double.valueOf(a.getText());
			this.b = Double.valueOf(b.getText());
			this.f = func.getText();
			Dot(ex.getText());
			CalcH();
			CalcXandY();
			CalcSimps();
			CalcSimps2H();

			ans.setText(setAns());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "������������ ������ \n" + "��������� ������������ �����");
		}
	}

	// чтение значений из файла
	void fromFile(File f, JTextField a, JTextField b, JTextField func, JTextField ex, JTextField ans) {
		try {
			Scanner sc = new Scanner(f);

			this.e = Double.valueOf(sc.nextLine());
			this.a = Double.valueOf(sc.nextLine());
			this.b = Double.valueOf(sc.nextLine());
			this.f = sc.nextLine();

			ex.setText(this.e + "");
			a.setText(this.a + "");
			b.setText(this.b + "");
			func.setText(this.f + "");
			sc.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "���� � ������������� ����������");
		}
	}

}
