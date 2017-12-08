package com.fibanez.util;


/**
 *
 * Factory which returns a configUtil
 *
 * @author fibanez
 */
public final class ConfigUtilFactory {

    private ConfigUtilFactory() {}

    public static ConfigUtil createConfigUtil(String data) {
        return new ConfigParserUtil(data);
    }

}
