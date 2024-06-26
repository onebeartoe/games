
package org.onebeartoe.minecraft;

import java.util.prefs.BackingStoreException;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
//import static org.testng.AssertJUnit.assertEquals;
//import org.testng.annotations.Test;

/**
 *
 */
public class CompanionAppPreferencesTest
{    
    @Test
    public void savesPath() throws BackingStoreException
    {
        var expected = "some/path/to/a/save";
        
        CompanionAppPreferences.savesPath(expected);
        
        String actual = CompanionAppPreferences.savesPath();
        
        assertEquals(expected, actual);
    }
}
