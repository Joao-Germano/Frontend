import java.sql.*;

public class Atualizar {
    public static String updateRegistro(String db, String tbl, String id, String nome, String email, String senha) throws Exception {
        String retorno = "Oi";
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlUltimoRegistro = "update `" + db + "`.`" + tbl + "` set `nome` = '" + nome + "', `email` = '" + email + "', `senha` = '" + senha + "' where `id` = " + id + ";";
            Statement stmSqlUltimoRegistro = conexao.createStatement();
            stmSqlUltimoRegistro.addBatch(strSqlUltimoRegistro);
            stmSqlUltimoRegistro.executeBatch();
            stmSqlUltimoRegistro.close();
            return retorno;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
