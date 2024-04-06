
package org.onebeartoe.minecraft;

import java.net.URISyntaxException;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;
//import org.testng.annotations.Test;

//import static org.testng.AssertJUnit.assertTrue;

/**
 *
 */
public class SuperSecretOptionsControllerTest
{
    @Test
    public void loadBackgroundSoundUri() throws URISyntaxException
    {
        SuperSecretOptionsController implementation = new SuperSecretOptionsController();
        
        var uri = implementation.loadBackgroundSoundUri();
                
        assertTrue(uri != null);
        
        assertTrue( !uri.isEmpty() );
    }
}
