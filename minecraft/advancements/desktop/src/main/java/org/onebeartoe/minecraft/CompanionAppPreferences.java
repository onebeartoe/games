
package org.onebeartoe.minecraft;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 */
public class CompanionAppPreferences
{
    private static final Preferences preferences = Preferences.userNodeForPackage(CompanionAppPreferences.class);
        
    final static String DEFAULT_PREFERENCE = "default-preference";
    
    final static String SAVES_PATH = "onebeartoe.minecraft.companion.app.SAVES_PATH";
    
    private CompanionAppPreferences()
    {
        // Single is a Ton of fun!
    }
    
    public static String savesPath()
    {
	String value = preferences.get(SAVES_PATH, DEFAULT_PREFERENCE);
	
	return value;        
    }

    public static void savesPath(String path) throws BackingStoreException
    {
        persistProperty(SAVES_PATH, path);
    }
    
    public static void persistProperty(String key, String value) throws BackingStoreException
    {
        preferences.put(key, value);
        
        preferences.flush();
    }    
}
