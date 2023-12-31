package org.onebeartoe.desktop;

import org.onebeartoe.minecraft.advancements.AdvancementItem;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import org.json.simple.parser.ParseException;
import org.onebeartoe.minecraft.advancements.v1_20.MinecraftWildAdvancementsService;

/**
 * The background "splash" animation is actually setup/started in the 
 * JavaFX application's main() class in the @Override start() method.
 */
public class AdvancementsController 
{
    @FXML
    public MediaView mediaView;
    
    @FXML
    public VBox vBox;
    
    @FXML
    Button singlePlayerButton;
    
    @FXML
    TextArea husbundryHavesTextArea;
    
    @FXML
    HBox netherHBox;
    
    @FXML
    HBox husbandryHBox;
    
    @FXML
    ImageView hotTouristDestinationsImage;
    
    private MinecraftWildAdvancementsService implementation;
    
    private PlayerAdvancementsService playerAdvancementsService;
    
    private PlayerAdvancements playerAdvancements;
    
    @FXML
    public void initialize() throws URISyntaxException, IOException, ParseException
    {     
        implementation = new MinecraftWildAdvancementsService();                  
          
        List<String> allAdventureTimes = implementation.allAdventureTimes();
        
        husbundryHavesTextArea.setText(allAdventureTimes.toString());
        
        netherHBox.setSpacing(10);
        
        husbandryHBox.setSpacing(10);
        
        var advancementsPath = "file:///home/roberto/.minecraft/saves/worldo/advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";
        
        playerAdvancementsService = new PlayerAdvancementsService();
        
        playerAdvancements = playerAdvancementsService.load(advancementsPath);
    }
    
    @FXML
    private void switchToPrimary() throws IOException 
    {
        System.out.println("mediaView again: " + mediaView);
        
        App.setRoot("play-selected-world");
    }
    
    @FXML
    private void showHotTouristDestinationsData()
    {
        System.out.println("farto");
        
//        List<AdvancementItem> advancementItems = playerAdvancements.nether.advancements.hotTouristDestinations;
        List<AdvancementItem> advancementItems = null;
        
        StringBuilder text = new StringBuilder();
        
        for(AdvancementItem item : advancementItems)
        {
            text.append( item.toString() );
        }
        
        husbundryHavesTextArea.setText(text.toString() );
    }
}