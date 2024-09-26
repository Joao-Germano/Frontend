import javax.swing.*;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;

public class FileMove {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        
        
        chooser.setDialogTitle("Selecione o arquivo que deseja mover.");
        int returnVal1 = chooser.showOpenDialog(null);
        String fileFullPath = "";
        String fileName = "";
        
        if(returnVal1 == JFileChooser.APPROVE_OPTION) {
            fileFullPath = chooser.getSelectedFile().getAbsolutePath();
            fileName = chooser.getSelectedFile().getName();
            System.out.println("Você escolheu o arquivo: " + fileName);
            System.out.println("Caminho completo do arquivo: " + fileFullPath);
        } else {
            System.out.println("Operação cancelada ao selecionar o arquivo.");
            return;
        }

        chooser.setDialogTitle("Selecione a pasta de destino.");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal2 = chooser.showOpenDialog(null);
        String folderFullPath = "";
        
        if(returnVal2 == JFileChooser.APPROVE_OPTION) {
            folderFullPath = chooser.getSelectedFile().getAbsolutePath();
            System.out.println("Você escolheu a pasta: " + chooser.getSelectedFile().getName());
            System.out.println("Caminho completo da pasta: " + folderFullPath);
        } else {
            System.out.println("Operação cancelada ao selecionar a pasta.");
            return;  
        }

        try {
            Path pathOrigin = Paths.get(fileFullPath);
            Path pathDestination = Paths.get(folderFullPath, fileName);
            Files.move(pathOrigin, pathDestination, REPLACE_EXISTING);
            System.out.println("Arquivo movido com sucesso para: " + pathDestination.toString());
        } catch (Exception e) {
            System.out.println("Não foi possível mover o arquivo: " + e.getMessage());
        }
    }
}

          