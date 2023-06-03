import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JPanel panelBMI;
    private JPanel panelKalorie;
    private JPanel panelOProgramie;
    private JLabel labelWaga, labelWzrost, labelBMI, labelWynikBMI;
    private JTextField textFieldWaga, textFieldWzrost;
    private JButton buttonObliczBMI;
    private JLabel labelWiek, labelPlec, labelAktywnosc, labelWynikKalorie;
    private JTextField textFieldWiek;
    private JRadioButton radioButtonMezczyzna, radioButtonKobieta;
    private JComboBox<String> comboBoxAktywnosc;
    private JButton buttonObliczKalorie;
    private JLabel labelAutor;
    //[opis programu] private JLabel labelOpisProgramu;
    private JTable tableBMI;
    private static Random random = new Random();
    private void zapiszDoPliku(String zawartosc) {
        try {
            //tworzenie obiektu daty i formatowanie
            Date data = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dataSformatowana = format.format(data);

            //id, data, zawartosc
            String tekstZDanymi = "[ID: " + generujID() + "][" + dataSformatowana + "]" + zawartosc + "\n";

            FileWriter writer = new FileWriter("wyniki.txt", true); //true=tryb dopisywania
            writer.write(tekstZDanymi);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generujID() {
        String id = generateRandomID();
        return id;
    }

    private String generateRandomID() {
        StringBuilder sb = new StringBuilder();
        int length = 6; //zdefiniowanie dlugosci ID (liczba cyfr)
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); //losowa cyfra (0-9)
            sb.append(digit);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Main kalkulator = new Main();
        kalkulator.inicjalizujGUI();
    }

    private void inicjalizujGUI() {
        frame = new JFrame("BeFit - zdrowy kalkulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(420, 320));
        frame.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        panelBMI = new JPanel();
        panelBMI.setLayout(new BorderLayout());

        //tabela z zakresem BMI dla roznych kat.
        Object[][] data = {
                {"Niedowaga", "< 18.5"},
                {"Prawidłowa", "18.5 - 24.9"},
                {"Nadwaga", "25.0 - 29.9"},
                {"Otyłość", "≥ 30.0"}
        };
        String[] columnNames = {"Kategoria", "Zakres BMI"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tableBMI = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableBMI);
        panelBMI.add(scrollPane, BorderLayout.CENTER);
        //panel zakladki kalkulator bmi
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        labelWaga = new JLabel("Waga (kg):");
        labelWzrost = new JLabel("Wzrost (cm):");
        textFieldWaga = new JTextField();
        textFieldWzrost = new JTextField();
        buttonObliczBMI = new JButton("Oblicz");
        labelBMI = new JLabel("BMI:");
        labelWynikBMI = new JLabel();

        inputPanel.add(labelWaga);
        inputPanel.add(textFieldWaga);
        inputPanel.add(labelWzrost);
        inputPanel.add(textFieldWzrost);
        inputPanel.add(new JLabel());
        inputPanel.add(buttonObliczBMI);
        inputPanel.add(labelBMI);
        inputPanel.add(labelWynikBMI);

        panelBMI.add(inputPanel, BorderLayout.NORTH);
        //panel zakladki Kalkulator Kalorii
        panelKalorie = new JPanel();
        panelKalorie.setLayout(new GridLayout(6, 2));

        labelWiek = new JLabel("Wiek:");
        labelPlec = new JLabel("Płeć:");
        labelAktywnosc = new JLabel("Aktywność fizyczna:");
        labelWynikKalorie = new JLabel();
        textFieldWiek = new JTextField();
        radioButtonMezczyzna = new JRadioButton("Mężczyzna");
        radioButtonKobieta = new JRadioButton("Kobieta");
        ButtonGroup buttonGroupPlec = new ButtonGroup();
        buttonGroupPlec.add(radioButtonMezczyzna);
        buttonGroupPlec.add(radioButtonKobieta);
        String[] aktywnosc = {"Brak aktywności", "Niska aktywność", "Średnia aktywność", "Wysoka aktywność"};
        comboBoxAktywnosc = new JComboBox<>(aktywnosc);
        buttonObliczKalorie = new JButton("Oblicz");

        panelKalorie.add(labelWiek);
        panelKalorie.add(textFieldWiek);
        panelKalorie.add(labelPlec);
        panelKalorie.add(radioButtonMezczyzna);
        panelKalorie.add(new JLabel());
        panelKalorie.add(radioButtonKobieta);
        panelKalorie.add(labelAktywnosc);
        panelKalorie.add(comboBoxAktywnosc);
        panelKalorie.add(new JLabel());
        panelKalorie.add(buttonObliczKalorie);
        panelKalorie.add(new JLabel());
        panelKalorie.add(labelWynikKalorie);

        panelOProgramie = new JPanel();
        panelOProgramie.setLayout(new BorderLayout());
        labelAutor = new JLabel("Autor: s27118");
        labelAutor.setHorizontalAlignment(SwingConstants.CENTER);
        labelAutor.setFont(labelAutor.getFont().deriveFont(Font.BOLD));
        //[bold i size] labelAutor.setFont(labelAutor.getFont().deriveFont(Font.BOLD, 18));
        //[opis programu] labelOpisProgramu = new JLabel("O programie: Kalkulator do obliczania BMI i zapotrzebowania kalorycznego.");
        //[opis programu] labelOpisProgramu.setHorizontalAlignment(SwingConstants.CENTER);

        //dodanie logo i informacji o wersji programu
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BorderLayout());
        ImageIcon logo = new ImageIcon("pjatk.png");
        JLabel labelLogo = new JLabel(logo);
        labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel labelWersja = new JLabel("Wersja aplikacji: 1.1");
        labelWersja.setHorizontalAlignment(SwingConstants.CENTER);
        logoPanel.add(labelLogo, BorderLayout.CENTER);
        logoPanel.add(labelWersja, BorderLayout.SOUTH);

        panelOProgramie.add(logoPanel, BorderLayout.NORTH);
        panelOProgramie.add(labelAutor, BorderLayout.CENTER);
        //[opis programu] panelOProgramie.add(labelOpisProgramu, BorderLayout.SOUTH);
        //gorne zakladki programu
        tabbedPane.addTab("Kalkulator BMI", panelBMI);
        tabbedPane.addTab("Kalkulator Kalorii", panelKalorie);
        tabbedPane.addTab("O programie", panelOProgramie);

        buttonObliczBMI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String wagaText = textFieldWaga.getText();
                String wzrostText = textFieldWzrost.getText();

                if (wagaText.isEmpty() || wzrostText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Proszę wprowadzić wagę i wzrost.", "Błąd", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        double waga = Double.parseDouble(wagaText);
                        double wzrost = Double.parseDouble(wzrostText);

                        double bmi = obliczBMI(waga, wzrost);
                        String kategoria = okreslKategorie(bmi);

                        labelWynikBMI.setText(String.format("BMI: %.2f (%s)", bmi, kategoria));
                        zapiszDoPliku(String.format("[BMI: %.2f (%s]", bmi, kategoria + ") Waga: " + waga + " Wzrost: " + wzrost)); //zapis do pliku
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Wprowadzono nieprawidłowe dane.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        buttonObliczKalorie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String wiekText = textFieldWiek.getText();
                int plec = radioButtonMezczyzna.isSelected() ? 1 : 0;
                String aktywnosc = (String) comboBoxAktywnosc.getSelectedItem();

                if (wiekText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Proszę wprowadzić wiek.", "Błąd", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int wiek = Integer.parseInt(wiekText);
                        double zapotrzebowanieKaloryczne = obliczZapotrzebowanieKaloryczne(wiek, plec, aktywnosc);

                        labelWynikKalorie.setText(String.format("Zapotrzebowanie: %.2f kcal", zapotrzebowanieKaloryczne));
                        zapiszDoPliku(String.format("[Zapotrzebowanie kaloryczne: %.2f kcal, ", zapotrzebowanieKaloryczne) + aktywnosc + ", Wiek: " + wiek + "]"); //zapis do pliku
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Wprowadzono nieprawidłowe dane.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private double obliczBMI(double waga, double wzrost) { //obliczanie Bmi [wzor: waga/wzrost^2]
        double wzrostMetry = wzrost / 100.0;
        return waga / (wzrostMetry * wzrostMetry);
    }

    private String okreslKategorie(double bmi) {
        if (bmi < 18.5) {
            return "Niedowaga";
        } else if (bmi < 25) {
            return "Prawidłowa";
        } else if (bmi < 30) {
            return "Nadwaga";
        } else {
            return "Otyłość";
        }
    }

    private double obliczZapotrzebowanieKaloryczne(int wiek, int plec, String aktywnosc) {
        double zapotrzebowanieKaloryczne = 0;
        double wzrost = Double.parseDouble(textFieldWzrost.getText());
        double waga = Double.parseDouble(textFieldWaga.getText());

        //wzor Harrisa Benedicta – obliczanie zapotrzebowania kcal - podstawowa przemiana materii
        //PPM mezczyzna = 66,47 + (13,7 x masa ciała w kg) + (5 x wzrost w cm] – [6,76 x wiek w latach]
        //PPM kobiety = 655,1 + (9,567 x masa ciała w kg) + (1,85 x wzrost w cm) – (4,68 x wiek w latach)
        if (plec == 1) { //mezczyzna
            if (aktywnosc.equals("Brak aktywności")) {
                zapotrzebowanieKaloryczne = (66.5 + (13.75 * waga) + (5.003 * wzrost) - (6.755 * wiek));
            } else if (aktywnosc.equals("Niska aktywność")) {
                zapotrzebowanieKaloryczne = (66.5 + (13.75 * waga) + (5.003 * wzrost) - (6.755 * wiek)) * 1.2;
            } else if (aktywnosc.equals("Średnia aktywność")) {
                zapotrzebowanieKaloryczne = (66.5 + (13.75 * waga) + (5.003 * wzrost) - (6.755 * wiek)) * 1.4;
            } else if (aktywnosc.equals("Wysoka aktywność")) {
                zapotrzebowanieKaloryczne = (66.5 + (13.75 * waga) + (5.003 * wzrost) - (6.755 * wiek)) * 1.6;
            }
        } else { //kobieta
            if (aktywnosc.equals("Brak aktywności")) {
                zapotrzebowanieKaloryczne = (655.1 + (9.563 * waga) + (1.85 * wzrost) - (4.676 * wiek));
            } else if (aktywnosc.equals("Niska aktywność")) {
                zapotrzebowanieKaloryczne = (655.1 + (9.563 * waga) + (1.85 * wzrost) - (4.676 * wiek)) * 1.2;
            } else if (aktywnosc.equals("Średnia aktywność")) {
                zapotrzebowanieKaloryczne = (655.1 + (9.563 * waga) + (1.85 * wzrost) - (4.676 * wiek)) * 1.4;
            } else if (aktywnosc.equals("Wysoka aktywność")) {
                zapotrzebowanieKaloryczne = (655.1 + (9.563 * waga) + (1.85 * wzrost) - (4.676 * wiek)) * 1.6;
            }
        }
        return zapotrzebowanieKaloryczne;
    }
}
