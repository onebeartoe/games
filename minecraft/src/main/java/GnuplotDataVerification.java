
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This application  verifies input files are in the correct format for a 
 * 2D with labels Gnuplot.
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
        System.out.println("hello Java world!\n\n\n");
    
        List<File> dataFiles = Arrays.stream(args)
                        .map(s -> new File(s) )
                        .collect( Collectors.toList() );
                
        GnuplotDataVerification app = new GnuplotDataVerification();
        
        app.verify(dataFiles);
    }
    
    private void verify(List<File> dataFiles) throws IOException
    {
        List<FileValidation> validationLists = new ArrayList();
        
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
                    case DataFormatError e -> System.out.println("line " + e.lineNumber() + "\n" + e.line());
                    
                    default -> System.out.print("");
                }
            }
            
            System.out.println("\n");
        }
    }
    
    private FileValidation parseOneFile(File infile) throws IOException
    {
        Path inpath = infile.toPath();
        
        List<String> lines = Files.readAllLines(inpath);
        
        List<ValidationEntry> entries = new ArrayList();

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