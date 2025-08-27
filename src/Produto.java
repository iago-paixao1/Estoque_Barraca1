/**
 * Representa um produto no sistema de estoque.
 */
public class Produto {
    private int codigo;       // identificador único do produto
    private String nome;      // nome do produto
    private int quantidade;   // quantidade em estoque
    private double preco;     // preço do produto

    // Construtor
    public Produto(int codigo, String nome, int quantidade, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Representação em String para exibição
    @Override
    public String toString() {
        return "Código: " + codigo +
                "\nNome: " + nome +
                "\nQuantidade: " + quantidade +
                "\nPreço: R$ " + String.format("%.2f", preco);
    }
}