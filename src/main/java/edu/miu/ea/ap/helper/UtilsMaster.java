package edu.miu.ea.ap.helper;

import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import edu.miu.ea.ap.helper.configuration.DateConfigFinals;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class UtilsMaster {

    public static final char[] CSV_SPECIAL_CHARACTERS = {',', '"', '\r', '\n', '\t'};

    public static void shouldNotBeNull(Object object, String objectName) {
        if (object == null) {
            throw new RuntimeException(objectName + " not found.");
        }
    }

    public static Map<String, Object> packetObject(String key, Object object) {
        Map<String, Object> packet = new HashMap<>();
        packet.put(key, object);
        return packet;
    }

    public static boolean isValidString(String string) {
        if (string == null) {
            return false;
        } else {
            return !string.trim().equalsIgnoreCase("");
        }
    }

    public static String getFormattedException(Exception e) {
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }

    public static Date getFormattedDate(Date date, String format) {
        try {
            DateFormat formatter = new SimpleDateFormat(format);
            return formatter.parse(formatter.format(date));
        } catch (Exception ex) {
            ex.printStackTrace();
            return date;
        }
    }

    public static String getFormattedDateString(Date date, String format) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return date != null ? date.toString() : null;
        }
    }

    public static String getFormattedDateStringDefault(Date date) {
        try {
            return new SimpleDateFormat(DateConfigFinals.DATETIME_FORMAT).format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return date != null ? date.toString() : null;
        }
    }

    public static Date getFormattedDate(String date, String format) {
        try {
            DateFormat formatter = new SimpleDateFormat(format);
            return formatter.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Date getFormattedDateDefault(String date) {
        try {
            DateFormat formatter = new SimpleDateFormat(DateConfigFinals.DATETIME_FORMAT);
            return formatter.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getFormattedDateString(String date, String format) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Date getFormattedDate(Object date, String format) {
        if (date != null) {
            if (Date.class.isAssignableFrom(date.getClass())) {
                return getFormattedDate((Date) date, format);
            } else if (String.class.isAssignableFrom(date.getClass())) {
                return getFormattedDate((String) date, format);
            }
        }

        return null;
    }

    public static String getFormattedDateString(Object date, String format) {
        if (date != null) {
            if (Date.class.isAssignableFrom(date.getClass())) {
                return getFormattedDateString((Date) date, format);
            } else if (String.class.isAssignableFrom(date.getClass())) {
                return getFormattedDateString((String) date, format);
            }
        }

        return null;
    }

    public static String fetchClientIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        String ip = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        Assert.isTrue(ip.chars().filter($ -> $ == '.').count() == 3, "Illegal IP: " + ip);
        return ip;
    }

}
