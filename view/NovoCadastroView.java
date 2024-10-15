package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import model.MySQLConnectorModel;

public class NovoCadastroView extends JFrame {
    private final JLabel nomeJLabel = new JLabel("Digite um nome:", SwingConstants.RIGHT);
    private final JTextField nomeJTextField = new JTextField();

    private final JLabel emailJLabel = new JLabel("Digite um email:", SwingConstants.RIGHT);
    private final JTextField emailJTextField = new JTextField();

    private final JLabel senhaJLabel = new JLabel("Digite uma senha:", SwingConstants.RIGHT);
    private final JPasswordField senhaJPasswordField = new JPasswordField();

    private final JButton cadastrarJButton = new JButton("Cadastrar");
    private final JButton selecionarImagemJButton = new JButton("Selecionar Imagem");

    private final JLabel notificacaoJLabel = new JLabel("Notificações...", SwingConstants.CENTER);
    private final JLabel labelImagem = new JLabel(); 

    private String caminhoImagem; 

    private final String dbPadrao = "db_texte";
    private final String tblPadrao = "tbl_texte";

    public NovoCadastroView() {
        super("Novo Cadastro");
        setLayout(new BorderLayout(10, 10)); 

        labelImagem.setHorizontalAlignment(JLabel.CENTER); 
        labelImagem.setPreferredSize(new Dimension(200, 200)); 
        add(labelImagem, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 5, 5)); 

     
        formPanel.add(createLinePanel(nomeJLabel, nomeJTextField));
        formPanel.add(createLinePanel(emailJLabel, emailJTextField));
        formPanel.add(createLinePanel(senhaJLabel, senhaJPasswordField));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(cadastrarJButton);
        buttonPanel.add(selecionarImagemJButton);
        formPanel.add(buttonPanel);

        JPanel notificationPanel = new JPanel(new FlowLayout());
        notificationPanel.add(notificacaoJLabel);
        formPanel.add(notificationPanel);

        add(formPanel, BorderLayout.CENTER); 

        cadastrarJButton.addActionListener(event -> {
            try {
                Connection conexao = MySQLConnectorModel.conectar();
                
                String strSqlPesquisarEmail = "SELECT * FROM `" + dbPadrao + "`.`" + tblPadrao + "` WHERE `email` = ?";
                try (PreparedStatement pst = conexao.prepareStatement(strSqlPesquisarEmail)) {
                    pst.setString(1, emailJTextField.getText());
                    ResultSet rstPesquisarEmail = pst.executeQuery();
                    if (rstPesquisarEmail.next()) {
                        notificacaoJLabel.setText("Ops! Esse email já está cadastrado.");
                        return;
                    }
                }

                String strSqlCadastrarRegistro = "INSERT INTO `" + dbPadrao + "`.`" + tblPadrao + "` (`nome`, `email`, `senha`, `foto`) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pst = conexao.prepareStatement(strSqlCadastrarRegistro)) {
                    pst.setString(1, nomeJTextField.getText());
                    pst.setString(2, emailJTextField.getText());
                    pst.setString(3, String.valueOf(senhaJPasswordField.getPassword()));

                    if (caminhoImagem != null) {
                        pst.setString(4, caminhoImagem); 
                    } else {
                        pst.setNull(4, Types.VARCHAR);
                    }

                    pst.executeUpdate();
                    notificacaoJLabel.setText("Cadastro realizado com sucesso!");
                }
                
                dispose(); 

            } catch (SQLException e) {
                notificacaoJLabel.setText("Não foi possível realizar o cadastro. Por favor, verifique e tente novamente.");
                System.out.println("Erro: " + e);
            }
        });

        selecionarImagemJButton.addActionListener(e -> selecionarImagem());

        setSize(500, 500);
        setLocationRelativeTo(null); 
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private JPanel createLinePanel(JLabel label, JTextField textField) {
        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private void selecionarImagem() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione a imagem");
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            caminhoImagem = fileChooser.getSelectedFile().getAbsolutePath();
            exibirImagem(caminhoImagem);
        }
    }

    private void exibirImagem(String caminhoImagem) {
        ImageIcon imagemIcon = new ImageIcon(caminhoImagem);
        Image imagemRedimensionada = imagemIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        labelImagem.setIcon(new ImageIcon(imagemRedimensionada));
        labelImagem.repaint();
    }

    public static void main(String[] args) {
        new NovoCadastroView();
    }
}
