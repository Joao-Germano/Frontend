import javax.swing.*;
import java.nio.file.*;
import java.io.*;

public class FileDeleteTexte {
    public static void main(String[] args) {
        try {
            
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Selecione o arquivo ou pasta que deseja deletar.");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 

            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                String fileFullPath = selectedFile.getAbsolutePath();
                System.out.println("Você escolheu deletar: " + selectedFile.getName());
                System.out.println("Caminho Completo: " + fileFullPath);

                int confirmation = JOptionPane.showConfirmDialog(null, 
                        "Tem certeza que deseja deletar " + selectedFile.getName() + "?", 
                        "Confirmação", 
                        JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                   
                    Path path = selectedFile.toPath();
                    Files.delete(path);
                    System.out.println("Arquivo ou pasta deletado com sucesso!");
                } else {
                    System.out.println("Deleção cancelada.");
                }
            } else {
                System.out.println("Nenhum arquivo ou pasta selecionado.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao tentar deletar o arquivo ou pasta: " + e.getMessage());
        }
    }
}
