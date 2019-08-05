package com.seal.comman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringcloudCommanApplicationTests {

    @Test
    public void contextLoads() {
        File file = new File(getClass().getResource("E:/text.txt").getFile());
        BufferedReader reader;
        String text = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                text += line.trim() + "\n";
            }
            reader.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(text);
    }

}
