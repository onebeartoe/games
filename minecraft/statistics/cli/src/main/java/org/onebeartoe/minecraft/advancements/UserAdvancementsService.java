
package org.onebeartoe.minecraft.advancements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class provides service methods for user specific advancements.
 */
public class UserAdvancementsService
{    
    private List<String> balancedDietItems;
    
    private List<String> bredAnimals;
    
    private List<String> userCats;
            
    private List<Advancement> incompleteUserAdvancements;

    private List<String> monstersHunted;
    
    private List<String> unbredAnimals;
    
    private AdvancementsService advancementsService;

    public final String statsPath = "/home/roberto/.minecraft/saves/Dragon Fart 2020 - 1_15_2/advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";
        
    public UserAdvancementsService() throws IOException, ParseException
    {
        advancementsService = new AdvancementsService();

        File inile = new File(statsPath);
                
        JSONParser parser = new JSONParser();

        String s = Files.readString( inile.toPath() );
        
        Object obj = parser.parse(s);

        JSONObject base = (JSONObject) obj;
        
        parseIncompleteUserAdvancements(base);
        
        // husbandry
        JSONObject dietItems = (JSONObject) base.get("minecraft:husbandry/balanced_diet");
        parseBalancedDiet(dietItems);
        
        JSONObject bredAnimals = (JSONObject) base.get("minecraft:husbandry/bred_all_animals");
        parseBredAnimals(bredAnimals);
        
        // adventure
        JSONObject mobsKilled = (JSONObject) base.get("minecraft:adventure/kill_all_mobs");
        parseMobsKilled(mobsKilled);
    }

    public List<String> balancedDietItems()
    {
        return List.copyOf(balancedDietItems);                
    }

    public List<Advancement> incompleteUserAdvancements() throws IOException, ParseException 
    {
        return incompleteUserAdvancements;
    }

    public List<Advancement> loadUserAdvancements() throws IOException, ParseException 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> monstersHunted() 
    {
        return List.copyOf(monstersHunted);
    }
    
    public List<String> unbredAnimals()
    {
        return List.copyOf(unbredAnimals);
    }

    private void parseBalancedDiet(JSONObject dietItems) 
    {
        List<String> allItems = new ArrayList<String>();
        
        JSONObject criteria = (JSONObject) dietItems.get("criteria");
        
        criteria.forEach((advanementName, u) -> 
        {
            String itemName = advanementName.toString();
            
            allItems.add(itemName);            
        });
        
        balancedDietItems                
                = allItems.stream()
                .sorted()
                .collect( Collectors.toList() );
    }
    
    private void parseIncompleteUserAdvancements(JSONObject base) throws IOException, ParseException 
    {                
        incompleteUserAdvancements = new ArrayList();
        
        userCats = new ArrayList();

        base.forEach((advanementName, u) -> 
        {
            Class<? extends Object> uClass = u.getClass();
                                    
            if( !advanementName.equals("DataVersion") )   // skip the DataVersion entry
            {
                JSONObject payload = (JSONObject) u;

// can this next line be moved to inside the if() statement?                
                JSONObject criteria = (JSONObject) payload.get("criteria");

if(criteria == null)
{
    System.out.println("a, u = " + advanementName + " - "+  u);
}
                
                boolean done = (boolean) payload.get("done");
                
                if( !done )
                {
                    Class<? extends Object> tClass = advanementName.getClass();

                    criteria.forEach((name, date) ->
                    {                        
                        Advancement adv = new Advancement();
                        adv.name = name.toString();
  
System.out.println("not done: " + adv.name);                        
                        
                        incompleteUserAdvancements.add(adv);
                        
                        if( adv.name.startsWith("textures/entity/cat/") )
                        {
                            userCats.add(adv.name);
                        }
                    });                    
                }
else
{
//    System.out.println("done: " + advanementName);
}                    
            }
        });
    }

    private void parseMobsKilled(JSONObject mobsKilledJson) 
    {
        List<String> list = new ArrayList<String>();
        
        JSONObject criteriaJson = (JSONObject) mobsKilledJson.get("criteria");
        
        criteriaJson.forEach( (n, w) -> 
        {
            String name = n.toString();
            
            list.add(name);
        });
        
        monstersHunted = list.stream()
                            .sorted()
                            .collect( Collectors.toList() );
    }

    private void parseBredAnimals(JSONObject bredAnimalsJson)
    {
        JSONObject criteriaJson = (JSONObject) bredAnimalsJson.get("criteria");

        List<String> list = new ArrayList<String>();

        criteriaJson.forEach( (n, w) -> 
        {
            String name = n.toString();
            
            list.add(name);
            
            System.out.println("n, w -> " + 
                name + " :-: " + w.toString()
                    );
        });        
        
        bredAnimals = list;
        
        List<String> missingItems = new ArrayList();
        
        System.out.println();
        System.out.println("Missing Edible Items:");
        List<String> breedableAnimals = advancementsService.breedableAnimals();
//TODO: reuse a BiPredicate        
        breedableAnimals.forEach(i ->
        {
            if( !bredAnimals.contains(i) )
            {
                missingItems.add(i);
                
                System.out.println(i);
            }
        });
        
        unbredAnimals = missingItems;
    }

    public List<String> missingCats() 
    {
        List<String> minecraftCats = advancementsService.allCatCategories();
        
        System.out.println("minecraftCats yo = " + minecraftCats);
        
        System.out.println("userCats = " + userCats);
        
        return missingFromB(minecraftCats, userCats);
    }
    
    private List<String> missingFromB(List<String> a, List<String> b)
    {
        List<String> missingItems = new ArrayList();
        
        System.out.println();
        System.out.println("Missing Items yo:");
        
//TODO: reuse a BiPredicate        
        a.forEach(i ->
        {
            if( !b.contains(i) )
            {
                missingItems.add(i);
                
                System.out.println(i);
            }
        });
        
        return missingItems;
    }

    public List<String> tamedCats() 
    {
        return List.copyOf(userCats);
    }
}
