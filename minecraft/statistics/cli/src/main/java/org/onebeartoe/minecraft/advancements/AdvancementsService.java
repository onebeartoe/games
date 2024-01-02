
package org.onebeartoe.minecraft.advancements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class provides method to parse and query Minecraft Advancements data 
 * files.
 * 
 * This is specifically for Minecraft 1.17.
 */
public class AdvancementsService
{   
    //TODO: move all these to a map of <File, AdvancementsDto>    
    private List<String> catCategories;
    private List<String> balancedDietItems;
    private List<String> monstersHunted;
    private List<String> breedableAnimals;
    
//TODO: move this    
    private NetherAdvancementsCategory nether;
    
    public AdvancementsService() throws IOException, ParseException
    {
        JSONObject base = parseBase();
        
        JSONObject adventure = (JSONObject) base.get("adventure");
        parseAdventure(adventure);
        
        JSONObject end = (JSONObject) base.get("end");
        
        JSONObject husbandry = (JSONObject) base.get("husbandry");
        parseHusbandry(husbandry);
        
        JSONObject netherJson = (JSONObject) base.get("nether");
        parseNether(netherJson);
    }

    public List<String> allCatCategories() 
    {
        return List.copyOf(catCategories);
    }

    public List<String> balancedDietItems() 
    {
        List<String> items = List.copyOf(balancedDietItems);
        
        return items;
    }

    public List<String> monstersHunted() 
    {
        return List.copyOf(monstersHunted);
    }

    public List<String> breedableAnimals() 
    {                
        return List.copyOf(breedableAnimals);
    }

    private AdventureAdvancements parseAdventure(JSONObject adventureJson) 
    {
        JSONObject monstersHuntedJson = (JSONObject) adventureJson.get("minecraft:adventure/kill_all_mobs");
        
        JSONArray criteria = (JSONArray) monstersHuntedJson.get("criteria");
        
        monstersHunted = new ArrayList<String>();
        
        AdventureAdvancements adventure = new AdventureAdvancements();
        
        criteria.forEach(m -> 
        {
            String monster = m.toString();
            
            monstersHunted.add(monster);
        });
        
        adventure.monstersHunted.criteria = monstersHunted;
        
        return adventure;
    }

    private void parseBalancedDiet(JSONObject diet) 
    {
         balancedDietItems = new ArrayList<String>();

        JSONArray criteria = (JSONArray) diet.get("criteria");
        
        criteria.forEach(c -> 
        {
            String s = c.toString();
            
            balancedDietItems.add(s);
        });
    }

    private void parseBredAllAnimals(JSONObject bredAllAnimalsJson)
    {
        breedableAnimals = new ArrayList<String>();
        
        JSONArray criteria = (JSONArray) bredAllAnimalsJson.get("criteria");   
       
        criteria.forEach(c -> 
        {
            String animal = c.toString();
            
            breedableAnimals.add(animal);
        });
    }
  
    private List<String> parseCatCategories(JSONObject catelog) 
    {
        JSONArray criteria = (JSONArray) catelog.get("criteria");
        
        catCategories = new ArrayList<String>();
        
        criteria.forEach(c ->
        {            
            String category = c.toString();

            int end = category.lastIndexOf(".");

            category = category.substring(0, end);            
            
            catCategories.add(category);
        });
        
        return catCategories;
    }

    private HusbandryAdvancements parseHusbandry(JSONObject husbandry) 
    {
        JSONObject catalog = (JSONObject)husbandry.get("minecraft:husbandry/complete_catalogue");        
        List<String> completeCatalogue = parseCatCategories(catalog);
        
        JSONObject diet = (JSONObject)husbandry.get("minecraft:husbandry/balanced_diet");
        parseBalancedDiet(diet);
        
        JSONObject bredAllAnimals = (JSONObject) husbandry.get("minecraft:husbandry/bred_all_animals");
        parseBredAllAnimals(bredAllAnimals);
        
        HusbandryAdvancements advancements = new HusbandryAdvancements();
        
        advancements.aCompleteCatalogue.criteria = completeCatalogue;
        
        return advancements;
        
        
    }

    public Advancements load() throws IOException, ParseException 
    {
        Advancements advancements = new Advancements();
        
        advancements.nether = nether;
        
        JSONObject base = parseBase();
        
        JSONObject husbandry = (JSONObject) base.get("husbandry");
        advancements.husbandry = parseHusbandry(husbandry);
        
        JSONObject advanture = (JSONObject) base.get("adventure");
        
        advancements.adventure = parseAdventure(advanture);
        
        return advancements;
    }
    
    private void parseNether(JSONObject netherJson)
    {
        JSONObject hotTourist = (JSONObject) netherJson.get("minecraft:nether/explore_nether");
        
        JSONArray criteria = (JSONArray) hotTourist.get("criteria");
        
        nether = new NetherAdvancementsCategory();
        
        criteria.forEach(c -> 
        {
            var destination = c.toString();
            
            nether.hotTouristDestinations.criteria.add(destination);
        });
    }

    private JSONObject parseBase() throws IOException, ParseException 
    {
        String statsPath = "advancements/minecraft/20.json";
                
        JSONParser parser = new JSONParser();
        
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(statsPath);
        
        String s = new String(systemResourceAsStream.readAllBytes(), 
                              StandardCharsets.UTF_8);
        
        Object obj = parser.parse(s);

        JSONObject base = (JSONObject) obj;
        
        return base;
    }
}
