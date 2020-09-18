import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class poemWordCounter {
    public static void main(String[] args) throws IOException {
        //Gathers Poem from a URL
        Document Poem = Jsoup.connect("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").get();

        //Separates the poem portion of the page and turns it into a String
        Elements poemBody = Poem.select("p");
        String lit = new String(String.valueOf(poemBody));

        //Removes Remaining HTML tags and punctuation impeding word counting and then makes the string all lower case.
        String iter1 = lit.replaceAll("<br>", "");
        String iter2 = iter1.replaceAll("</p>", "");
        String iter3 = iter2.replaceAll("<p class=\"poem\">", "");
        String iter4 = iter3.replaceAll("<span style=\"margin-left: 20%\">", "");
        String iter5 = iter4.replaceAll("</span> ", "");
        String iter6 = iter5.replaceAll("<i>", "");
        String iter7 = iter6.replaceAll("</i>", "");
        String iter8 = iter7.replaceAll("�", " ");
        String iter9 = iter8.replaceAll("!", "");
        String iter10 = iter9.replaceAll("\"", "");
        String iter11 = iter10.replaceAll("\n", " ");
        String iter12 = iter11.replaceAll(",", "");
        String iter13 = iter12.replaceAll(";", "");
        String iter14 = iter13.replaceAll("\\.", "");
        String iter15 = iter14.replaceAll("'", "");
        String iter16 = iter15.toLowerCase();

        //Invokes the word counting method to count the words of the newly transformed string.
        poemWordCounter countWordTest = new poemWordCounter();
        countWordTest.wordCounter(iter16);

    }

    //Method to count the words in the string
    public void wordCounter(String input) {
        //Create a String linkedhashmap to store words and values
        Map <String, String> map = new LinkedHashMap<String, String>();

        // Establish Separate words with split method
        if (input != null) {
            String[] separatedWords = input.split(" ");

            //Loop to check if the map already contains the word and to adjust list accordingly
            for (String word: separatedWords) {

                if (map.containsKey(word)) {
                    int count = Integer.parseInt(map.get(word));
                    map.put(word, String.valueOf(count + 1));

                } else {
                    map.put(word, "1");
                }
            }




        }

        //Removes character from the map I couldn't figure out how to make the counter not count it
        map.keySet().removeIf(key -> key.contentEquals(""));
        //converts previous map to integer(made it easier to sort)
        Map<String, Integer> hashmap = map.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> Integer.parseInt(entry.getValue())));
        //Converts new map to a list and sorts it by value from greatest to least
        List list = new ArrayList(hashmap.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        //prints result of method.
        System.out.println("Word Count: " + list);
    }

}