package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigSingleton {

    private Dotenv env;

    private static class SingletonHolder {
        public static ConfigSingleton instance = new ConfigSingleton();
    }

    private ConfigSingleton()
    {
        this.env = Dotenv.configure().directory(System.getProperty("user.dir")).load();
    }

    public static ConfigSingleton getInstance() {
        return SingletonHolder.instance;
    }

    public String getProperty(ConfigAttributes value) {
        return this.env.get(value.toString());
    }

}
