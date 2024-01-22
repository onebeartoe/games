
package net.minecraft.advancements;

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
 * 
 * This is specifically for Minecraft 1.17.
 */
public class PlayerAdvancementsService
{    
    private List<String> balancedDietItems;
    
    private List<String> bredAnimals;
    
    private List<String> userCats;
            
    private List<Advancement> incompleteUserAdvancements;

    private List<String> monstersHunted;
    
    private List<String> unbredAnimals;
    
    private final Advancements minecraftAdvancements;
    
//TODO: move this to local assignment once all the parsing is moved
    private AdvancementsService advancementsService = new AdvancementsService();
    
    public PlayerAdvancementsService() throws IOException, ParseException
    {
        minecraftAdvancements = advancementsService.load();
    }

    public List<String> balancedDietItems()
    {
        return List.copyOf(balancedDietItems);                
    }

    public List<Advancement> incompleteUserAdvancements() throws IOException, ParseException 
    {
        return incompleteUserAdvancements;
    }
    
    public JSONObject loadBase(String advancementsPath) throws IOException, ParseException
    {
        File inile = new File(advancementsPath);
                
        JSONParser parser = new JSONParser();
        
        
        String s = Files.readString(inile.toPath());
        
        Object obj = parser.parse(s);

        JSONObject base = (JSONObject) obj;
        
        parseIncompleteUserAdvancements(base);
        
        // husbandry
        JSONObject dietItems = (JSONObject) base.get("minecraft:husbandry/balanced_diet");
        parseBalancedDiet(dietItems);
        
        JSONObject bredAnimalsJson = (JSONObject) base.get("minecraft:husbandry/bred_all_animals");
        parseBredAnimals(bredAnimalsJson);
        
        // adventure
        JSONObject mobsKilled = (JSONObject) base.get("minecraft:adventure/kill_all_mobs");
        parseMobsKilled(mobsKilled);        
        
        return base;
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

                JSONObject criteria = (JSONObject) payload.get("criteria");
                
                boolean done = (boolean) payload.get("done");
                
                if( !done )
                {
                    Class<? extends Object> tClass = advanementName.getClass();

                    criteria.forEach((name, date) ->
                    {                        
                        Advancement adv = new Advancement();
                        adv.name = name.toString();
                        
                        
                        incompleteUserAdvancements.add(adv);
                        
                        if( adv.name.startsWith("textures/entity/cat/") )
                        {
                            userCats.add(adv.name);
                        }
                    });                    
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
        });        
        
        bredAnimals = list;
        
        List<String> missingItems = new ArrayList();
        
        List<String> breedableAnimals = advancementsService.breedableAnimals();

//TODO: reuse a BiPredicate        
        breedableAnimals.forEach(i ->
        {
            if( !bredAnimals.contains(i) )
            {
                missingItems.add(i);
            }
        });
        
        unbredAnimals = missingItems;
    }

    public List<String> missingCats() 
    {
        List<String> minecraftCats = advancementsService.allCatCategories();
        
        return missingFromB(minecraftCats, userCats);
    }
    
//TODO: move this to a better place    
    public static List<String> missingFromB(List<String> a, List<String> b)
    {
        List<String> missingItems = new ArrayList();
        

        
//TODO: reuse a BiPredicate        
        a.forEach(i ->
        {
            if( !b.contains(i) )
            {
                missingItems.add(i);
            }
        });
        
        return missingItems;
    }

    public List<String> tamedCats() 
    {
        return List.copyOf(userCats);
    }
    
    public PlayerAdvancements load(String advancementsPath) throws IOException, ParseException    
    {
        var advancements = new PlayerAdvancements();
        
        JSONObject base = loadBase(advancementsPath);        

        JSONObject netherJson = (JSONObject) base.get("minecraft:nether/explore_nether");        
        advancements.nether = parseNether(netherJson);
        
        advancements.husbandry = parseHusbandry(base);
        
        advancements.adventure = parseAdventure();
        
        return advancements;
    }

    private PlayerNetherAdvancementsCategory parseNether(JSONObject netherJson) 
    {
        var playerAdvancements = new <String> ArrayList();
                
        JSONObject criteriaJson = (JSONObject) netherJson.get("criteria");
        
        criteriaJson.forEach((name, date) -> 
        {
            playerAdvancements.add(name);
        });
                
        PlayerNetherAdvancementsCategory advancements = new PlayerNetherAdvancementsCategory();
        
        List<String> minecraftCriteria = minecraftAdvancements.nether.hotTouristDestinations.criteria;
        
        minecraftCriteria.forEach((criteriaName) -> 
        {
            if(playerAdvancements.contains(criteriaName))
            {
                advancements.hotTouristDestinations.criteria.put(criteriaName, true);
            }
            else
            {
                advancements.hotTouristDestinations.criteria.put(criteriaName, false);
            }
        });
        
        return advancements;
    }

    private PlayerHusbandryAdvancements parseHusbandry(JSONObject base) 
    {
        JSONObject completeCatalogueJson = (JSONObject) base.get("minecraft:husbandry/complete_catalogue");
        
        var playerAdvancementsList = new <String> ArrayList();
        
        JSONObject criteriaJson = (JSONObject) completeCatalogueJson.get("criteria");
        
        criteriaJson.forEach((name, date) -> 
        {
            playerAdvancementsList.add(name);
        });
        
        var playerAdvancements = new PlayerHusbandryAdvancements();
        
        List<String> minecraftCriteria = minecraftAdvancements.husbandry.aCompleteCatalogue.criteria;
// ARE THE NAMES ARE MISMATCHED!!!!!!!!!they formages were different,deletes one tests pass
        minecraftCriteria.forEach((criteriaName) -> 
        {
            if(playerAdvancementsList.contains(criteriaName))
            {
                playerAdvancements.aCompleteCatelogue.criteria.put(criteriaName, true);
            }
            else
            {
                playerAdvancements.aCompleteCatelogue.criteria.put(criteriaName, false);
            }
        });
        
        return playerAdvancements;
    }

    private PlayerAdventureAdvancements parseAdventure() 
    {
        var adventure = new PlayerAdventureAdvancements();

//TODO: fix this!!!!
        List<String> minecraftCriteria = minecraftAdvancements.adventure.monstersHunted.criteria;

        minecraftCriteria.forEach((criteriaName) -> 
        {
            if(monstersHunted.contains(criteriaName))
            {
                adventure.monstersHunted.criteria.put(criteriaName, true);
            }
            else
            {
                adventure.monstersHunted.criteria.put(criteriaName, false);
            }
        });

        return adventure;
    }
}
