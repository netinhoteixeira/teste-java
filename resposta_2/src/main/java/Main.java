// Francisco Ernesto Teixeira (fco.ernesto@gmail.com)

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Questao 2
 * =========
 * Escreva um método que recebe uma lista de strings como parâmetro. Essa lista possui uma série de nomes próprios,
 * podendo conter nomes repetidos. O método deve retornar um objeto com o número de ocorrências por nome, com a contagem
 * seguindo a ordem alfabética dos nomes. A contagem de ocorrências por nome deve ser case-insensitive e deve ignorar
 * acentuação (“João” e “jOao” são considerados como sendo o mesmo nome). Não utilize funções Lambda.
 * <p>
 * Exemplo de entrada: {“Pedro”, “João”, “Maria”, “JOAO”, “Alberto”, “João”, “MARiA”}
 * Saída esperada: {“ALBERTO” = 1, “JOAO” = 3, “MARIA” = 2, “PEDRO” = 1}
 */
public class Main {

    public static void main(String[] args) {
        String[] entrada = new String[]{"Pedro", "João", "Maria", "JOAO", "Alberto", "João", "MARiA"};
        Map<String, Integer> saida = contarFrequencia(entrada);

        // Opcional. Somente para fins de apresentação:
        apresentarSaida(saida);
    }

    public static Map<String, Integer> contarFrequencia(String[] strings) {
        Map<String, Integer> frequencia = new HashMap<>();

        for (int i = 0; i < strings.length; i++) {
            String s = removerAcentuacao(strings[i]).toUpperCase();

            if (frequencia.containsKey(s)) {
                frequencia.put(s, frequencia.get(s) + 1);
            } else {
                frequencia.put(s, 1);
            }
        }

        // Referência: https://stackabuse.com/how-to-sort-a-hashmap-by-key-in-java
        Map<String, Integer> ordenado = new TreeMap<>(frequencia);

        return ordenado;
    }

    // Referência: https://gist.github.com/rponte/893494
    public static String removerAcentuacao(String s) {
        return Normalizer
                .normalize(s, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    public static void apresentarSaida(Map<String, Integer> saida) {
        if (!saida.isEmpty()) {
            System.out.println("Contagem dos textos:");

            Object[] chaves = saida.keySet().toArray();
            for (int i = 0; i < chaves.length; i++) {
                String chave = (String) chaves[i];
                Integer valor = saida.get(chave);

                System.out.println(" - \"" + chave + "\" = " + valor);
            }
        } else {
            System.out.println("Nao existem textos contados a serem apresentados.");
        }
    }

}
