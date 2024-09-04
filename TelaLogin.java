import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*; 


public class TelaLogin extends JFrame {
    private final JLabel loginJLabel = new JLabel("Login",SwingConstants.CENTER);
    private final JTextField loginJTextField = new JTextField();
    private final JLabel senhaJLabel = new JLabel("Senha",SwingConstants.CENTER);
    private final JPasswordField senhaJPasswordField = new JPasswordField();
    private final JButton cadastrarJButton = new JButton("Cadastro");
    private final JButton loginJButton = new JButton("Login");
    private final JCheckBox jCheckBox = new JCheckBox("Aceitar os termos");
    private final JLabel notificacaoJLabel = new JLabel("Notificações...");
    private final String dbPadrao = "db_texte";
    private final String tblPadrao = "tbl_texte";


    public TelaLogin()
 {
 super("Tela Login");
 setLayout(new GridLayout(7, 1, 10, 10));

 JPanel linha1 = new JPanel(new GridLayout(1, 1, 5, 5));
 JPanel linha2 = new JPanel(new GridLayout(1, 1, 5, 5));
 JPanel linha3 = new JPanel(new GridLayout(1, 1, 5, 5));
 JPanel linha4 = new JPanel(new GridLayout(1, 1, 5, 5));
 JPanel linha5 = new JPanel(new GridLayout(1, 2, 5, 5));
 JPanel linha6 = new JPanel(new GridLayout(1, 1, 5, 5));
 JPanel linha7 = new JPanel(new GridLayout(1, 1, 5, 5));

 loginJButton.addActionListener(
    new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            try {
                if (loginJTextField.getText().trim().length() > 0) {
                    Connection conexao = MySQLConnector.conectar();
                    String strSqlLogin = "select * from `" + dbPadrao + "`.`" + tblPadrao + "`" + " where `nome` = '" + loginJTextField.getText() + "' and `senha` = '" + String.valueOf(senhaJPasswordField.getPassword()) + "';";
                    Statement stmSqlLogin = conexao.createStatement();
                    ResultSet rstSqlLogin = stmSqlLogin.executeQuery(strSqlLogin);
                    if (rstSqlLogin.next()) {
                        MenuDesafio appMenuDesafio = new MenuDesafio();
                        appMenuDesafio.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    } else {
                        notificacaoJLabel.setText("Login ou Senha incorreto");
                    }
                }
            } catch (Exception e) {
                notificacaoJLabel.setText(" Login encontrado com sucesso " + e);
            }
        }
    }
);

cadastrarJButton.addActionListener(
    new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            setVisible(false);
            LoginCadastro appLoginCadastro = new LoginCadastro();
            appLoginCadastro.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
);

 linha2.add(loginJTextField);
 linha1.add(loginJLabel);
 add(linha1);
 add(linha2);

 linha4.add(senhaJPasswordField);
 linha3.add(senhaJLabel);
 linha5.add(loginJButton);
 linha5.add(cadastrarJButton);
 add(linha3);
 add(linha4);
 add(linha5);

 linha6.add(jCheckBox);
 linha7.add(notificacaoJLabel);
 add(linha6);
 add(linha7);
       
 setSize(300, 400);
 setVisible(true);
 }
 public static String setHtmlFormat(String strText) {
   return "<html><body>" + strText + "<body><html>";
 }

 public static TelaLogin appTelaLogin;
 public static void main(String[] args) {
    TelaLogin application = new TelaLogin();
    application.setDefaultCloseOperation(EXIT_ON_CLOSE);
 }
}
