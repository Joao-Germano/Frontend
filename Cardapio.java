import java.util.*;

public class Cardapio {
    
    public static void main(String[]args) {
        System.out.println("Opcões 1, 2, 3, 4, 5, 6");
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
            case 5:
                System.out.println("Batata frita");
                System.out.println("10 minutos");
                    break;
            case 6:
                 System.out.println("Salada");
                 System.out.println("10 minutos");
                 System.out.println("É sério isso");
                        break;
            default:
             System.out.println("Não existe essa opção você está errado");
                break;
        }
        scntempo.close();
    }
}
