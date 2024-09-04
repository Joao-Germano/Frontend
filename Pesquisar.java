import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 


public class Pesquisar extends JFrame {
 private final JTextField idJTextField = new JTextField();
 private final JTextField nomeJTextField = new JTextField();
 private final JTextField emailJTextField = new JTextField();
 private final JPasswordField senhaJPasswordField = new JPasswordField();
 private final JButton nextJButton = new JButton(">");
 private final JButton previousJButton = new JButton("<");
 private final JButton firstJButton = new JButton("<<");
 private final JButton lastJButton = new JButton(">>");
 private final JLabel idJLabel = new JLabel("Id:");
 private final JLabel nomeJLabel = new JLabel("Nome:");
 private final JLabel emailJLabel = new JLabel("Email:");
 private final JLabel senhaJLabel = new JLabel("Senha:");
 private final JLabel notificacaoJLabel = new JLabel("Notificações...");
 private final JButton verJButton = new JButton("");
 private final JLabel espacador1 = new JLabel("");
 private final JLabel espacador2 = new JLabel("");
 private final JLabel espacador3 = new JLabel("");
 private final JLabel espacador4 = new JLabel("");

 
 private String strNome;
 private String strEmail;
 
 private final String dbPadrao = "db_texte";
 private final String tblPadrao = "tbl_texte";

 public Pesquisar()
 {
 super("Cadastrar");
 setLayout(new GridLayout(6, 2, 10, 10));

        JPanel linha1 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha2 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha3 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha4 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha5 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha6 = new JPanel(new GridLayout(1, 1, 5, 5));
 

verJButton.addActionListener(new ActionListener() {
  public void actionPerformed(ActionEvent event) {
      String nome;
      String email;
      String[] resultado;
      try {
          nome = nomeJTextField.getText();
          email = emailJTextField.getText();
          resultado = NavegadorDeRegistro.verRegistro("db_texte","tbl_texte","nome", "email","senha", nome, email);

        nomeJTextField.setText(resultado[0]);
        emailJTextField.setText(resultado[1]);
        senhaJPasswordField.setText(resultado[2]);
        lastJButton.setEnabled(false);
        nextJButton.setEnabled(false);
        firstJButton.setEnabled(false);
        previousJButton.setEnabled(false);
       
       

          notificacaoJLabel.setText("Pesquisa realizada com sucesso");
      } catch (Exception e) {

          notificacaoJLabel.setText("Erro ao realizar a pesquisa: " + e);
      }
  }
});

       linha1.add(idJLabel);
       linha1.add(idJTextField);
       add(linha1);
       
       idJTextField.setEditable(false);
       
       add(espacador1);
       linha2.add(nomeJLabel);
       linha2.add(nomeJTextField);
       add(linha2);
      
       add(espacador2);
       linha3.add(emailJLabel);
       linha3.add(emailJTextField);
       add(linha3);

       linha2.add(verJButton);
       
       add(espacador3);
       linha4.add(senhaJLabel);
       linha4.add(senhaJPasswordField);
       add(linha4);

         add(espacador4);
       linha5.add(firstJButton);
       linha5.add(previousJButton);
       linha5.add(nextJButton);
       linha5.add(lastJButton);
       add(linha5);

       linha6.add(notificacaoJLabel);
       add(linha6);
       
       
       
       setSize(600, 300);
       
       setVisible(true);
      }

      
      
      public static void main(String[] args) {
      Pesquisar application = new Pesquisar();
        application.setDefaultCloseOperation(EXIT_ON_CLOSE);
      }
    }
   