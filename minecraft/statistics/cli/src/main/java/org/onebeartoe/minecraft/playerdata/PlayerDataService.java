
package org.onebeartoe.minecraft.playerdata;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import net.minecraft.server.

/**
 *
 */
public class PlayerDataService
{

    public PlayerData load(String inpath) throws IOException, URISyntaxException, InterruptedException, ParseException 
    {
        var infile = new File(inpath);
        
        var name = infile.getName();
        
        var end = name.lastIndexOf(".");
        
        var uuid = name.substring(0, end);
        
        var playerName = requestPlayerName(uuid);            
        
        var data = new PlayerData();
        
        data.name = playerName;
        
        return data;
    }
    
    private String requestPlayerName(String uuid) throws URISyntaxException, IOException, InterruptedException, ParseException
    {
        var httpClient = HttpClient.newHttpClient();
        
        var endPoint = "https://playerdb.co/api/player/minecraft/b8da6a01-2a0d-4df1-a86a-94a3e3da6389";
        
        var uri = new URI(endPoint);
        
        HttpRequest request = HttpRequest.newBuilder(uri)
                                .build();
           
        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        
        var body = response.body();
        
        return parsePlayerNameJson(body);
    }

    private String parsePlayerNameJson(String body) throws ParseException
    {
        JSONParser parser = new JSONParser();        
        
        Object obj = parser.parse(body);

        JSONObject base = (JSONObject) obj;

        JSONObject data = (JSONObject) base.get("data");

        JSONObject player = (JSONObject) data.get("player");
        
        var playerName = player.get("username");
        
//TODO: ALSO PARSE THE PLAYER'S AVITAR AND DISPLAY IT        
        
        return playerName.toString();
    }
}
