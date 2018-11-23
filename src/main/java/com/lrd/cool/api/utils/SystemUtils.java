package com.lrd.cool.api.utils;

import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author lierdong
 */
@Component
public class SystemUtils {

    private static String getIP() {
        try {
            InetAddress host = Inet4Address.getLocalHost();
            return host.getHostAddress();
        } catch (UnknownHostException e) {
            LogMan.e(e.getMessage());
        }
        return null;
    }

    private static int getPort() {
        return 8080;
    }

    public static String getSchema() {
        return "http://" + getIP() + ":" + getPort();
    }
}
