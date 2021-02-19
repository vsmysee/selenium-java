import com.google.gson.Gson;
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
import java.util.*;


public class HelloSelenium3Test {

    static int SCROLL_COUNT = 2;


    static Map<String, String> site = new LinkedHashMap<>();
    static Map<String, String> blog = new LinkedHashMap<>();

    static void news() {

        site.put("https://36kr.com", "a.article-item-title");
        site.put("https://www.pedaily.cn/", "div.txt > h3 > a");
        site.put("https://www.huxiu.com", "h5.article-item__content__title");
        site.put("https://www.ifanr.com", "a.js-title-transform");
        site.put("http://www.iheima.com", "a.title");
        site.put("https://www.donews.com/", "div.content > span.title");
        site.put("https://www.cnbeta.com/", "div.item > dl > dt > a");
        site.put("https://jishuin.proginn.com/", "div.article-title > a");
        site.put("http://www.cbdio.com/node_2570.htm", "p.cb-media-title > a");
        site.put("http://www.woshipm.com/", "h2.post-title > a");
        site.put("http://www.kejilie.com", "h3.am_list_title > a");
        site.put("https://www.geeksforgeeks.org/", "div.content > div.head > a");
        site.put("https://z.itpub.net/", "li.has-img > h4");


        site.put("https://toutiao.io#true", "div.content > h3 > a");
        site.put("https://www.williamlong.info#true", "a.post-title");
        site.put("https://readhub.cn/topics#true", "div#itemList > div > div > h2 > a");
        site.put("https://news.sogou.com/sci#true", "h4.new-title > a");
        site.put("https://insights.thoughtworks.cn", "h4.entry-title > a");
        site.put("https://insights.thoughtworks.cn/page/2/", "h4.entry-title > a");
        site.put("https://www.tuicool.com/ah", "div.aricle_item_info > div > object > a");
        site.put("https://www.tuicool.com/ah/0/1?lang=1", "div.aricle_item_info > div > object > a");
        site.put("https://juejin.cn#true", "div.title-row > span > a");
        site.put("https://www.oschina.net/news/industry#true", "div.news-item > div > h3 > a");
        site.put("https://www.oschina.net/blog#true", "div.blog-item > div > a");
        site.put("https://www.oschina.net/translate#true", "div.translate-item > div > a");
        site.put("https://dzone.com", "h3.article-title > a");
        site.put("https://juejin.cn/tag/%E6%8E%98%E9%87%91%E7%BF%BB%E8%AF%91%E8%AE%A1%E5%88%92#true", "div.title-row > span > a");
        site.put("http://www.geekpark.net#true", "h3.multiline-text-overflow");
        site.put("https://blog.google/products/android/", "a > section > h3");
        site.put("https://www.jdon.com", "h3.vid-name > a");
        site.put("http://ifeve.com/", "h3.title > a");
        site.put("http://ifeve.com/page/2/", "h3.title > a");
        site.put("https://segmentfault.com/news", "h4.news__item-title");
        site.put("https://www.tmtpost.com/lists/latest_list_new", "li.part_post > div.info > a > h3");
        site.put("https://www.infoworld.com", "div.post-cont > h3 > a");
        site.put("https://www.cnblogs.com", "a.post-item-title");
        site.put("https://developer.ibm.com/zh/articles/", "h3.developer--card__title > span");
        site.put("https://www.qbitai.com/", "div.text_box > h4 > a");
        site.put("https://www.cncf.io/blog/", "p.archive-title > a");
        site.put("https://thzt.github.io/archives/", "h2.post-title > a  > span");
        site.put("https://draveness.me/", "article > a");
        site.put("https://blog.jetbrains.com/idea/category/releases/", "article > h3");
        site.put("https://hackernoon.com", "div.title-wrapper > h2 > a ");

    }

    static void books() {

        site.put("https://www.epubit.com/books", "div.list-title");
        site.put("https://www.tuicool.com/books", "div.title > a");
        site.put("https://www.ituring.com.cn/book", "div.book-info > h4 > a");
        site.put("https://www.ituring.com.cn/book?tab=book&sort=hot&page=1", "div.book-info > h4 > a");
        site.put("https://www.ituring.com.cn/book?tab=book&sort=hot&page=2", "div.book-info > h4 > a");
        site.put("https://www.manning.com/meap-catalog", "div.title");

        for (int i = 2021; i > 2015; i--) {
            site.put("http://www.oreilly.com.cn/index.php?func=completelist&pubyear=" + i, "a.tip");
        }

    }


    static void blogs() {

        blog.put("https://afoo.me/posts.html", "h2 > a");
        blog.put("http://www.yinwang.org/", "li.title > a");
        blog.put("https://manateelazycat.github.io/index.html", "a.post-title");
        blog.put("https://spring.io/blog/category/releases", "h2.blog--title > a");
        blog.put("https://spring.io/blog?page=2", "h2.blog--title > a");
        blog.put("https://netflixtechblog.com", "h3");
        blog.put("https://amazonaws-china.com/cn/blogs/china/", "h2.blog-post-title");
        blog.put("https://amazonaws-china.com/cn/blogs/china/page/2/", "h2.blog-post-title");
        blog.put("http://zhangyi.xyz/", "h1.post-title > a");
        blog.put("http://zhangyi.xyz/page/2/", "h1.post-title > a");
        blog.put("http://blog.devtang.com", "a.abstract-title");
        blog.put("http://blog.devtang.com/page/2/", "a.abstract-title");
        blog.put("https://onevcat.com/", "h1 > a");
        blog.put("https://www.topjavablogs.com/", "a.itemLink");
        blog.put("https://pingcap.com/blog-cn/", "h1.title > a");
        blog.put("https://blog.codingnow.com/", "h3.entry-header");
        blog.put("https://coolshell.cn", "h2.entry-title > a");
        blog.put("https://blogs.360.cn/", "h1.title > a");
        blog.put("https://codechina.org/", "ul.wp-block-latest-posts > li > a");
        blog.put("https://aijishu.com/", "h3.text-body");
        blog.put("https://tech.meituan.com/", "h2.post-title > a");


    }


    static {

        //news();
        //blogs();
        //books();

        site.put("https://jishuin.proginn.com/", "div.article-title > a");


    }


    @Test
    public void smarterTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = ((JavascriptExecutor) driver);

        Map<String, List<String>> map = new HashMap<>();
        map.put("blog", new ArrayList<>());
        map.put("news", new ArrayList<>());

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
                String text = element.text();
                if (text.equals("")) {
                    continue;
                }
                text = text.trim().replaceAll("\n", "");

                map.get("news").add(text);

                System.out.println(text);
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
                String text = element.text();
                if (text.equals("")) {
                    continue;
                }
                text = text.trim().replaceAll("\n", "");
                map.get("blog").add(text);

                System.out.println(text);
            }

        }


        //wanqu(driver, map);



        try {
            FileWriter fileWriter = new FileWriter(new File("data.json"));
            fileWriter.append(new Gson().toJson(map));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        driver.quit();
    }


    static void wanqu(WebDriver driver, Map<String, List<String>> map) {
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
                String text = element.text();
                System.out.println(text);

                map.get("blog").add(text);


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
