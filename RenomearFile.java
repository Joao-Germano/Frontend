import javax.swing.*;
import java.nio.file.*;

public class RenomearFile {
    public static void main(String[] args) {
        try {
            JFileChooser chooser = new JFileChooser();
        
            chooser.setDialogTitle("Selecione o arquivo que deseja renomear.");
            int returnVal1 = chooser.showOpenDialog(null);
            String fileFullPath = "";
            if (returnVal1 == JFileChooser.APPROVE_OPTION) {
                System.out.println("Você escolheu abrir: " + chooser.getSelectedFile().getName());
                fileFullPath = chooser.getSelectedFile().getAbsolutePath();
                System.out.println("Caminho Completo: " + fileFullPath);
            } else {
                System.out.println("Que pena!");
                return; 
            }

            String newName = JOptionPane.showInputDialog("Digite o novo nome (sem extensão):");
            if (newName == null || newName.trim().isEmpty()) {
                System.out.println("Nome inválido, operação cancelada.");
                return;
            }

            Path originalPath = Paths.get(fileFullPath);
            Path parentDir = originalPath.getParent();
            String newFileName = newName + (getFileExtension(originalPath).isEmpty() ? "" : "." + getFileExtension(originalPath));
            Path newPath = parentDir.resolve(newFileName);

            Files.move(originalPath, newPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Arquivo renomeado com sucesso para: " + newPath.toString());
        } catch (Exception e) {
            System.out.println("Não foi possível renomear o arquivo: " + e.getMessage());
        }
    }

    private static String getFileExtension(Path path) {
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
