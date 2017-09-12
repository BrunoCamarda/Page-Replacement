
public class Pagina {
    int linha; //linha do arquivo onde foi referenciada
    int valor; //valor lido do arquivo
    int referencia; // 0 ou 1, mas sempre comeca com 1 
    
    public Pagina(int valor, int linha){ 
        this.linha = linha; 
        this.valor = valor;
    }
    
    
}
