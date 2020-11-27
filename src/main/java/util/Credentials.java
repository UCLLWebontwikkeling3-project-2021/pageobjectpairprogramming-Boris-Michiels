package util;

import java.util.Properties;

public abstract class Credentials {
    public static void setPass(Properties dbProperties) {
        dbProperties.setProperty("user", "local_r123456");
        dbProperties.setProperty("password", "dagadegijniebepalen!");
    }
}