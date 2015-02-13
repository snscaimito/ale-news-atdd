package alenews.content.analysis;

import alenews.content.acquisition.Content;
import alenews.content.acquisition.ContentConnection;
import alenews.content.db.ContentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class HTMLLinkFinder implements LinkFinder {
    private static final Log logger = LogFactory.getLog(HTMLLinkFinder.class);
    private final ContentService contentService;

    @Autowired
    public HTMLLinkFinder(ContentService contentService) {
        this.contentService = contentService ;
    }

    @Override
    public Content findDiscussionLinks(Content content) {
        ContentConnection contentConnection = new ContentConnection(content.getSourceLocation()) ;
        try {
            Document document = Jsoup.parse(contentConnection.getInputStream(), "utf-8", content.getSourceLocation().toExternalForm());

            Elements articleElements = document.select(String.format("h1:containsOwn(%s)", content.getTitle())) ;
            Elements assumedArticleElements ;

            if (articleElements.size() > 0)
                assumedArticleElements = articleElements.get(0).siblingElements().select("p") ;
            else
                assumedArticleElements = articleElements ;

            for (Element link : assumedArticleElements.select("a[href]")) {
                String linkTarget = link.attr("abs:href").toString();

                if (contentService.findByLocation(linkTarget) != null) {
                    try {
                        content.addDiscussionLink(new URL(linkTarget));
                    } catch (MalformedURLException e) {
                        logger.warn(String.format("Link %s malformed", link), e);
                    }
                }
            }

        } catch (IOException e) {
            logger.error(String.format("Trying to find outgoing links in %s", content.getSourceLocation().toExternalForm()), e);
        }

        return content;
    }

}
