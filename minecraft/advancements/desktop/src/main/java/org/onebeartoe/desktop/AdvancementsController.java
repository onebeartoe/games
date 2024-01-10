package org.onebeartoe.desktop;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import org.json.simple.parser.ParseException;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.PlayerAdvancementsService;
import org.onebeartoe.minecraft.advancements.v1_20.MinecraftWildAdvancementsService;


//TODO: add a test to make sure advancement images are not null and present on the GUI
//TODO:     improve the FXML test to make sure the URI are acutaly available
//TODO:         on the classpapth/modulepath

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
    TextArea netherHavesTextArea;
    
    @FXML
    TextArea netherHaveNotsTextArea;
    
    @FXML
    TextArea husbundryCompleteCatelogueHavesTextArea;
    
    @FXML
    TextArea husbundryCompleteCatelogueHaveNotsTextArea;
    
    @FXML
    HBox netherHBox;
    
    @FXML
    HBox husbandryHBox;
    
    @FXML
    Image completeCatalogueImage;
    
    @FXML
    ImageView hotTouristDestinationsImage;
    
//    private MinecraftWildAdvancementsService implementation;
    
    private PlayerAdvancementsService playerAdvancementsService;
    
    private PlayerAdvancements playerAdvancements;
    
    @FXML
    public void initialize() throws URISyntaxException, IOException, ParseException
    {     
//        implementation = new MinecraftWildAdvancementsService();
        
        netherHBox.setSpacing(10);
        
        husbandryHBox.setSpacing(10);
        
        var advancementsPath = "/home/roberto/.minecraft/saves/worldo/advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";
        
        playerAdvancementsService = new PlayerAdvancementsService();
        
        playerAdvancements = playerAdvancementsService.load(advancementsPath);
        
        showHotTouristDestinationsData();
        
        showCompleteCatalogueData();
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
        System.out.println("jfalkjlsdj");    
        
        StringBuilder haves = new StringBuilder();        
                
        playerAdvancements.nether.hotTouristDestinations.haves()
            .forEach(have -> 
            {
                haves.append(have);
                haves.append("\n");
            });
                
        netherHavesTextArea.setText(haves.toString() );
        
        StringBuilder haveNots = new StringBuilder();
        
        playerAdvancements.nether.hotTouristDestinations.haveNots()
            .forEach(not -> 
            {
                haveNots.append(not);
                haveNots.append("\n");
            });
        
        netherHaveNotsTextArea.setText(haveNots.toString() );
    }

    private void showCompleteCatalogueData() 
    {
        StringBuilder haves = new StringBuilder();        
        playerAdvancements.husbandry.aCompleteCatelogue.haves()
                .forEach(have ->
                {
                    haves.append(have);
                    haves.append("\n");
                });
        
        StringBuilder nots = new StringBuilder();
        playerAdvancements.husbandry.aCompleteCatelogue.haveNots()
                .forEach(not ->
                {
                    nots.append(not);
                    nots.append("\n");
                });
        
        
        husbundryCompleteCatelogueHavesTextArea.setText(haves.toString());
        
        husbundryCompleteCatelogueHaveNotsTextArea.setText(nots.toString());
    }
}
