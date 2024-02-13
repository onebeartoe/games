
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This application  verifies input files are in the correct format for a 
 * 2D, with labels, Gnuplot.
 * 
 * The format is 
 * 
 *   x, y, "label"
 * 
 * Any line starting with a hash (#) is ignored.
 * 
 * Any blank line is also ignored.
 * 
 * Any other line in any other format is considered an error and is output.
 */
public class GnuplotDataVerification
{
    public static void main(String[] args) throws IOException 
    {
        System.out.println("Hello Gnuplot Data Verification world!\n");
    
        boolean hasArguments = args.length > 0;
        
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
                    .filter(p -> {return p.toString().endsWith(".data");})                    
                    .collect(Collectors.toList());
        }        
                
        GnuplotDataVerification app = new GnuplotDataVerification();
        
        app.verify(dataFiles);
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
                    default -> System.out.print("");
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
            else if( isValid(line) )
            {
                entry = new ValidEntry(lineNumber, line);
            }
            else if( line.trim().isBlank() )
            {
                entry = new BlankEntry();
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

    private boolean isValid(String line) 
    {
        var valid = true;
        
        String[] split = line.split(",");
        
        try
        {
            var s1 = split[0].trim();
            Integer x = Integer.valueOf(s1);
            
            var s2 = split[1].trim();
            Integer y = Integer.valueOf(s2);
            
            var s3 = split[2].trim();
            if(s3.length() < 2
                    || !s3.startsWith("\"")
                    || !s3.endsWith("\"") )
            {
                // the last item should be a string begining and ending in a double quote
                
                throw new Exception("the label has bad formatting: " + line);
            }
            
        }
        catch(Exception e)
        {
            valid = false;
        }
        
        return valid;
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