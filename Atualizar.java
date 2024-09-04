import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 


public class Atualizar extends JFrame {
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
 private final JButton updateJButton = new JButton("");
 private final JLabel espacador1 = new JLabel("");
 private final JLabel espacador2 = new JLabel("");
 private final JLabel espacador3 = new JLabel("");
 private final JLabel espacador4 = new JLabel("");

 
 private String strNome;
 private String strEmail;
 
 private final String dbPadrao = "db_texte";
 private final String tblPadrao = "tbl_texte";

 public Atualizar()
 {
 super("Cadastrar");
 setLayout(new GridLayout(6, 2, 10, 10));

        JPanel linha1 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha2 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha3 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha4 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha5 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha6 = new JPanel(new GridLayout(1, 1, 5, 5));
 
        updateJButton.addActionListener(
          new ActionListener() {
              public void actionPerformed(ActionEvent event)
        {
        String nome;
        String email;
        String senha;
        String id;
        try
        {
          nome = nomeJTextField.getText();
          email = emailJTextField.getText();
          senha = senhaJPasswordField.getText();
          id = idJTextField.getText();
          NavegadorDeRegistro.updateRegistro("db_texte", "tbl_texte",id, nome, email, senha );
                        
          notificacaoJLabel.setText("Atualização realizada com sucesso");
        } 
        catch(Exception e){
          System.out.println("Digite alguma coisa.");
          return;
           }     
          }
         }
        );
        
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

       linha2.add(updateJButton);
       
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
       iniciarCampos();
       setVisible(true);
      }

      public void iniciarCampos() {
        try {
            String[] resultado = NavegadorDeRegistro.primeiroRegistro(dbPadrao, tblPadrao);
            notificacaoJLabel.setText("Primeio registro posicionado com sucesso");
            if (resultado != null) {
                idJTextField.setText(resultado[0]);
                nomeJTextField.setText(resultado[1]);
                strNome = nomeJTextField.getText();
                emailJTextField.setText(resultado[2]);
                strEmail = emailJTextField.getText();
                
            } else {
                notificacaoJLabel.setText("Já está no primeiro registro, por isso não é possível retroceder ao registro anterior.");
            }
        } catch(Exception e) {
            System.out.println("Ops! Ocorreu algum erro ao posicionar o registro para o primeiro. Veja o erro: " + e);
        }
    }

      
      public static void main(String[] args) {
        Atualizar application = new Atualizar();
        application.setDefaultCloseOperation(EXIT_ON_CLOSE);
      }
    }
   