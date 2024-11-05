



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
        var valid = true;
        
        String[] split = line.split(",");
        
        Integer x = null;
        
        Integer y = null;
                
        Integer z = 0;
        
        String id = null;
        
        if(requireFourItems && split.length != 4)
        {
            valid = false;
        }
        else if(split.length < 2)
        {
            valid = false;
        }
        else
        {
            try
            {
                var s1 = split[0].trim();
                x = Integer.valueOf(s1);
                
                if(minX > x)
                {
                    minX = x;                    
                }
                
                if(maxX < x)
                {
                    maxX = x;
                }
                
                boolean lengthIs4 = split.length == 4;

                var s2 = split[1].trim();

                // allow the tilde character for s2 if there are 4 items
                if(s2.equals("~"))
                {
                    if(!lengthIs4)
                    {
                        var message = "~ tilde found, but not at position 2";

                        throw new IllegalArgumentException(message);
                    }
                    
                    y = 0;
                    
//jkljlkjkjl                    z = Integer.valueOf(s2);
                }
                else
                {
                    // otherwise verify s2 is an integer
                    y = Integer.valueOf(s2);
                }

                int lastIndex = 2;
                
                if(lengthIs4)
                {            
                    lastIndex = 3;                  

                    // validate the third item in the list
                    var s3 = split[2].trim();
                    z = Integer.valueOf(s3);
                    
                    if(minZ > z)
                    {
                        minZ = z;
                    }
                    
                    if(maxZ < z)
                    {
                        maxZ = z;     
                    }                    
                }

                var lastStr = split[lastIndex].trim();

                if(lastStr.length() < 2
                        || !lastStr.startsWith("\"")
                        || !lastStr.endsWith("\"") )
                {
                    // the last item should be a string begining and ending in a double quote

                    throw new Exception("the label has bad formatting: " + line);
                }
                
                id = lastStr;

            }
            catch(Exception e)
            {
                valid = false;

                var mesage = "\nerror with: " + Arrays.toString(split) 
                        + "\n" + e.getClass() + " - " + e.getMessage();

                System.out.println(mesage);
            }            
        }
        
        if(x == null)
        {
            var message = "x is null in line:\n" + line;
            
            throw new IllegalArgumentException(message);
        }

        if(y == null)
        {
            var message = "y is null in line:\n" + line;
            
            throw new IllegalArgumentException(message);
        }        
        
        Point3D point = new Point3D(x, y, z);
        
        String description = null;
        
        MapMarker mapMarker = new MapMarker(id, point, description, valid);
        
        return mapMarker;
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
