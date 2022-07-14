import java.util.Map;
import java.util.TreeMap;

public class CountOccurrenceOfWords {
    public static void main(String[] args){
        String text = "happy luck luck happy happy,aurora life";

        Map<String,Integer> map = new TreeMap<>();
        String[] words = text.split("[\\s+\\p{P}]");//从文本中提取单词，使用空格\s 或者 标点\p{P}作为分隔符
        for(int i =0;i<words.length;i++){
            String key = words[i].toLowerCase();

            if(key.length() > 0){
                if(!map.containsKey(key)){
                    map.put(key,1);
                }
                else{
                    int value = map.get(key);
                    value++;
                    map.put(key,value);
                }
            }
        }
        map.forEach((k,v) -> System.out.println(k + "\t" + v ));
    }
}
