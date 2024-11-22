



//TODO: move back/??????!!!???


package org.onebeartoe.games.gnuplot.map;
  
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.geometry.Point3D;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This application  verifies input files are in the correct format for a 
 * 2D, with labels, Gnuplot.
 * 
 * There are 2 valid formats
 * 
 * A) 
 *   x, y, "label"
 * 
 * B)
 *   x, y, z, "label"
 * 
 *      For B), the second value in the list is allowed to have the 
 *      of '~' or an integer value
 * 
 * Any line starting with a hash (#) is ignored.
 * 
 * Any blank line is also ignored.
 * 
 * Any other line in any other format is considered an error and is output.
 */
public class GnuplotDataVerification
{
    static boolean requireFourItems = false;

    static long minX = Long.MAX_VALUE;
    
    static long maxX = Long.MIN_VALUE;
    
    static long minZ = Long.MAX_VALUE;
    
    static long maxZ = Long.MIN_VALUE;    
    
    public static void main(String[] args) throws IOException 
    {
        System.out.println("Hello Gnuplot Data Verification world!\n");
    
        File pwd = new File(".");

        System.out.println("pwd = " + pwd.getAbsolutePath());
        
        boolean hasArguments = args.length > 0;
        
        String property = System.getProperty("requireFourItems");

        System.out.println("require 4 property = " + property);

        requireFourItems = Boolean.parseBoolean(property);
        
        System.out.println("requireFourItems = " + property);
                
        List<File> dataFiles;
                
        if(hasArguments)
        {
            dataFiles = Arrays.stream(args)
                            .map(s -> new File(s) )
                            .collect( Collectors.toList() );
        }
        else
        {
            Path startPath = Path.of(".");

            dataFiles = Files.walk(startPath)
                    .filter(Files::isRegularFile)
                    .map(p -> {return p.toFile(); })
//TODO: !!!!PUT THE CORRECT FILTER!!!!!
//.filter(p -> {return p.toString().endsWith("the-end.data");})                                        
                    .filter(p -> {return p.toString().endsWith(".data");})                    
                    .collect(Collectors.toList());
        }        
                
        GnuplotDataVerification app = new GnuplotDataVerification();
        
        app.verify(dataFiles);
        
        
        System.out.println("min x: " + minX);
        System.out.println("max x: " + maxX);

        // show the z min/max values
        System.out.println("min z: " + minZ);
        System.out.println("max z: " + maxZ);        
    }
    
    private void verify(List<File> dataFiles) throws IOException
    {
        var validationLists = new ArrayList<FileValidation>();
        
        for(File infile : dataFiles)
        {
            FileValidation errors = parseOneFile(infile);
            
            validationLists.add(errors);
        }
        
        for(FileValidation validation : validationLists)
        {
            System.out.println(validation.file + ":");
            
            for(ValidationEntry entry : validation.entries)
            {
                switch(entry)
                {
                    case DataFormatError e -> 
                    {
                        String message = "line " + e.lineNumber() + "\n" + e.line();
                        
                        System.out.println(message);
                        
                        playValidationFailureSound();
                    }                    
                    default -> System.err.println("error: no type found");
                }
            }
            
            System.out.println();
        }
    }
    
    private FileValidation parseOneFile(File infile) throws IOException
    {
        Path inpath = infile.toPath();
        
        var entries = new ArrayList<ValidationEntry>();

        List<String> lines = Files.readAllLines(inpath);           

        int lineNumber = 0;

        for(String line : lines)
        {
            ValidationEntry entry = null;

            if( line.startsWith("#") )
            {
                entry = new CommentEntry(lineNumber, line);
            }
            else if( line.trim().isBlank() )
            {
                entry = new BlankEntry();
            }
            else if( isValid(line).valid() )
            {
                entry = new ValidEntry(lineNumber, line);
            }
            else
            {
                entry = new DataFormatError(lineNumber, line);
            }

            entries.add(entry);

            lineNumber++;
        }   

        FileValidation validation = new FileValidation(infile, entries);
                
        return validation;
    }
    
    private void playValidationFailureSound()
    {
        // start the sound clip on a separate thread to allow the program to continue
        Runnable task = new Runnable()
        {
            @Override
            public void run() 
            {
                String path = "watcher/audio.1692864467107.wav";
                try 
                {
                    File raid = new File(path);

//TODO: fix this hack
if( !raid.exists() )
{
    //TODO: fix this hack
    path = "../dragon-fart-2000/maps/" + path;

    raid = new File(path);
}

                    Clip clip = AudioSystem.getClip();

                    clip.open(AudioSystem.getAudioInputStream(raid));

                    // this call returns immediately 
                    clip.start();

                    // so pause a little to let the clip finish playing
                    Thread.sleep( Duration.ofSeconds(1) );
                } 
                catch (Exception ex) 
                {
                    System.err.println("Couldn't open the stream");

                    ex.printStackTrace();

                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };
        task.run();
    }

    public MapMarker isValid(String line)
    {
        String [] split = line.split(",");
        
        String s1;
        String s2;
        String s3;
        String s4;
        
        
        
        if(split.length == 3)
        {
            s1 = split[0];
            s2 = "0";
            s3 = split[1];
            s4 = split[2];
        }
        else if(split.length == 4)
        {

            s1 = split[0];
            s2 = split[1].trim();
            s3 = split[2];
            s4 = split[3];

            if(s2.equals("~"))
            {
                s2 = "0";
            }
        }
        else
        {
            var message = "only 3 or 4 arguemnts are valid";
            
            throw new IllegalArgumentException(message);
        }
        
        var x = Double.valueOf(s1);
        var y = Double.valueOf(s2);
        var z = Double.valueOf(s3);
        
        String id = s4.trim();
        Point3D location = new Point3D(x,y,z);
        String description = "----"; 
        Boolean valid = true;
        
        var marker = new MapMarker(id, location, description, valid);
        
        return marker;
    }
    
    record FileValidation(File file, List<ValidationEntry> entries) {}
    
    sealed interface ValidationEntry permits DataFormatError, CommentEntry, ValidEntry, BlankEntry
    {
        
    }
    
    private record DataFormatError(int lineNumber, String line) implements ValidationEntry
    {
        
    }
    
    private record CommentEntry(int lineNumber, String line) implements ValidationEntry
    {
        
    }
    
    private record ValidEntry(int lineNumber, String line) implements ValidationEntry
    {
        
    }
    
    private record BlankEntry() implements ValidationEntry
    {
        
    }
}
