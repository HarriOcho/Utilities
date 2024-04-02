package github.com.harriocho.utilities.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Utils {
    private Utils() {
        throw new UnsupportedOperationException("No puede instanciar esta clase");
    }
    public static Component parseMessages(String message){
        return LegacyComponentSerializer.legacyAmpersand().deserialize(message);
    }
    public static Component deniedConsole(){
        return parseMessages("&cEste comando sólo puede ser establecido por jugadores.");
    }
    public static Component noPermission(){
        return parseMessages("&cDebes ser administrador o superior para poder ejecutar esta acción.");
    }
}
