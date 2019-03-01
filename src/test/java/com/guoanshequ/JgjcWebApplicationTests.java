package com.guoanshequ;

import com.guoanshequ.utils.CSVUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.net.ftp.*;
import org.apache.commons.net.tftp.TFTPClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;
//import java.time.LocalDate;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JgjcWebApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
//        Arrays.asList(applicationContext.getBeanDefinitionNames()).forEach((name) -> {
//            System.out.printf("name: %s \t type: %s \n", name, applicationContext.getBean(name).getClass().getTypeName());
//        });
    }

    @Test
    public void testCsvWriter() {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter("csv.txt"), CSVFormat.DEFAULT.withQuoteMode(QuoteMode.ALL))) {
            printer.printRecord("id", "userName", "firstName", "lastName", "birthday");
//            printer.printRecord(1, "john73", "John", "Doe", LocalDate.of(1973, 9, 15));
//            printer.printRecord(2, "mary", "Mary", "Meyer", LocalDate.of(1985, 3, 29));
            printer.printRecord(2, "mary", "Mary", "Meyer", "'1234567890'");
            printer.printRecord(2, "名字", "Mary", "Meyer", "'1234567890'");
            printer.printRecord(2, "mary", "Mary", "Meyer", "yyyy-MM-dd");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testCsvUtil() {
        String[] a = {"a","b"};
        CSVUtil.createCsvFile("aaa.csv",new String[]{"aa","名字"},Arrays.asList(a,a));

    }

    @Test
    public void testProxyFtp() {
        FTPClient ftpClient = new FTPClient();
//        FTPClient ftpClient = new FTPHTTPClient("10.10.30.1", 3128);
        FTPClientConfig config = new FTPClientConfig();
        ftpClient.configure(config );
        boolean error = false;
        try {
            int reply;
            String server = "10.16.31.241";
            ftpClient.connect(server);
            ftpClient.login("daishuhua", "abc123");
            System.out.printf("Connected to %s .", server);
            System.out.printf("%s \n", ftpClient.getReplyString());

            // After connection attempt, you should check the reply code to verify
            // success.
            reply = ftpClient.getReplyCode();

            if(!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }

            // transfer files
            FileInputStream fileInputStream=new FileInputStream(new File("csv.txt"));
            ftpClient.changeWorkingDirectory("/var/ftp/pub/");
            boolean b = ftpClient.storeFile("csv.txt", fileInputStream);

            System.out.printf("upload result: %s.\n", b);
            fileInputStream.close();

            ftpClient.logout();
        } catch(IOException e) {
            error = true;
            e.printStackTrace();
        } finally {
            if(ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch(IOException ioe) {
                    // do nothing
                }
            }
            System.exit(error ? 1 : 0);
            System.out.printf("error: %s\n", error);

        }
    }

    @Test
    public void validEncode(){
        System.out.println("中文乱码了");
    }


}
