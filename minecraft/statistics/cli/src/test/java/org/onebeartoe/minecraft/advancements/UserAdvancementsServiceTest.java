
package org.onebeartoe.minecraft.advancements;

import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * * This verifies the specification for AdvancementsService class.
 * 
 *  These tests explore the advancements in a Minecraft world for a single player.
 *
 * The tests assume the world data is stored in the default location:
 * 
 *     ~/.minecraft/saves/Dragon Fart 2020 - 1_15_2/
 * 
 */
public class UserAdvancementsServiceTest
{
    private UserAdvancementsService implementation;
    
    private AdvancementsService advancementsService;
    
    @BeforeTest
    private void initializeImplementation() throws IOException, ParseException
    {
        implementation = new UserAdvancementsService();
        
        advancementsService = new AdvancementsService();
    }
    
    @Test
    public void balancedDiet()
    {
        List<String> userDietItems = implementation.balancedDietItems();
        
        System.out.println("user diet items:");
        userDietItems.forEach(System.out::println);
        
//??????
        int missingItemCount = userDietItems.size();
        
        assertTrue(missingItemCount == 36);
        
        List<String> minecraftDietItems = advancementsService.balancedDietItems();
        System.out.println("all diet items:");
        minecraftDietItems.forEach(System.out::println);
        
        System.out.println("u: " + userDietItems.size() );
        System.out.println("m: " + minecraftDietItems.size() );
        
        
// where is the lambda!?!??!?!?!?!?!?!?!?!
throw new UnsupportedOperationException();
    }
    
    /**
     * This class verifies the production code returns the correct values for the 
     * missing mobiles associated with the 'Breed All the Animals' advancement.
     */
    @Test
    public void breedAllAnimals()
    {
        List<String> missingMobs = implementation.unbredMobs();
                
        // These values are the expected missing values 
        // in the advancements JSON file for this given user.
        assertTrue( missingMobs.contains("minecraft:axolotl") );
        assertTrue( missingMobs.contains("minecraft:donkey") );
        assertTrue( missingMobs.contains("minecraft:fox") );
        assertTrue( missingMobs.contains("minecraft:goat") );
        assertTrue( missingMobs.contains("minecraft:hoglin") );
        assertTrue( missingMobs.contains("minecraft:llama") );
        assertTrue( missingMobs.contains("minecraft:mooshroom") );
        assertTrue( missingMobs.contains("minecraft:mule") );
        assertTrue( missingMobs.contains("minecraft:ocelot") );
        assertTrue( missingMobs.contains("minecraft:panda") );
        assertTrue( missingMobs.contains("minecraft:strider") );
        assertTrue( missingMobs.contains("minecraft:turtle") );
        assertTrue( missingMobs.contains("minecraft:wolf") );               
    }
    
    @Test
    public void unfulfilledAdvancements() throws IOException, ParseException
    {
        List<Advancement> unfulfilledAdvancements = implementation.incompleteUserAdvancements();

//TODO: these need to be corrected        
        assertTrue(unfulfilledAdvancements.contains("two by two") );
        assertTrue(unfulfilledAdvancements.contains("a complete catalog") );
        assertTrue(unfulfilledAdvancements.contains("a balanced diet") );
        assertTrue(unfulfilledAdvancements.contains("a throw away joke") );
        assertTrue(unfulfilledAdvancements.contains("very very frightening") );
        assertTrue(unfulfilledAdvancements.contains("sniper dual") );
        assertTrue(unfulfilledAdvancements.contains("bullseye") );
        assertTrue(unfulfilledAdvancements.contains("monsters hunted") );
        assertTrue(unfulfilledAdvancements.contains("postmortal") );
        assertTrue(unfulfilledAdvancements.contains("two birds one arrow") );
        assertTrue(unfulfilledAdvancements.contains("adventuring time") );
        assertTrue(unfulfilledAdvancements.contains("the end again") );
        assertTrue(unfulfilledAdvancements.contains("uneasy alliance") );
        assertTrue(unfulfilledAdvancements.contains("country load take me home") );
        assertTrue(unfulfilledAdvancements.contains("spooky scary skeleton") );
        assertTrue(unfulfilledAdvancements.contains("withering heights") );
        assertTrue(unfulfilledAdvancements.contains("a furious cocktail") );        
    }
}
