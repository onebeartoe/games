
package org.onebeartoe.minecraft.advancements;

import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
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

        int userItemCount = userDietItems.size();
        
        assertTrue(userItemCount == 36);
        
        List<String> minecraftDietItems = advancementsService.balancedDietItems();
        
//        System.out.println("all diet items:");
//        minecraftDietItems.forEach(System.out::println);
//        
//        System.out.println("u: " + userDietItems.size() );
//        System.out.println("m: " + minecraftDietItems.size() );
                                
        
        assertFalse( userDietItems.contains("dried_kelp") );
        assertFalse( userDietItems.contains("mushroom_stew") );
        assertFalse( userDietItems.contains("rabbit") );
        
        
        // these are the itesm the player has already obtained:
        assertTrue( userDietItems.contains("apple") );
        assertTrue( userDietItems.contains("baked_potato") );
        assertTrue( userDietItems.contains("beef") );
        assertTrue( userDietItems.contains("beetroot") );
        assertTrue( userDietItems.contains("beetroot_soup") );
        assertTrue( userDietItems.contains("bread") );
        assertTrue( userDietItems.contains("carrot") );
        assertTrue( userDietItems.contains("chicken") );
        assertTrue( userDietItems.contains("chorus_fruit") );
        assertTrue( userDietItems.contains("cod") );
        assertTrue( userDietItems.contains("cooked_beef") );
        assertTrue( userDietItems.contains("cooked_chicken") );
        assertTrue( userDietItems.contains("cooked_cod") );
        assertTrue( userDietItems.contains("cooked_mutton") );
        assertTrue( userDietItems.contains("cooked_porkchop") );
        assertTrue( userDietItems.contains("cooked_rabbit") );
        assertTrue( userDietItems.contains("cooked_salmon") );
        assertTrue( userDietItems.contains("cookie") );
        assertTrue( userDietItems.contains("enchanted_golden_apple") );
        assertTrue( userDietItems.contains("golden_apple") );
        assertTrue( userDietItems.contains("golden_carrot") );
        assertTrue( userDietItems.contains("honey_bottle") );
        assertTrue( userDietItems.contains("melon_slice") );
        assertTrue( userDietItems.contains("mutton") );
        assertTrue( userDietItems.contains("poisonous_potato") );
        assertTrue( userDietItems.contains("porkchop") );
        assertTrue( userDietItems.contains("potato") );
        assertTrue( userDietItems.contains("pufferfish") );
        assertTrue( userDietItems.contains("pumpkin_pie") );
        assertTrue( userDietItems.contains("rabbit_stew") );
        assertTrue( userDietItems.contains("rotten_flesh") );
        assertTrue( userDietItems.contains("salmon") );
        assertTrue( userDietItems.contains("spider_eye") );
        assertTrue( userDietItems.contains("suspicious_stew") );
        assertTrue( userDietItems.contains("sweet_berries") );
        assertTrue( userDietItems.contains("tropical_fish") );        
    }
    
    @Test
    public void balancedDietSanityCheckAllUserItemsAreInTheMinecraftItemList()
    {
// testo!?!??!?!?!?!?!?!?!?!
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
    public void monstersHunted()
    {
        List<String> userMonstersHunted = implementation.monstersHunted();

        System.out.println("user monsters hunted:");        
        userMonstersHunted.forEach(System.out::println);

        int userItemCount = userMonstersHunted.size();        
        assertEquals(31, userItemCount);
        
        List<String> minecraftMonstersHunted = advancementsService.monstersHunted();
        
        assertEquals(33, minecraftMonstersHunted.size());
        
        System.out.println("monsters hunted:");
        minecraftMonstersHunted.forEach(System.out::println);
        
        System.out.println("u: " + userMonstersHunted.size() );
        System.out.println("m: " + minecraftMonstersHunted.size() );
                                
        
        assertFalse(userMonstersHunted.contains("minecraft:endermite") );
        assertFalse(userMonstersHunted.contains("minecraft:stray") );
        assertFalse(userMonstersHunted.contains("minecraft:wither") );
        
        
        // these are the itesm the player has already obtained:
        assertTrue(userMonstersHunted.contains("") );
        assertTrue(userMonstersHunted.contains("") );
        assertTrue(userMonstersHunted.contains("") );
        assertTrue(userMonstersHunted.contains("") );    
        
minecraft:blaze
minecraft:cave_spider
minecraft:creeper
minecraft:drowned
minecraft:elder_guardian
minecraft:ender_dragon
minecraft:enderman

minecraft:evoker
minecraft:ghast
minecraft:guardian
minecraft:hoglin
minecraft:husk
minecraft:magma_cube
minecraft:phantom
minecraft:piglin
minecraft:piglin_brute
minecraft:pillager
minecraft:ravager
minecraft:shulker
minecraft:silverfish
minecraft:skeleton
minecraft:slime
minecraft:spider

minecraft:vex
minecraft:vindicator
minecraft:witch

minecraft:wither_skeleton
minecraft:zoglin
minecraft:zombie
minecraft:zombie_villager
minecraft:zombified_piglin        
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
