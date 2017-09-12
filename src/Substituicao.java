
import java.io.IOException;
import java.util.Scanner;

public class Substituicao {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String caminho; //caminho para o arquivo de entrada de dados
        int numero_quadros;  //numero de maximo de quadros
        System.out.print("Informe o numero de paginas: ");
        numero_quadros = in.nextInt();
        in.nextLine();
        System.out.print("Informe o caminho do arquivo com a sequencia: ");
        caminho = in.nextLine();
        Pagina[] p = new Pagina[numero_quadros];
        Pagina[] p2 = new Pagina[numero_quadros];
        int[] contador = new int[numero_quadros]; //vetor contador usado no LFU

        for (int i = 0; i < numero_quadros; i++) {
            p[i] = new Pagina(-200, 1000); //inicia a linha com 1000 apenas que as primeiras iteracoes sejam validas
            p2[i] = new Pagina(-200, 0);
            contador[i] = -1;
        }
        System.out.println("Substituicao LFU: ");
        LFU lfu = new LFU(p, contador);
        lfu.Leitura(caminho);
        System.out.println("\nAlgoritmo da Segunda Chance: ");
        SegundaChance segunda = new SegundaChance(p2);
        segunda.Leitura(caminho);

    }
}
