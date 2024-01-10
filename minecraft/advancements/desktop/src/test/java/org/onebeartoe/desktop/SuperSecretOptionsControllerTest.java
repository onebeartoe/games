
package org.onebeartoe.desktop;

import java.net.URISyntaxException;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

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
