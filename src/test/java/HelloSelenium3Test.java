import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HelloSelenium3Test {

    static Map<String, String> site = new HashMap<>();
    static Map<String, String> blog = new HashMap<>();

    static {
        //site.put("https://36kr.com", "a.article-item-title");
        //site.put("https://toutiao.io#true", "div.content > h3 > a");
        //site.put("https://www.williamlong.info#true", "a.post-title");
        //site.put("https://readhub.cn/topics#true", "div#itemList > div > div > h2 > a");
        //site.put("https://news.sogou.com/sci#true", "h4.new-title > a");
        //site.put("https://insights.thoughtworks.cn", "h4.entry-title > a");
        //site.put("https://insights.thoughtworks.cn/page/2/", "h4.entry-title > a");
        //site.put("https://www.tuicool.com/ah", "div.aricle_item_info > div > object > a");
        //site.put("https://www.tuicool.com/ah/0/1?lang=1", "div.aricle_item_info > div > object > a");
        //site.put("https://www.tuicool.com/books", "div.title > a");
        //site.put("https://www.ituring.com.cn/book", "div.book-info > h4 > a");
        //site.put("https://www.ituring.com.cn/book?tab=book&sort=hot&page=1", "div.book-info > h4 > a");
        //site.put("https://www.ituring.com.cn/book?tab=book&sort=hot&page=2", "div.book-info > h4 > a");
        //site.put("https://juejin.cn#true", "div.title-row > span > a");
        //site.put("https://www.oschina.net/news/industry#true", "div.news-item > div > h3 > a");
        //site.put("https://www.oschina.net/blog#true", "div.blog-item > div > a");
        //site.put("https://www.oschina.net/translate#true", "div.translate-item > div > a");
        //site.put("https://hackernoon.com", "div.title-wrapper > h2 > a ");
        //site.put("https://dzone.com", "h3.article-title > a");
        //site.put("https://juejin.cn/tag/%E6%8E%98%E9%87%91%E7%BF%BB%E8%AF%91%E8%AE%A1%E5%88%92#true", "div.title-row > span > a");
        //site.put("https://www.huxiu.com", "h5.article-item__content__title");
        //site.put("http://www.geekpark.net#true", "h3.multiline-text-overflow");
        //site.put("https://www.manning.com/meap-catalog", "div.title");
        site.put("https://www.ifanr.com", "a.js-title-transform");

        //for (int i = 2021; i > 2015; i--) { site.put("http://www.oreilly.com.cn/index.php?func=completelist&pubyear=" + i, "a.tip"); }


        //blog.put("https://afoo.me/posts.html", "h2 > a");
    }

    static int SCROLL_COUNT = 2;


    @Test
    public void smarterTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = ((JavascriptExecutor) driver);

        for (String key : site.keySet()) {


            driver.get(key);


            if (key.endsWith("true")) {

                for (int i = 0; i < SCROLL_COUNT; i++) {

                    Thread.sleep(2000);

                    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                }
            }


            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);


            Elements select = doc.select(site.get(key));
            for (Element element : select) {
                System.out.println(element.text());
            }

        }


        for (String key : blog.keySet()) {


            driver.get(key);


            if (key.endsWith("true")) {

                for (int i = 0; i < SCROLL_COUNT; i++) {

                    Thread.sleep(2000);

                    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                }
            }


            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);


            Elements select = doc.select(blog.get(key));
            for (Element element : select) {
                System.out.println(element.text());
            }

        }


        //wanqu(driver);


        driver.quit();
    }


    private void wanqu(WebDriver driver) {
        driver.get("https://wanqu.co/issues/");
        String wanqu = "wanqu-20210-2-8";


        List<WebElement> finds = driver.findElements(By.cssSelector("div.row > div > a"));

        int index = 1;

        for (int i = 0; i < finds.size(); i++) {

            finds = driver.findElements(By.cssSelector("div.row > div > a"));
            finds.get(i).click();

            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);
            Elements select = doc.select("h2.wq-header");


            for (Element element : select) {
                System.out.println(element.text());

                File file = new File(wanqu);
                if (!file.exists()) {
                    file.mkdir();
                }

                try {
                    FileWriter fileWriter = new FileWriter(new File(file, index + ".md"));
                    for (Element e : doc.select("div.lead > div > p")) {
                        fileWriter.append("#" + element.text());
                        fileWriter.append("\n");
                        fileWriter.append(e.text());
                        fileWriter.flush();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                index++;

            }


            driver.navigate().back();

        }
    }
}
