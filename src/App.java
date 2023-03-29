import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma requisicao para a api e obter os top 250 filmes do IMDB
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair e converter os dados que interessam (title, image, imdbrating)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
      
        // exibir e manipular os dados
        var generator = new StickerGenerator();

        for (Map<String,String> filme : listaDeFilmes) {
            String ImageURL = filme.get("image");
            String titulo = filme.get("title");
            String nomeArquivo = titulo + ".png";
            InputStream inputStream = new URL(ImageURL).openStream();
            generator.criaImagem(inputStream, nomeArquivo);

            System.out.println(titulo);
            // System.out.println("\u001b[43;1m\u001b[37;1m" + titulo + "\u001b[0m");
            // System.out.println("\u001b[45;1m\u001b[37;1m" + filme.get("image") + "\u001b[0m");
            // System.out.println("\u001b[45;1m\u001b[37;1m" + filme.get("imDbRating") + "\u001b[0m");
            System.out.println();
        }

    }
}