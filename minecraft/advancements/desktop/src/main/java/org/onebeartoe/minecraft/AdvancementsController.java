package org.onebeartoe.minecraft;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import net.minecraft.advancements.PlayerAdvancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.PlayerAdvancementsService;
import org.json.simple.parser.ParseException;


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
    public VBox vBox;
    
    @FXML
    Button singlePlayerButton;
    
    @FXML
    TextArea netherHavesTextArea;
    
    @FXML
    TextArea netherHaveNotsTextArea;
    
    @FXML
    TextArea husbundryHavesTextArea;
    
    @FXML
    TextArea husbundryHaveNotsTextArea;
    
    @FXML
    TextArea advancementsHavesTextArea;
    
    @FXML
    TextArea advancementsHaveNotsTextArea;    
    
    @FXML
    HBox netherHBox;
    
    @FXML
    HBox husbandryHBox;
    
    @FXML
    Image completeCatalogueImage;
    
    @FXML
    ImageView hotTouristDestinationsImage;
    
    @FXML
    ImageView monstersHuntedImageView;
    
    private PlayerAdvancementsService playerAdvancementsService;
    
    private PlayerAdvancements playerAdvancements;
    
    @FXML
    public void initialize() throws URISyntaxException, IOException, ParseException
    {        
        netherHBox.setSpacing(10);
        
        husbandryHBox.setSpacing(10);

//TODO: find a way to inject this path value, for testing        
//      this next one is for testing
//        var advancementsPath = "/home/roberto/Versioning/owner/github/games/minecraft/advancements/cli/src/test/resources/minecraft/saves/1.20/advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";
//      These next two are temporary, until we dynamically load the player advancement JSON file
//        var advancementsPath = "/home/roberto/.minecraft/saves/seedro/advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";
//        var advancementsPath = "/home/roberto/.minecraft/saves/worldo/advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";
        var advancementsPath = "/home/roberto/.minecraft/saves/dragon-fart-2021/advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";

        playerAdvancementsService = new PlayerAdvancementsService();
        
        playerAdvancements = playerAdvancementsService.load(advancementsPath);
        
        displayHotTouristDestinationsData();
        
        displayCompleteCatalogueData();
        
        var monstersHuntedTooltip = new Tooltip("Monsters Hunted");
        
        monstersHuntedTooltip.setShowDelay(Duration.ZERO);
        
        var font = new Font(16);
                
        monstersHuntedTooltip.setFont(font);

        Tooltip.install(monstersHuntedImageView,  monstersHuntedTooltip);

        displayMonstersHuntedData();
    }
    
    @FXML
    private void switchToPrimary() throws IOException 
    {
        App.setRoot("play-selected-world");
    }

    @FXML
    private void displayBalancedDietData() throws Exception
    {
        PlayerAdvancement advancement = playerAdvancements.husbandry.balancedDiet;
                
        displayData(advancement, husbundryHavesTextArea, husbundryHaveNotsTextArea);
    }
    
    @FXML
    private void displayDiscoverEveryBiome()
    {
        var haves = new StringBuilder();        
        playerAdvancements.adventure.discoverEveryBiome.haves()
                .forEach(have ->
                {
                    haves.append(have);
                    haves.append("\n");
                });
//completeCatalogueImage.setTooltip(null);

        var haveNots = new StringBuilder();
        playerAdvancements.adventure.discoverEveryBiome.haveNots()
                        .forEach(not ->
                {
                    haveNots.append(not);
                    haveNots.append("\n");
                });
        
        advancementsHavesTextArea.setText(haves.toString());
        advancementsHavesTextArea.getStyleClass().add("title");
    
        advancementsHaveNotsTextArea.setText(haveNots.toString());
        advancementsHaveNotsTextArea.getStyleClass().add("title");
    }
    
    @FXML
    private void displayHotTouristDestinationsData()
    {
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

    private void displayCompleteCatalogueData() 
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

        husbundryHavesTextArea.setText(haves.toString());
        
        husbundryHaveNotsTextArea.setText(nots.toString());
    }

//TODO: reuse distplayData() once we know it is working    
    @FXML
    private void displayMonstersHuntedData() 
    {
        var haves = new StringBuilder();        
        playerAdvancements.adventure.monstersHunted.haves()
                .forEach(have ->
                {
                    haves.append(have);
                    haves.append(System.lineSeparator() );
                });

        var haveNots = new StringBuilder();
        playerAdvancements.adventure.monstersHunted.haveNots()
                        .forEach(not ->
                {
                    haveNots.append(not);
                    haveNots.append("\n");
                });
        
//TODO: Get fonts working again:
//TODO:    font adevnetrue monters killed font used towork!
        advancementsHavesTextArea.setText(haves.toString());
        advancementsHavesTextArea.getStyleClass().add("title");
    
        advancementsHaveNotsTextArea.setText(haveNots.toString());
        advancementsHaveNotsTextArea.getStyleClass().add("title");
    }
    
    @FXML
    private void displayTwoByTwoData()
    {
        PlayerAdvancement advancement = playerAdvancements.husbandry.twoByTwo;
                
        displayData(advancement, husbundryHavesTextArea, husbundryHaveNotsTextArea);
    }
    
    private void displayData(PlayerAdvancement advancement, TextArea havesTextArea, TextArea haveNotsTextArea)
    {
        var haves = new StringBuilder();        
        advancement.haves()
                .forEach(have ->
                {
                    haves.append(have);
                    haves.append(System.lineSeparator() );
                });

        var haveNots = new StringBuilder();
        advancement.haveNots()
                        .forEach(not ->
                {
                    haveNots.append(not);
                    haveNots.append("\n");
                });
        
        havesTextArea.setText(haves.toString());
        havesTextArea.getStyleClass().add("title");
    
        haveNotsTextArea.setText(haveNots.toString());
        haveNotsTextArea.getStyleClass().add("title");        
    }
}
