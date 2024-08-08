package example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUI extends JFrame {
    private static JTextField pTextField = new JTextField();
    private static JTextField qTextField = new JTextField();
    private static JTextField dTextField = new JTextField();
    private static JTextField msgTextField = new JTextField();
    private static JTextField answerTextField = new JTextField();
    private static JButton alphabetButton = new JButton("Собрать алфавит");
    private static JButton reAlphabetButton = new JButton("Пересобрать алфавит");
    private static JButton cipherButton = new JButton("Закодировать/Раскодировать");
    private static ArrayList<AlphabetSymbol> alphabet = new ArrayList<>();

    public GUI() {
        super("Криптосистема Уильямса");

        JLabel pLabel = new JLabel("Введите простое число p: ");
        JLabel qLabel = new JLabel("Введите простое число q: ");
        JLabel dLabel = new JLabel("Выберите число d: ");
        JLabel msgLabel = new JLabel("Введите сообщение: ");
        JLabel answerLabel = new JLabel("Ответ: ");

        pTextField.setText("11");
        qTextField.setText("13");
        dTextField.setText("16");

        msgTextField.setEnabled(false);
        answerTextField.setEnabled(false);
        reAlphabetButton.setEnabled(false);
        cipherButton.setEnabled(false);

        validation(pTextField);
        validation(qTextField);
        validation(dTextField);

        JPanel panel = new JPanel((new GridLayout(6, 2)));

        panel.add(pLabel);
        panel.add(pTextField);

        panel.add(qLabel);
        panel.add(qTextField);

        panel.add(dLabel);
        panel.add(dTextField);

        panel.add(alphabetButton);
        panel.add(reAlphabetButton);

        panel.add(msgLabel);
        panel.add(msgTextField);

        panel.add(answerLabel);
        panel.add(answerTextField);

        add(panel, BorderLayout.CENTER);
        add(cipherButton, BorderLayout.SOUTH);

        alphabetAction();
        reAlphabetAction();
        cipherAction();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private static void validation(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (textField.getText().length() > 3) {
                    JOptionPane.showMessageDialog(null, "Слишком большое число!");
                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                }
                else {
                    char ch = e.getKeyChar();
                    if (!Character.isDigit(ch) && !(Character.toString(ch).equals("\b"))) {
                        JOptionPane.showMessageDialog(null, "Можно вводить только числа!");
                        if (textField.getText().length() > 0)
                            textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                    }
                }
            }
        });
    }

    private static void alphabetAction() {
        alphabetButton.addActionListener(e -> {
            if (pTextField.getText().length() == 0 ||
                    qTextField.getText().length() == 0 ||
                    dTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Не все поля заполнены!");
            }
            else {
                try {
                    long p = Long.parseLong(pTextField.getText());
                    long q = Long.parseLong(qTextField.getText());
                    long d = Long.parseLong(dTextField.getText());

                    if (isCorrectPQ(p, q) && isCorrectP(p) && isCorrectQ(q) && isCorrectD(p, q, d)) {
                        pTextField.setEnabled(false);
                        qTextField.setEnabled(false);
                        dTextField.setEnabled(false);
                        alphabetButton.setEnabled(false);

                        alphabet = Alphabet.alphabet(p, q, d);

                        if (alphabet != null) {
                            reAlphabetButton.setEnabled(true);
                            msgTextField.setEnabled(true);
                            cipherButton.setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Произошла непредвиденная ошибка!");
                            pTextField.setEnabled(true);
                            qTextField.setEnabled(true);
                            dTextField.setEnabled(true);
                            alphabetButton.setEnabled(true);
                        }
                    }

                } catch (NumberFormatException exception) {
                    System.out.println(exception.getMessage());
                    JOptionPane.showMessageDialog(null, "Ошибка! Проверьте данные!");
                }

            }
        });
    }

    private static void reAlphabetAction() {
        reAlphabetButton.addActionListener(e -> {
            reAlphabetButton.setEnabled(false);
            msgTextField.setEnabled(false);
            answerTextField.setEnabled(false);
            cipherButton.setEnabled(false);

            pTextField.setEnabled(true);
            qTextField.setEnabled(true);
            dTextField.setEnabled(true);
            alphabetButton.setEnabled(true);
        });
    }

    private static void cipherAction() {
        cipherButton.addActionListener(e -> {
            if (msgTextField.getText().length() > 0) {
                String msg = Message.message(msgTextField.getText(), alphabet);
                answerTextField.setText(msg);
                answerTextField.setEnabled(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "Введите сообщение!");
            }
        });
    }

    private static boolean isCorrectPQ(long p, long q) {
        if (p == q) {
            JOptionPane.showMessageDialog(null, "Ошибка! p и q должны отличаться!");
            return false;
        }
        return true;
    }

    private static boolean isCorrectP(long p) {
        if (p < 10 || p > 1000) {
            JOptionPane.showMessageDialog(null, "Ошибка! 10 < p < 1000!");
            return false;
        }
        if (isNotSimple(p)) {
            JOptionPane.showMessageDialog(null, "Число p не простое!");
            return false;
        }
        return true;
    }

    private static boolean isCorrectQ(long q) {
        if (q < 10 || q > 1000) {
            JOptionPane.showMessageDialog(null, "Ошибка! 10 < q < 1000!");
            return false;
        }
        if (isNotSimple(q)) {
            JOptionPane.showMessageDialog(null, "Число q не простое!");
            return false;
        }
        return true;
    }

    private static boolean isNotSimple(long pq) {
        for (int i = 2; i <= (pq / 2); ++i) {
            if (pq % i == 0)
                return true;
        }
        return false;
    }

    private static boolean isCorrectD(long p, long q, long d) {
        if (d < 2 || d > 100) {
            JOptionPane.showMessageDialog(null, "Ошибка! 1 < d < 100!");
            return false;
        }

        long deltaP = Finder.findDeltaP(p);
        long deltaQ = Finder.findDeltaQ(q);
        if (deltaP != 0 && deltaQ != 0) {
            long m = Finder.findM(p, q, deltaP, deltaQ);
            if (Utils.nod(d, m) != 1) {
                JOptionPane.showMessageDialog(null, "Ошибка! d не подходит!");
                return false;
            } else {
                return true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ошибка! p или q не подходят!");
            return false;
        }
    }
}