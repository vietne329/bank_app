package com.bank_management.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestDate {

    @Test
    public void testDay() throws ParseException {
//        String time1 = "16/05/2021";
//        String time2 = "16/06/2021";
//
//        SimpleDateFormat  df = new SimpleDateFormat("dd/MM/yyyy");
//
//        Date date1 = df.parse(time1);
//        Date date2 = df.parse(time2);
//
//        long difference = date2.getTime() - date1.getTime(); // milis
//
//        System.out.println(difference/ 86400000);


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }
}
