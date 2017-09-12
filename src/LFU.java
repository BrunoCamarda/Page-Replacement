
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class LFU {
    Pagina[] p;
    int falhas;
    int[] contador;

    public LFU(Pagina[] p, int[] contador) {
        this.p = p;
        this.falhas = 0;
        this.contador = contador;
    }

    //metodo que exibe os valores das paginas
    void exibe() {
        for (Pagina p1 : p) {
            if (p1.valor != -200) { //nao exibe nada nas primeiras iteracoes
                System.out.print(p1.valor + " ");
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    
    /*
    Metodo que encontra qual pagina deve ser alterada. 
    Primeiro o metodo encontra o menor valor no vetor de 
    contador e o salva em "MENOR"
    Em seguida, 2 situacoes: 
    1) caso nao haja valores repetidos de "MENOR" (cont = 1), apenas 
    retorna a posicao onde "MENOR" foi encontrado. 
    2) caso haja valores repetidos de "MENOR" (cont > 1) o metodo
    usa a lista "aux" onde se encontram os indices das paginas com os 
    menores valores de contador (repetidos).  
    Para esses valores onde o contador eh repetido, encontra 
    e retorna a posicao onde o valor da linha da pagina eh o menor
    (COMO SE FOSSE UM ALGORITMO DE LRU)
    */
    
    public int menosAcessado() {
        int menor = 100;
        int j = 0, i;
        int minimo = 100;
        int[] aux;
        int cont = 0;
        aux = new int[p.length];
        for (i = 0; i < p.length; i++) {
            if (contador[i] < menor) {
                menor = contador[i];
                j = i;
            }
        }
        for (i = 0; i < p.length; i++) {
            if (contador[i] == menor) {
                aux[i] = 1;
                cont++;
            }
        }
        if (cont > 1) {
            for (i = 0; i < p.length; i++) {
                if (aux[i] == 1) {
                    if (p[i].linha < minimo) {
                        minimo = p[i].linha;
                        j = i;
                    }
                }
            }
        } else {
            return j;
        }
        return j;
    }

    /* 
    Metodo principal da classe: 
    Existem duas ocasioes: 
    1) Caso o numero ja esteja em alguma das paginas, 
    o contador referente aquela pagina eh incrementado em 1
    e o numero da linha da pagina recebe um valor vindo de Leitura 
    
    2) caso o numero nao esteja, chama o metado menosAcessado
    para encontrar qual a pagina que deve ser alterada. Feito isso
    altera o valor, o contador e o numero da linha da pagina. Alem 
    de incrementar o numero de falhas.
    */
    
    public void Lfu(String linha, int pos) {
        int numero = Integer.parseInt(linha);
        for (int i = 0; i < p.length; i++) {
            if (numero == p[i].valor) {
                contador[i]++;
                p[i].linha = pos;
                return;

            }
        }
        int posicao = menosAcessado();
        p[posicao].valor = numero;
        contador[posicao] = 1;
        p[posicao].linha = pos;
        falhas++;
    }
    
    /* 
    metodo que faz a leitura linha-a-linha do arquivo
    e chama a funcao Lfu e a exibe logo em seguida. 
    Ao final, exibe o numero de falhas
    */
     public void Leitura(String caminho) throws IOException {
        String linha;
        int i = 0, j = 0;
        try {
            FileReader f = new FileReader(caminho);
            BufferedReader g = new BufferedReader(f);
            while (g.ready()) {
                linha = g.readLine();
                j++;
                Lfu(linha, j);
                exibe();  
            }
            System.out.println("\nFalhas: " + falhas);
            g.close();
        } catch (FileNotFoundException ex) {
        }
    }
    
    
}
