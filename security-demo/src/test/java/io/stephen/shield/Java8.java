package io.stephen.shield;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhoushuyi
 * @since 2018/4/24
 */
public class Java8 {



    @Test
    public void java8date() {


        LocalDateTime date1 = LocalDateTime.now();


        date1.format(DateTimeFormatter.ISO_ORDINAL_DATE);



        System.out.println(date1);
    }
}
