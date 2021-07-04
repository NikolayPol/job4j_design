package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        boolean a = false;
        char b = '1';
        byte c = 2;
        short d = 3;
        int e = 4;
        long f = 5;
        float g = 6;
        double h = 7;
        LOG.debug("LogOut: a : {}, b : {}, c : {}, d : {}, e : {}, f : {}, g : {}, h : {}",
                a, b, c, d, e, f, g, h);
        LOG.debug(String.valueOf(a));
        LOG.debug(String.valueOf(b));
        LOG.debug(String.valueOf(c));
        LOG.debug(String.valueOf(d));
        LOG.debug(String.valueOf(e));
        LOG.debug(String.valueOf(f));
        LOG.debug(String.valueOf(g));
        LOG.debug(String.valueOf(h));
    }
}
