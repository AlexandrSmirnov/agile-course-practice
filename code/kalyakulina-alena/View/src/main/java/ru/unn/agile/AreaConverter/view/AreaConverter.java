package ru.unn.agile.AreaConverter.view;

import ru.unn.agile.AreaConverter.model.AreaMeasure;
import ru.unn.agile.AreaConverter.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class AreaConverter {

    private ViewModel viewModel;

    private JPanel mainPanel;
    private JTextField inputArea;
    private JComboBox from;
    private JButton convertButton;
    private JComboBox to;
    private JTextField resultArea;
    private JTextField status;

    private AreaConverter() { }

    private AreaConverter(final ViewModel viewModel) {
        this.viewModel = viewModel;

        loadListOfMeasures();
        backBind();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                viewModel.convert();
                backBind();
            }
        });

        from.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        to.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                viewModel.parseInput();
                backBind();
            }
        };

        inputArea.addKeyListener(keyListener);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("AreaConverter");
        frame.setContentPane(new AreaConverter(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void loadListOfMeasures() {
        AreaMeasure[] measures = AreaMeasure.values();
        from.setModel(new JComboBox<>(measures).getModel());
        to.setModel(new JComboBox<>(measures).getModel());
    }

    private void bind() {
        viewModel.setInputArea(inputArea.getText());

        viewModel.setFrom((AreaMeasure) from.getSelectedItem());
        viewModel.setTo((AreaMeasure) to.getSelectedItem());
    }

    private void backBind() {
        convertButton.setEnabled(viewModel.isConvertButtonEnabled());

        resultArea.setText(viewModel.getResultArea());
        status.setText(viewModel.getStatus());
    }
}
