
package org.onebeartoe.minecraft.advancements;

import net.minecraft.advancements.PlayerAdvancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.PlayerNetherAdvancementsCategory;
import net.minecraft.advancements.PlayerHusbandryAdvancements;
import net.minecraft.advancements.PlayerAdvancementsService;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementsService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import net.minecraft.advancements.Advancements;
import org.json.simple.parser.ParseException;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * This is specifically for Minecraft 1.20.
 * 
 * * This verifies the specification for UserAdvancementsService class.
 * 
 *  These tests explore the advancements in a Minecraft world for a single player.
 *
 * The tests assume the world data is stored in the default location:
 * 
 *     ~/.minecraft/saves/Dragon Fart 2020 - 1_15_2/
 * 
 */
public class PlayerAdvancementsServiceTest
{
    private PlayerAdvancementsService implementation;
    
    private AdvancementsService advancementsService;
    
    private PlayerAdvancements playerAdvancements;

//TODO: DO NOT USE A DEVELOPER ENVIRONMENT SPECIFIC PATH!!!!!!!!!!    
    public static final String advancementsPath = "/home/roberto/Versioning/owner/github/games/minecraft/advancements/cli/src/test/resources/minecraft/saves/1.20/advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";
    
    private Advancements advancements;
    
    @BeforeTest
    private void initializeImplementation() throws IOException, ParseException
    {        
        implementation = new PlayerAdvancementsService();
        
        implementation.load(advancementsPath);
        
        advancementsService = new AdvancementsService();
        
        advancements = advancementsService.load();
    }

    @Test
    public void balancedDiet()
    {
        List<String> userDietItems = implementation.balancedDietItems();

        int userItemCount = userDietItems.size();
        
        assertTrue(userItemCount == 19);
        
        List<String> minecraftDietItems = advancementsService.balancedDietItems();
        
        assertFalse( userDietItems.contains("dried_kelp") );
        assertFalse( userDietItems.contains("mushroom_stew") );
        assertFalse( userDietItems.contains("rabbit") );
        
        // these are the itesm the player has already obtained:
        assertTrue( userDietItems.contains("apple") );
        assertTrue( userDietItems.contains("baked_potato") );
        assertTrue( !userDietItems.contains("beef") );
        assertTrue( !userDietItems.contains("beetroot") );
        assertTrue( !userDietItems.contains("beetroot_soup") );
        assertTrue( userDietItems.contains("bread") );
        assertTrue( userDietItems.contains("carrot") );
        assertTrue( userDietItems.contains("chicken") );
        assertTrue( !userDietItems.contains("chorus_fruit") );
        assertTrue( !userDietItems.contains("cod") );
        assertTrue( !userDietItems.contains("cooked_beef") );
        assertTrue( userDietItems.contains("cooked_chicken") );
        assertTrue( !userDietItems.contains("cooked_cod") );
        assertTrue( !userDietItems.contains("cooked_mutton") );
        assertTrue( !userDietItems.contains("cooked_porkchop") );
        assertTrue( !userDietItems.contains("cooked_rabbit") );
        assertTrue( userDietItems.contains("cooked_salmon") );
        assertTrue( userDietItems.contains("cookie") );
        assertTrue( !userDietItems.contains("enchanted_golden_apple") );
        assertTrue( userDietItems.contains("golden_apple") );
        assertTrue( userDietItems.contains("golden_carrot") );
        assertTrue( !userDietItems.contains("honey_bottle") );
        assertTrue( userDietItems.contains("melon_slice") );
        assertTrue( !userDietItems.contains("mutton") );
        assertTrue( userDietItems.contains("poisonous_potato") );
        assertTrue( !userDietItems.contains("porkchop") );
        assertTrue( userDietItems.contains("potato") );
        assertTrue( !userDietItems.contains("pufferfish") );
        assertTrue( userDietItems.contains("pumpkin_pie") );
        assertTrue( userDietItems.contains("rabbit_stew") );
        assertTrue( userDietItems.contains("rotten_flesh") );
        assertTrue( !userDietItems.contains("salmon") );
        assertTrue( !userDietItems.contains("spider_eye") );
        assertTrue( userDietItems.contains("suspicious_stew") );
        assertTrue( userDietItems.contains("sweet_berries") );
        assertTrue( !userDietItems.contains("tropical_fish") );        
    }
    
    @Test
    public void balancedDietSanityCheckAllUserItemsAreInTheMinecraftItemList()
    {
        List<String> userBalancedDietItems = implementation.balancedDietItems();
        
        List<String> allBalancedDietItems1 = advancementsService.balancedDietItems();

        userBalancedDietItems.forEach(ubdi ->
        {
            boolean missingItem = !allBalancedDietItems1.contains(ubdi);
                                   
//            if(missingItem)
//            {
//                System.out.println("missingItem = " + ubdi);
//            }
            
            assertFalse(missingItem);
        });
    }
    
    /**
     * This class verifies the production code returns the correct values for the 
     * missing mobiles associated with the 'Breed All the Animals' advancement.
     */
    @Test
    public void breedAllAnimals()
    {
        List<String> missingMobs = implementation.unbredAnimals();
        
        List<String> breadAnimals = implementation.bredAnimals();

        int actualHaves = breadAnimals.size();
        
        int expectedHaves = 8;
        assertEquals(expectedHaves, actualHaves);        
                
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
//!?!?!?!??!?!?!?!?!?!?S        
assertTrue( missingMobs.contains("minecraft:strider") );
        assertTrue( missingMobs.contains("minecraft:strider") );
        assertTrue( !missingMobs.contains("minecraft:turtle") );
        assertTrue( !missingMobs.contains("minecraft:wolf") );               
    }

    @Test
    public void monstersHunted()
    {
        List<String> userMonstersHunted = implementation.monstersHunted();

        int userItemCount = userMonstersHunted.size();        
        assertEquals(31, userItemCount);
        
        List<String> minecraftMonstersHunted = advancements.adventure.monstersHunted.criteria;
        
        assertEquals(35, minecraftMonstersHunted.size());

        assertFalse(userMonstersHunted.contains("minecraft:endermite") );
        assertFalse(!userMonstersHunted.contains("minecraft:stray") );
        assertFalse(userMonstersHunted.contains("minecraft:wither") );
        
        
        // these are the itesm the player has already obtained:
        assertTrue(userMonstersHunted.contains("minecraft:blaze") );
        assertTrue(userMonstersHunted.contains("minecraft:cave_spider") );
        assertTrue(userMonstersHunted.contains("minecraft:creeper") );
        assertTrue(userMonstersHunted.contains("minecraft:drowned") );
        assertTrue(userMonstersHunted.contains("minecraft:elder_guardian") );
        assertTrue(userMonstersHunted.contains("minecraft:ender_dragon") );
        assertTrue(userMonstersHunted.contains("minecraft:enderman") );
        assertTrue(userMonstersHunted.contains("minecraft:evoker") );
        assertTrue(userMonstersHunted.contains("minecraft:ghast") );
        assertTrue(userMonstersHunted.contains("minecraft:guardian") );
        assertTrue(userMonstersHunted.contains("minecraft:hoglin") );
        assertTrue(userMonstersHunted.contains("minecraft:husk") );
        assertTrue(userMonstersHunted.contains("minecraft:magma_cube") );
        assertTrue(userMonstersHunted.contains("minecraft:phantom") );
        assertTrue(userMonstersHunted.contains("minecraft:piglin") );
        assertTrue(userMonstersHunted.contains("minecraft:piglin_brute") );
        assertTrue(userMonstersHunted.contains("minecraft:pillager") );
        assertTrue(userMonstersHunted.contains("minecraft:ravager") );
        assertTrue(!userMonstersHunted.contains("minecraft:shulker") );
        assertTrue(userMonstersHunted.contains("minecraft:silverfish") );
        assertTrue(userMonstersHunted.contains("minecraft:skeleton") );
        assertTrue(userMonstersHunted.contains("minecraft:slime") );
        assertTrue(userMonstersHunted.contains("minecraft:spider") );
        assertTrue(userMonstersHunted.contains("minecraft:vex") );
        assertTrue(userMonstersHunted.contains("minecraft:vindicator") );
        assertTrue(userMonstersHunted.contains("minecraft:witch") );
        assertTrue(userMonstersHunted.contains("minecraft:wither_skeleton") );
        assertTrue(!userMonstersHunted.contains("minecraft:zoglin") );
        assertTrue(userMonstersHunted.contains("minecraft:zombie") );
        assertTrue(userMonstersHunted.contains("minecraft:zombie_villager") );
        assertTrue(userMonstersHunted.contains("minecraft:zombified_piglin") );            
    }
    
    
//TODO: enable this once you understand why there are 100+ advancements    
    @Test(enabled = false)
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
    
    @Test
    public void tamedCats()
    {
//        List<String> cats = implementation.tamedCats();
        List<String> cats = playerAdvancements.husbandry.aCompleteCatelogue.haves();
        
        int expected = 10;
        
        int actual = cats.size();
        
        // verify the size of the list
        assertEquals(expected, actual);
        
        // check for all expected missing cats
        assertTrue( cats.contains("minecraft:all_black") );
        assertTrue( cats.contains("minecraft:black") );
        assertTrue( cats.contains("minecraft:british_shorthair") );
        assertTrue( cats.contains("minecraft:calico" ));
        assertTrue( cats.contains("minecraft:jellie" ));
        assertTrue( cats.contains("minecraft:persian" ));
        assertTrue( cats.contains("minecraft:red" ));
        assertTrue( cats.contains("minecraft:siamese" ));
        assertTrue( cats.contains("minecraft:tabby" ));
        
        // I manually looked in the .minecraft/saves/adavancements/file.json to see 
        // which cat I was missing.
        assertTrue( !cats.contains("minecraft:white") );
    }

    @Test
    public void load() throws IOException, ParseException
    {
        playerAdvancements = implementation.load(advancementsPath);

        verifyNether(playerAdvancements.nether);
        
        verifyHusbandry(playerAdvancements.husbandry);
    }

    private void verifyNether(PlayerNetherAdvancementsCategory nether) 
    {
        Map<String, Boolean> criteria = nether.hotTouristDestinations.criteria;
        
        assertTrue(criteria.size() == 5);

        List<String> haves = nether.hotTouristDestinations.haves();
        
        assertEquals( haves.size() , 5);
        
        List<String> haveNots = nether.hotTouristDestinations.haveNots();
        
        assertTrue( haveNots.isEmpty() );
    }

    private void verifyHusbandry(PlayerHusbandryAdvancements husbandry) 
    {
        verifyCompleteCatelogue(husbandry.aCompleteCatelogue);
    }

    private void verifyCompleteCatelogue(PlayerAdvancement aCompleteCatelogue)
    {
        var missingCats = playerAdvancements.husbandry.aCompleteCatelogue.haveNots();
                     
        int expected = 1;
        
        int actual = missingCats.size();
        
        // verify the size of the list
        assertEquals(expected, actual);
        
//        String cat1 = missingCats.get(0);
//        assertTrue(!cat1.contains("/"));
//        
//        assertTrue(!cat1.contains("."));
        
        // check for all expected missing cats
        assertTrue( missingCats.contains("minecraft:white") );        
    }
}
