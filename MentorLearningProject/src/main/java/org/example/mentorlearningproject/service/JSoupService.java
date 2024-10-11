package org.example.mentorlearningproject.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JSoupService {

//    @Scheduled(fixedRate = 3000)
//    public void getData() throws IOException {
//        Document doc = Jsoup.connect("https://bina.az/baki/alqi-satqi/menziller/yeni-tikili").get();
//
//        Elements elementsByClass = doc.getElementsByClass("items_list");
//        for (Element element : elementsByClass) {
//            Element name = element.getElementsByClass("name").get(0);
//            System.out.println(name.text());
//        }
//    }

    public List<String> scrapeData() throws IOException {
        List<String> resultList = new ArrayList<>();

        String url = "https://bina.az/baki/alqi-satqi/menziller/yeni-tikili";
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.getElementsByClass("items_list");

        for (Element element : elements) {
            String name = element.getElementsByClass("name").text();
            resultList.add(name);
        }

        return resultList;
    }

}
