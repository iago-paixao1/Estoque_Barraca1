import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Aplicação principal para gerenciar o estoque usando JOptionPane para interação.
 */
public class GerenciamentoEstoqueApp {
    // Armazena todos os produtos cadastrados
    private static List<Produto> listaProdutos = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            String menu =  "=== Gerenciamento de Estoque ===\n" +
                    "1 - Cadastrar Produto\n" +
                    "2 - Listar Todos os Produtos\n" +
                    "3 - Pesquisar Produto por Código\n" +
                    "4 - Pesquisar Produto por Nome\n" +
                    "5 - Atualizar Quantidade\n" +
                    "6 - Atualizar Preço\n" +
                    "7 - Remover Produto\n" +
                    "8 - Listar Produtos com Estoque Baixo\n" +
                    "9 - Calcular Valor Total em Estoque\n" +
                    "10 - Ordenar Produtos por Nome\n" +
                    "0 - Sair\n\n" +
                    "Escolha uma opção:";
            String opcaoStr = JOptionPane.showInputDialog(menu);
            if (opcaoStr == null) break;  // Cancelou
            int opcao;
            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (NumberFormatException e) {
                continue;
            }

            switch (opcao) {
                case 1: cadastrarProduto(); break;
                case 2: listarTodosProdutos(); break;
                case 3: pesquisarPorCodigo(); break;
                case 4: pesquisarPorNome(); break;
                case 5: atualizarQuantidade(); break;
                case 6: atualizarPreco(); break;
                case 7: removerProduto(); break;
                case 8: listarEstoqueBaixo(); break;
                case 9: calcularValorTotalEstoque(); break;
                case 10: ordenarProdutosPorNome(); break;
                case 0: System.exit(0);
                default: break;
            }
        }
    }

    // 1. Cadastrar Produto
    private static void cadastrarProduto() {
        try {
            int codigo = Integer.parseInt(
                    JOptionPane.showInputDialog("Informe o código do produto:"));
            String nome = JOptionPane.showInputDialog("Informe o nome do produto:");
            int qtd = Integer.parseInt(
                    JOptionPane.showInputDialog("Informe a quantidade em estoque:"));
            double preco = Double.parseDouble(
                    JOptionPane.showInputDialog("Informe o preço do produto:"));

            // Verifica se código já existe
            for (Produto p : listaProdutos) {
                if (p.getCodigo() == codigo) {
                    JOptionPane.showMessageDialog(null,
                            "Já existe um produto com esse código.");
                    return;
                }
            }

            listaProdutos.add(new Produto(codigo, nome, qtd, preco));
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Tente novamente.");
        }
    }

    // 2. Listar Todos os Produtos
    private static void listarTodosProdutos() {
        if (listaProdutos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há produtos cadastrados.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Produto p : listaProdutos) {
            sb.append(p).append("\n-----------------------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // 3. Pesquisar Produto por Código
    private static void pesquisarPorCodigo() {
        try {
            int codigo = Integer.parseInt(
                    JOptionPane.showInputDialog("Informe o código a pesquisar:"));
            for (Produto p : listaProdutos) {
                if (p.getCodigo() == codigo) {
                    JOptionPane.showMessageDialog(null, p.toString());
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        } catch (Exception e) {
            // ignora
        }
    }

    // 4. Pesquisar Produto por Nome
    private static void pesquisarPorNome() {
        String termo = JOptionPane.showInputDialog(
                "Informe parte do nome para pesquisa:");
        if (termo == null || termo.isEmpty()) return;

        StringBuilder sb = new StringBuilder();
        for (Produto p : listaProdutos) {
            if (p.getNome().toLowerCase().contains(termo.toLowerCase())) {
                sb.append(p).append("\n-----------------------\n");
            }
        }
        String resultado = sb.length() > 0 ? sb.toString() : "Nenhum produto corresponde à pesquisa.";
        JOptionPane.showMessageDialog(null, resultado);
    }

    // 5. Atualizar Quantidade
    private static void atualizarQuantidade() {
        try {
            int codigo = Integer.parseInt(
                    JOptionPane.showInputDialog("Código do produto a atualizar:"));
            for (Produto p : listaProdutos) {
                if (p.getCodigo() == codigo) {
                    int novaQtd = Integer.parseInt(
                            JOptionPane.showInputDialog("Nova quantidade:"));
                    p.setQuantidade(novaQtd);
                    JOptionPane.showMessageDialog(null, "Quantidade atualizada.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        } catch (Exception e) {
            // ignora
        }
    }

    // 6. Atualizar Preço
    private static void atualizarPreco() {
        try {
            int codigo = Integer.parseInt(
                    JOptionPane.showInputDialog("Código do produto a atualizar:"));
            for (Produto p : listaProdutos) {
                if (p.getCodigo() == codigo) {
                    double novoPreco = Double.parseDouble(
                            JOptionPane.showInputDialog("Novo preço:"));
                    p.setPreco(novoPreco);
                    JOptionPane.showMessageDialog(null, "Preço atualizado.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        } catch (Exception e) {
            // ignora
        }
    }

    // 7. Remover Produto
    private static void removerProduto() {
        try {
            int codigo = Integer.parseInt(
                    JOptionPane.showInputDialog("Código do produto a remover:"));
            for (Produto p : listaProdutos) {
                if (p.getCodigo() == codigo) {
                    listaProdutos.remove(p);
                    JOptionPane.showMessageDialog(null, "Produto removido.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        } catch (Exception e) {
            // ignora
        }
    }

    // 8. Listar Produtos com Estoque Baixo
    private static void listarEstoqueBaixo() {
        try {
            int limite = Integer.parseInt(
                    JOptionPane.showInputDialog("Informe o limite de estoque:"));
            StringBuilder sb = new StringBuilder();
            for (Produto p : listaProdutos) {
                if (p.getQuantidade() < limite) {
                    sb.append(p).append("\n-----------------------\n");
                }
            }
            String resultado = sb.length() > 0 ? sb.toString() : "Nenhum produto com estoque abaixo de " + limite;
            JOptionPane.showMessageDialog(null, resultado);
        } catch (Exception e) {
            // ignora
        }
    }

    // 9. Calcular Valor Total em Estoque
    private static void calcularValorTotalEstoque() {
        double total = 0;
        for (Produto p : listaProdutos) {
            total += p.getPreco() * p.getQuantidade();
        }
        JOptionPane.showMessageDialog(null,
                "Valor total em estoque: R$ " + String.format("%.2f", total));
    }

    // 10. Ordenar Produtos por Nome
    private static void ordenarProdutosPorNome() {
        listaProdutos.sort(Comparator.comparing(Produto::getNome));
        JOptionPane.showMessageDialog(null, "Produtos ordenados por nome.");
    }
}