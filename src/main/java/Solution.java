import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;

import java.io.*;
import java.util.Date;

public class Solution {
    public static final String SLIME_MIDL = "Скол. средние:";
    public static final String TECH_IND = "Тех. индикаторы:";

    public static void main(String[] args) throws IOException, InterruptedException {

        //     System.out.println("Вводим адрес, если пусто выходим");
//        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader reader = new BufferedReader(new FileReader(rd.readLine()));
//        StringBuilder stringBuilder = new StringBuilder();
//        String line;
//
//
//        while ((line = reader.readLine()) != null){ª
//            stringBuilder.append(line);
//        }
//        rd.close();
//        reader.close();

        while (true) {
            FileWriter fileWriter = new FileWriter("monitor.txt", true);

            Document doc = Jsoup.connect("https://ru.investing.com/equities/microsoft-corp-technical").get();
            //Document doc = Jsoup.connect("https://ru.investing.com/equities/nikola-corp-chart").get();
            String title = doc.title();
            System.out.println("Title : " + title);
            // System.out.println(doc.body());
            // System.out.println(doc.charset());

            //Document document = Jsoup.parse("https://ru.investing.com/equities/microsoft-corp-technical", "https://ru.investing.com/equities/microsoft-corp-technical", Parser.htmlParser());
            Elements elem = doc.select("div.summaryTableLine"); //<div class="summaryTableLine"><span>Скол. средние:</span><span class="greenFont bold">Покупать</span><span><i class="noBold">Покупать</i> <i id="maBuy">(8)</i></span><span><i class="noBold">Продавать</i> <i id="maSell">(4)</i></span></div>
            String valueSlimeMidl = "";
            String valueTechMidl = "";

            for (Element elements : elem) {
                //System.out.println(elements);
                //System.out.println(elements);
                for (int i = 0; i < elements.childNodeSize(); i++) {
                    if (SLIME_MIDL.equals(elements.childNode(i).childNode(0).toString())) {
                        valueSlimeMidl = elements.childNode(i + 1).childNode(0).toString();
                    }

                    if (TECH_IND.equals(elements.childNode(i).childNode(0).toString())) {
                        valueTechMidl = elements.childNode(i + 1).childNode(0).toString();
                    }
                }
//            for (Element elemChild : elements.children()) {
//                if (elemChild.childNodeSize() > 0) {
//                    if (SLIME_MIDL.equals(elemChild.childNode(0)) {
//
//                    }
//
//
//                }
//
//            }
            }

            Date date = new Date();
            fileWriter.write(date + " | Скользящее среднее: " + valueSlimeMidl + " | Техноиндикаторы: " + valueTechMidl + "\n");
            fileWriter.flush();
            fileWriter.close();
//            System.out.println("Скользящее среднее: " + valueSlimeMidl);
//            System.out.println("Техноиндикаторы: " + valueTechMidl);

            Thread.sleep(300_000);
        }
    }
}
