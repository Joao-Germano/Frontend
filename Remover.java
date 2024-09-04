import java.sql.*;

public class Remover {
     public static String[] DeleteRegister(String db, String tbl, String campo, String campo2, String campo3, String nome,String email, String senha) throws Exception {
        String retorno = "Nada aconteceu ainda";
        try {
        Connection conexao = MySQLConnector.conectar();
        
            String strSqlDeleteRegister = "delete from `" + db + "`.`" + tbl + "` where `" + campo + "` = ? and`" + campo2 + "` = ? and`" + campo3 + "` = ?;";
                PreparedStatement rstDeleteRegister = conexao.prepareStatement(strSqlDeleteRegister);
                rstDeleteRegister.setString(1, nome);
                rstDeleteRegister.setString(2, email);
                rstDeleteRegister.setString(3, senha);
                int linhaRegistro = rstDeleteRegister.executeUpdate();
                rstDeleteRegister.close();
                conexao.close();
                if (linhaRegistro > 0) {
                    retorno = "Registro excluido com sucesso";
                }else {
                    retorno = "Nenhum registro encontrado";
                }
        } catch (Exception e) {
            retorno = "Ops ocorreu erro" + e;
            System.out.println(retorno);
        }
        return null;
    }       
}
