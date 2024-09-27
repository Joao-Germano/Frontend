import javax.swing.*;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
//import java.io.*;

public class DeleteFile {
    public static void main(String[] args) throws Exception {
        try {
            JFileChooser chooser = new JFileChooser();
          
            chooser.setDialogTitle("Selecione o arquivo para deletar.");
            int returnVal1 = chooser.showOpenDialog(null);
            String fileFullPath = "";
            String fileName = "";
            if(returnVal1 == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
                fileFullPath = chooser.getSelectedFile().getAbsolutePath();
                fileName = chooser.getSelectedFile().getName();
                System.out.println("File Full Path: " + fileFullPath);
            } else {
                System.out.println("Que pena!");
            }

            chooser.setDialogTitle("Selecione a pasta de destino.");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal2 = chooser.showOpenDialog(null);
            String folderFullPath = "";
            if(returnVal2 == JFileChooser.APPROVE_OPTION) {
                folderFullPath = chooser.getSelectedFile().getAbsolutePath();
                System.out.println("You chose to open this folder: " + chooser.getSelectedFile().getName());
                System.out.println("Folder Full Path: " + chooser.getSelectedFile().getAbsolutePath());
            } else {
                System.out.println("Que pena!");
            }

            Path pathOrigin = Paths.get(fileFullPath);
            Path pathDestination = Paths.get(folderFullPath + "\\" + fileName);
            Files.copy(pathOrigin, pathDestination, REPLACE_EXISTING);
            Files.delete(pathDestination);
        } catch (Exception e) {
            System.out.println("Não foi possível deletar o arquivo.");
        }
    }
}

