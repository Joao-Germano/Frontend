import java.sql.*;

public class Pesquisar {
     public  static String[] verRegistro(String db, String tbl,String campo1,String campo2,String campo3, String nome, String email) {
            String retorno = "Nada aconteceu ainda...";
            String resultado[] = new String[4];
                try {
                    Connection conexao = MySQLConnector.conectar();
                    String strSqlVerRegistro = "SELECT " + campo1 + ", " + campo2 + ", " + campo3 + " FROM " + db + "." + tbl + " WHERE " + campo1 + " = '" + nome + "' OR " + campo2 + " = '" + email + "'";
                    Statement stmSqlVerRegistro = conexao.createStatement();
                    ResultSet resultSet = stmSqlVerRegistro.executeQuery(strSqlVerRegistro);

                    if(resultSet.next()){
                        // resultado[0] = resultSet.getString("id");
                        resultado[0] = resultSet.getString("nome");
                        resultado[1] = resultSet.getString("email");
                        resultado[2] = resultSet.getString("senha");
                    }
                    stmSqlVerRegistro.close();
                    retorno = ("Pesquisa realizada com sucesso !");
                    System.out.println(retorno);
              } catch (Exception e) {
                System.out.println(e);
                System.out.println("Error" + retorno);
                retorno = "texte";
                resultado[0] = "Erro";
                resultado[1] = "Erro";
                resultado[2] = "Erro";
                resultado[3] = "Erro";
              }
            return resultado;
        }
            
}
