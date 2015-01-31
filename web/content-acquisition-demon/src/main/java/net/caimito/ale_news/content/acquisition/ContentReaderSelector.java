package net.caimito.ale_news.content.acquisition;

public class ContentReaderSelector {

    public static ContentReader selectReader(ContentType contentType) {
        switch (contentType) {
            case HTML:
                return new HTMLContentReader() ;
            default:
                throw new RuntimeException(String.format("Unknown content type %s", contentType)) ;
        }
    }
}
