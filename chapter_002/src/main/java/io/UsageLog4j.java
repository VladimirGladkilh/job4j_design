package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte a = 123;
        short b = 3276;
        int c = 2147483645;
        long d = 922337203685477580L;
        float e = 3.4e+32f;
        double f = 1.7e+305;
        boolean g = false;
        char h = 'h';
        LOG.debug("byte {}; short {}; int {}; long {}; float {}; double {}; boolean {}; char {}", a, b, c, d, e, f, g, h);

    }
}
