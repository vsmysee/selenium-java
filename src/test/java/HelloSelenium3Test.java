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
import java.text.SimpleDateFormat;
import java.util.*;


public class HelloSelenium3Test {

    static int SCROLL_COUNT = 2;

    static boolean test = true;


    static Map<String, String> newsLink = new LinkedHashMap<>();
    static Map<String, String> articleLink = new LinkedHashMap<>();
    static Map<String, String> blogLink = new LinkedHashMap<>();
    static Map<String, String> bookLink = new LinkedHashMap<>();

    static void news() {

        newsLink.put("https://36kr.com", "a.article-item-title");
        newsLink.put("https://www.pedaily.cn/", "div.txt > h3 > a");
        newsLink.put("https://www.huxiu.com", "h5.article-item__content__title");
        newsLink.put("https://www.ifanr.com", "a.js-title-transform");
        newsLink.put("http://www.iheima.com", "a.title");
        newsLink.put("https://www.donews.com/", "div.content > span.title");
        newsLink.put("https://www.cnbeta.com/", "div.item > dl > dt > a");
        newsLink.put("https://jishuin.proginn.com/", "div.article-title > a");
        newsLink.put("http://www.cbdio.com/node_2570.htm", "p.cb-media-title > a");
        newsLink.put("http://www.woshipm.com/", "h2.post-title > a");
        newsLink.put("http://www.kejilie.com", "h3.am_list_title > a");
        newsLink.put("https://www.geeksforgeeks.org/", "div.content > div.head > a");
        newsLink.put("https://z.itpub.net/", "li.has-img > h4");

    }

    static void article() {

        articleLink.put("https://www.infoq.cn/themes#true", "h6.title > a");
        articleLink.put("https://toutiao.io#true", "div.content > h3 > a");
        articleLink.put("https://www.williamlong.info#true", "a.post-title");
        articleLink.put("https://readhub.cn/topics#true", "div#itemList > div > div > h2 > a");
        articleLink.put("https://news.sogou.com/sci#true", "h4.new-title > a");
        articleLink.put("https://insights.thoughtworks.cn", "h4.entry-title > a");
        articleLink.put("https://insights.thoughtworks.cn/page/2/", "h4.entry-title > a");
        articleLink.put("https://www.tuicool.com/ah", "div.aricle_item_info > div > object > a");
        articleLink.put("https://www.tuicool.com/ah/0/1?lang=1", "div.aricle_item_info > div > object > a");
        articleLink.put("https://juejin.cn#true", "div.title-row > span > a");
        articleLink.put("https://www.oschina.net/news/industry#true", "div.news-item > div > h3 > a");
        articleLink.put("https://www.oschina.net/blog#true", "div.blog-item > div > a");
        articleLink.put("https://www.oschina.net/translate#true", "div.translate-item > div > a");
        articleLink.put("https://dzone.com", "h3.article-title > a");
        articleLink.put("https://juejin.cn/tag/%E6%8E%98%E9%87%91%E7%BF%BB%E8%AF%91%E8%AE%A1%E5%88%92#true", "div.title-row > span > a");
        articleLink.put("http://www.geekpark.net#true", "h3.multiline-text-overflow");
        articleLink.put("https://blog.google/products/android/", "a > section > h3");
        articleLink.put("https://www.jdon.com", "h3.vid-name > a");
        articleLink.put("http://ifeve.com/", "h3.title > a");
        articleLink.put("http://ifeve.com/page/2/", "h3.title > a");
        articleLink.put("https://segmentfault.com/news", "h4.news__item-title");
        articleLink.put("https://www.tmtpost.com/lists/latest_list_new", "li.part_post > div.info > a > h3");
        articleLink.put("https://www.infoworld.com", "div.post-cont > h3 > a");
        articleLink.put("https://www.cnblogs.com", "a.post-item-title");
        articleLink.put("https://developer.ibm.com/zh/articles/", "h3.developer--card__title > span");
        articleLink.put("https://www.qbitai.com/", "div.text_box > h4 > a");
        articleLink.put("https://www.cncf.io/blog/", "p.archive-title > a");
        articleLink.put("https://thzt.github.io/archives/", "h2.post-title > a  > span");
        articleLink.put("https://draveness.me/", "article > a");
        articleLink.put("https://blog.jetbrains.com/idea/category/releases/", "article > h3");
        articleLink.put("https://hackernoon.com", "div.title-wrapper > h2 > a ");

    }

    static void books() {

        bookLink.put("https://www.epubit.com/books", "div.list-title");
        bookLink.put("https://www.tuicool.com/books", "div.title > a");
        bookLink.put("https://www.ituring.com.cn/book", "div.book-info > h4 > a");
        bookLink.put("https://www.ituring.com.cn/book?tab=book&sort=hot&page=1", "div.book-info > h4 > a");
        bookLink.put("https://www.ituring.com.cn/book?tab=book&sort=hot&page=2", "div.book-info > h4 > a");
        bookLink.put("https://www.manning.com/meap-catalog", "div.title");

        for (int i = 2021; i > 2015; i--) {
            bookLink.put("http://www.oreilly.com.cn/index.php?func=completelist&pubyear=" + i, "a.tip");
        }

    }


    static void blogs() {

        blogLink.put("https://afoo.me/posts.html", "h2 > a");
        blogLink.put("http://www.yinwang.org/", "li.title > a");
        blogLink.put("https://manateelazycat.github.io/index.html", "a.post-title");
        blogLink.put("https://spring.io/blog/category/releases", "h2.blog--title > a");
        blogLink.put("https://spring.io/blog?page=2", "h2.blog--title > a");
        blogLink.put("https://netflixtechblog.com", "h3");
        blogLink.put("https://amazonaws-china.com/cn/blogs/china/", "h2.blog-post-title");
        blogLink.put("https://amazonaws-china.com/cn/blogs/china/page/2/", "h2.blog-post-title");
        blogLink.put("http://zhangyi.xyz/", "h1.post-title > a");
        blogLink.put("http://zhangyi.xyz/page/2/", "h1.post-title > a");
        blogLink.put("http://blog.devtang.com", "a.abstract-title");
        blogLink.put("http://blog.devtang.com/page/2/", "a.abstract-title");
        blogLink.put("https://onevcat.com/", "h1 > a");
        blogLink.put("https://www.topjavablogs.com/", "a.itemLink");
        blogLink.put("https://pingcap.com/blog-cn/", "h1.title > a");
        blogLink.put("https://blog.codingnow.com/", "h3.entry-header");
        blogLink.put("https://coolshell.cn", "h2.entry-title > a");
        blogLink.put("https://blogs.360.cn/", "h1.title > a");
        blogLink.put("https://codechina.org/", "ul.wp-block-latest-posts > li > a");
        blogLink.put("https://aijishu.com/", "h3.text-body");
        blogLink.put("https://tech.meituan.com/", "h2.post-title > a");
        blogLink.put("https://baotiao.github.io/", "h3.post-title");
        blogLink.put("https://skyao.io/post/", "h3.article-title");
        blogLink.put("https://yongyuan.name/blog/", "span.article");
        blogLink.put("http://zhangtielei.com/", "h2 > a");
        blogLink.put("https://liujiacai.net/", "a.post-link");
        blogLink.put("http://duanple.com/", "h2 > a");
        blogLink.put("https://colobu.com/", "a.article-title");
        blogLink.put("http://blog.fnil.net/", "h1.entry-title > a");
        blogLink.put("https://www.503error.com/", "h1.entry-title > a");
        blogLink.put("https://mccxj.github.io/", "div.nav > ul > li > a");
        blogLink.put("https://blog.cleancoder.com/", "ul > li > a");
        blogLink.put("https://blog.wangqi.love/", "a.post-title-link");
        blogLink.put("http://arthurchiao.art/index.html", "a.post-link");
        blogLink.put("https://encrt.com/allpost/", "td.td-left > a");
        blogLink.put("http://www.softwareishard.com/blog/", "h2 > a");
        blogLink.put("https://www.amusinganalyst.com/", "h3 > a");
        blogLink.put("https://tech.glowing.com/cn/", "h2.post-card-title");
        blogLink.put("http://blueskykong.com/", "h1.post-title > a");
        blogLink.put("http://itindex.net/", "h2 > a");
        blogLink.put("https://www.cnblogs.com/xiexj/", "a.postTitle2");
        blogLink.put("https://tech.youzan.com/", "h2.post-title > a");
        blogLink.put("https://lotabout.me/", "ul > li > a");
        blogLink.put("https://twobithistory.org/", "div.post-header > h1 > a.title-link");
        blogLink.put("https://einverne.github.io/archive.html", "div.page-header > ul > li > a");
        blogLink.put("https://happypeter.github.io/", "ul.posts > li > a");
        blogLink.put("https://huang-jerryc.com/archives/", "a.post-title");
        blogLink.put("https://www.duyidong.com/", "h2.post-title > a");
        blogLink.put("http://deliberate-software.com/page/post/", "li.sidebar_li > a");
        blogLink.put("https://thorstenball.com/blog/", "td > a");
        blogLink.put("http://www.plainionist.net/", "h1.post-title > a");
        blogLink.put("https://cnblogs.com/zhengyun_ustc/", "a.postTitle2");
        blogLink.put("https://www.raychase.net/", "h2.entry-title > a");
        blogLink.put("https://begriffs.com/", "li.row > a");
        blogLink.put("https://boyter.org/", "body > ul > li > a");
        blogLink.put("http://www.skywind.me/blog/", "h2 > a.title");
        blogLink.put("https://tonsky.me/", "div.post > p > a");
        blogLink.put("https://freemind.pluskid.org/archive/", "article > h1 > a");
        blogLink.put("http://www.zreading.cn/", "h2.block-title > a");
        blogLink.put("https://qcrao.com/archives/", "ul.listing > li > a");
        blogLink.put("https://maintao.com/", "div > a");
        blogLink.put("https://blog.wutj.info/", "h3.entry-title > a");
        blogLink.put("https://www.barretlee.com/blog/archives/", "div.cate-detail > ul > li > a");
        blogLink.put("https://blog.youxu.info/archive.html", "div.well > ul > li > a");
        blogLink.put("https://martin.kleppmann.com/archive.html", "div#content > ul > li > a");


    }


    static {

        news();
        article();
        blogs();
        books();

    }


    @Test
    public void smarterTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = ((JavascriptExecutor) driver);

        List<String> book = new ArrayList<>();
        List<String> news = new ArrayList<>();
        List<String> article = new ArrayList<>();
        List<String> blog = new ArrayList<>();

        getData(driver, js, news, newsLink);

        getData(driver, js, article, articleLink);

        getData(driver, js, blog, blogLink);

        getData(driver, js, book, bookLink);


        wanqu(driver, blog);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String day = format.format(new Date());


        saveData(news, day, "news");
        saveData(article, day, "articles");
        saveData(blog, day, "blogs");
        saveData(book, day, "books");


        driver.quit();
    }

    private void getData(WebDriver driver, JavascriptExecutor js, List<String> article, Map<String, String> articleLink) throws InterruptedException {
        for (String key : articleLink.keySet()) {


            driver.get(key);


            if (key.endsWith("true")) {

                for (int i = 0; i < SCROLL_COUNT; i++) {

                    Thread.sleep(2000);

                    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                }
            }


            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);


            Elements select = doc.select(articleLink.get(key));
            for (Element element : select) {
                String text = element.text();
                if (text.equals("")) {
                    continue;
                }
                text = text.trim().replaceAll("\n", "");

                article.add(text);

            }

            if (test) {
                break;
            }

        }
    }

    private void saveData(List<String> list, String day, String key) {
        try {
            FileWriter fileWriter = new FileWriter(new File(day + "-" + key + ".json"));
            fileWriter.append(new Gson().toJson(list));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void wanqu(WebDriver driver, List<String> list) {
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

                list.add(text);


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

            if (test) {
                break;
            }

        }
    }
}
