import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorIMDB {

    public List<Content> contentExtractor (String jsonData) {

        var parser = new JsonParser();
        List<Map<String,String>> collection = parser.parse(jsonData);
        
        List<Content> contents = new ArrayList<>();

        for(Map<String,String> itens : collection) {

            String title = itens.get("title");
            String imageURL = itens.get("image");

            var content = new Content(title, imageURL);

            contents.add(content);          
        }
        return contents;
    }    
}