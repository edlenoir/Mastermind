import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
        ModeleMastermind m = new ModeleMastermind();
        m.genererCombinaison();
        System.out.println("Combinaison à trouver " + m);
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez la premiere valeur");
        int v1 = sc.nextInt();
        System.out.println("Entrez la deuxieme valeur");
        int v2 = sc.nextInt();
        System.out.println("Entrez la troisieme valeur");
        int v3 = sc.nextInt();
        System.out.println("Entrez la quatrieme valeur");
        int v4 = sc.nextInt();
        int[] c = {v1,v2,v3,v4};
        System.out.println("Nombre de chiffres bien places :" + m.nbChiffresBienPlaces(c));
        System.out.println("Nombre de chiffres mal places :" + m.nbChiffresMalPlaces(c));
        System.out.println("combinaison finale" + m);
        while (m.nbChiffresBienPlaces(c) != 4) {
            System.out.println("----------------------------------");
            System.out.println("Entrez la premiere valeur");
            v1 = sc.nextInt();
            System.out.println("Entrez la deuxieme valeur");
            v2 = sc.nextInt();
            System.out.println("Entrez la troisieme valeur");
            v3 = sc.nextInt();
            System.out.println("Entrez la quatrieme valeur");
            v4 = sc.nextInt();
            System.out.println(m.getCombinaison());
            c[0] = v1;
            c[1] = v2;
            c[2] = v3;
            c[3] = v4;
            System.out.println("Nombre de chiffres bien places :" + m.nbChiffresBienPlaces(c));
            System.out.println("Nombre de chiffres mal places :" + m.nbChiffresMalPlaces(c));
            System.out.println("Combinaison à trouver " + m);
        }
        System.out.println("Vous avez gagné!");
        sc.close();
    }

}