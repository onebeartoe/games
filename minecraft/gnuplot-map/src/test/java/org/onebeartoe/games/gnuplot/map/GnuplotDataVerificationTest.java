

package org.onebeartoe.games.gnuplot.map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 *
 */
public class GnuplotDataVerificationTest 
{
    GnuplotDataVerification implementation = new GnuplotDataVerification();
    
    @Test
    public void isValid_three()
    {        
        var line = """                   
                    2, 3, "id"
                    """;
                    
        var marker = implementation.isValid(line);
        
        assertThat(marker.valid()).isTrue();
        
        assertThat(marker.location().getX()).isEqualTo(2.0);
        
        assertThat(marker.location().getY()).isEqualTo(0.0);
        
        assertThat(marker.location().getZ()).isEqualTo(3.0);
        
        var id = marker.id();
        
        String expectedId = "\"id\"";
        
        assertThat(id).isEqualTo(expectedId);
    }
    
    @Test
    public void isValid_four()
    {
        var line = """                   
                    12, 43, -33, "id"
                    """;
                    
        var marker = implementation.isValid(line);
        
        assertThat(marker.valid()).isTrue();
        
        assertThat(marker.location().getX()).isEqualTo(12.0);
        
        assertThat(marker.location().getY()).isEqualTo(43.0);
        
        assertThat(marker.location().getZ()).isEqualTo(-33.0);
        
        assertThat(marker.id()).isEqualTo("\"id\"");
    }
        
    @Test
    public void isValid_four_tilde()
    {
        var line = """                   
                    12, ~, -33, "id"
                    """;
                    
        var marker = implementation.isValid(line);
        
        assertThat(marker.valid()).isTrue();
        
        assertThat(marker.location().getX()).isEqualTo(12.0);
        
        assertThat(marker.location().getY()).isEqualTo(0.0); // tilde defalut to zero
        
        assertThat(marker.location().getZ()).isEqualTo(-33.0);
        
        assertThat(marker.id()).isEqualTo("\"id\"");
    }
}
