package vlad.kucher.spribe.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static vlad.kucher.spribe.TestData.WORD;

/**
 * Created by Vlad on 07.11.2017.
 */
public class WordCounterServiceTest extends AbstractServiceTest {

    @Autowired
    private WordCounterService service;

    @Before
    public void setUp() throws Exception {
        service.clear();
    }

    @Test
    public void testAddWord() throws Exception {
        int count = service.getWordCount(WORD);
        service.addWord(WORD);
        assertEquals(service.getWordCount(WORD), ++count);
    }

    @Test
    public void testGetWordCount() throws Exception {
        assertEquals(service.getWordCount(WORD), 0);
        service.addWord(WORD);
        assertEquals(service.getWordCount(WORD), 1);
    }

    @Test
    public void testIgnoringCase() throws Exception {
        assertEquals(service.getWordCount(WORD), 0);
        service.addWord("Test");
        service.addWord("TEST");
        service.addWord("test");
        assertEquals(service.getWordCount(WORD), 3);
    }

    @Test
    public void testConcurrency() throws Exception {
        int threadCount = 100;
        int addCount = 10000;

        List<Thread> threads = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < addCount; j++) {
                    service.addWord(WORD);
                }
            }));
        }

        threads.forEach(Thread::start);

        for (Thread t : threads) {
            t.join();
        }

        assertEquals(service.getWordCount(WORD), threadCount*addCount);
    }

    @Test
    public void testInvalidWords() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid word");
        service.addWord("42");

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid word");
        service.addWord(" ");

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid word");
        service.addWord("?$%&#№");
    }

    @Test
    public void testNullWord() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Word must not be null");
        service.addWord(null);
    }
}