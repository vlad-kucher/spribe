package vlad.kucher.spribe.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static vlad.kucher.spribe.util.WordUtil.checkWord;

/**
 * Created by Vlad on 07.11.2017.
 */
@Service
public class WordCounterService {
    private Map<String, Integer> map = new ConcurrentHashMap<>();

    public void addWord(String word) {
        checkWord(word);
        map.merge(word.toLowerCase(), 1, Integer::sum);
    }

    public int getWordCount(String word) {
        checkWord(word);
        return map.getOrDefault(word.toLowerCase(), 0);
    }

    public void clear(){
        map.clear();
    }
}
