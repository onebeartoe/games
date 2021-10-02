
package org.onebeartoe.minecraft.advancements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class provides method to parse and query Minecraft Advancements data 
 * files.
 */
public class AdvancementsService
{   
//TODO: move all these to a map of <File, AdvancementsDto>    
private List<String> catCategories;
private List<String> balancedDietItems;
private List<String> monstersHunted;
private List<String> breedableAnimals;
    
    public AdvancementsService() throws IOException, ParseException
    {
//        String statsPath = "src/main/resources/advancements/minecraft/16.json";
        String statsPath = "src/main/resources/advancements/minecraft/17.json";

        File inile = new File(statsPath);
                
        JSONParser parser = new JSONParser();

        String s = Files.readString( inile.toPath() );
        
        Object obj = parser.parse(s);

        JSONObject base = (JSONObject) obj;
        
        JSONObject adventure = (JSONObject) base.get("adventure");
        parseAdventure(adventure);
        
        JSONObject end = (JSONObject) base.get("end");
        
        JSONObject husbandry = (JSONObject) base.get("husbandry");
        parseHusbandry(husbandry);
        
        JSONObject nether = (JSONObject) base.get("nether");
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

    private void parseAdventure(JSONObject adventureJson) 
    {
        JSONObject monstersHuntedJson = (JSONObject) adventureJson.get("minecraft:adventure/kill_all_mobs");
        
        JSONArray criteria = (JSONArray) monstersHuntedJson.get("criteria");
        
        monstersHunted = new ArrayList<String>();
        
        criteria.forEach(m -> 
        {
            String monster = m.toString();
            
            monstersHunted.add(monster);
        });
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
        
System.out.println(criteria);        
       
        criteria.forEach(c -> 
        {
            String animal = c.toString();
            
            breedableAnimals.add(animal);
        });
    }
  
    private void parseCatCategories(JSONObject catelog) 
    {
        JSONArray criteria = (JSONArray) catelog.get("criteria");
        
        catCategories = new ArrayList<String>();
        
        criteria.forEach(c ->
        {
//            System.out.println("c = " + c);
//            System.out.println("class = " + c.getClass() );
            
            String category = c.toString();
            
            catCategories.add(category);
        });
    }

    private void parseHusbandry(JSONObject husbandry) 
    {
        JSONObject catalog = (JSONObject)husbandry.get("minecraft:husbandry/complete_catalogue");        
        parseCatCategories(catalog);
        
        JSONObject diet = (JSONObject)husbandry.get("minecraft:husbandry/balanced_diet");
        parseBalancedDiet(diet);
        
        JSONObject bredAllAnimals = (JSONObject) husbandry.get("minecraft:husbandry/bred_all_animals");
        parseBredAllAnimals(bredAllAnimals);
    }
}
