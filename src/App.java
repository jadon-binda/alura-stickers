import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // faz uma requisicao para a api do IMDB e obtem os dados
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // faz uma requisicao para a api da NASA e obtem os dados
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";

        var connection = new Connection();
        String jsonData = connection.dataSearch(url);
      
        // exibir e manipular os dados
        ContentExtractorIMDB extractor = new ContentExtractorIMDB();
        // ContentExtractorNasa extractor = new ContentExtractorNasa();
        List<Content> contents = extractor.contentExtractor(jsonData);

        var generator = new StickerGenerator();

        for (int i = 0; i < contents.size(); i++) {

            Content content = contents.get(i);
                   
            InputStream inputStream = new URL(content.getImageURL()).openStream();
            String nomeArquivo = "saida/" + content.getTitle() + ".png";
            
            generator.criaImagem(inputStream, nomeArquivo);

            System.out.println(content.getTitle());
            System.out.println();
 
        }

    }
}