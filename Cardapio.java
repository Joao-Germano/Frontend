import java.util.*;

public class Cardapio {
    private static String strogonoff;
    private static String peixe;
    private static String lasanha;
    private static String hambúrguer;
   
    public static void main(String[]args) {
        System.out.println("Opcões 1, 2, 3, 4");
        Scanner scntempo = new Scanner(System.in);
        int opcoes = scntempo.nextInt();
        switch (opcoes) {
            case 1:
            System.out.println("Strogonof");
            System.out.println("40 minutos");
                break;
            case 2:
            System.out.println("Peixe");
            System.out.println("20 minutos");
                break;
            case 3:
            System.out.println("Lasanha");
            System.out.println("50 minutos");
                break;
            case 4:
            System.out.println("Hambúrguer");
            System.out.println("1 Hora");
                break;
            default:
             System.out.println("Não existe essa opção você está errado");
                break;
        }
        scntempo.close();
    }
}
