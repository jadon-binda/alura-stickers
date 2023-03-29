import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorNasa {
    
    public List<Content> contentExtractor (String jsonData) {
        
        // extrai e converte os dados que interessam
        var parser = new JsonParser();
        List<Map<String, String>> collection = parser.parse(jsonData);

        List<Content> contents = new ArrayList<>();
            
        // popular a lista
            for(Map<String,String> itens : collection) {
                String title = itens.get("title");
                String imageURL = itens.get("url");

                var content = new Content(title, imageURL);

                contents.add(content);
            }      

        return contents;       

        }
}