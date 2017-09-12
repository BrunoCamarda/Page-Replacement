
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class SegundaChance {
    Pagina[] p; 
    int falhas; 
    int primeiro_a_sair; 
    
    public SegundaChance(Pagina[] p){ 
        this.p = p; 
        this.falhas = 0;
        this.primeiro_a_sair = 0; //informa qual pagina sera testada a referencia primeiro
    }
    
    /* 
    metodo que reseta (altera para zero) o valor
    do primeiro a sair, caso ele seja maior
    que o numero de paginas.
    */
     public int resetaPrimeiro() {
        if (primeiro_a_sair > p.length - 1) {
            primeiro_a_sair = 0;
        }
        return primeiro_a_sair;
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
    Metodo principal da classe. segundaChance.
    Existem duas ocasioes: 
    1) se o numero a ser inserido ja estiver na pagina
    altera seu bit de referencia para 1.
    2) se  o numero a ser inserido nao estiver na pagina
    testa na posicao 'primeiro_a_sair' o bit de referencia: 
    2.1) caso seja 1, troca para 0 e incrementa 'primeiro_a_sair.
    2.2) caso seja 0, altera o valor da pagina, o bit de referencia,
    o primeiro_a_sair eh incrementado.
    O controlador flag eh usado para saber quando ele 
    encontra o primeiro bit de referencia igual a zero.
    */
    
    public void segundaChance(String linha) {
        int numero = Integer.parseInt(linha);
        int flag = 0;
        for (Pagina p1 : p) {
            if (numero == p1.valor) {
                p1.referencia = 1;
                return;
            }
        }
        while (flag != 1) {
            if (p[primeiro_a_sair].referencia == 1) {
                p[primeiro_a_sair].referencia = 0;
                primeiro_a_sair++;
                resetaPrimeiro();
            } else {
                p[primeiro_a_sair].valor = numero;
                p[primeiro_a_sair].referencia = 1;
                falhas++;
                primeiro_a_sair++;
                resetaPrimeiro();
                flag = 1;
            }
        }
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
                segundaChance(linha);
                exibe();
                }
            
            System.out.println("\nFalhas: " + falhas);
            g.close();
        } catch (FileNotFoundException ex) {
        }
    }
 
     
}
