package malincok.playground.escape;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PipeLoader
{
    public static void loadPipe(int[] indexes) throws IOException
    {
        Escape.solution(loadPipe(indexes[0], indexes.length));
        System.out.println("L LIKE LOOSER");
    
    }
    protected static Movement loadPipe(int index, int pipesCount) throws IOException
    {
        File file = new File("src\\main\\resources\\pipe"+index);
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        List<String> lines = new ArrayList();
        String st;
        while ((st = br.readLine()) != null)
        {
            lines.add(st);
        }
        lines.remove(lines.size()-1);
        Collections.reverse(lines);
        
        List<Coordinate> spaces = new ArrayList();
        Coordinate finish = null;
        for(int y=0; y<lines.size(); y++)
        {
            String line = lines.get(y);
            for(int x=0; x<line.length(); x++)
            {
                char character = line.charAt(x);
                if(character == ' ' || character == '.')
                    spaces.add(new Coordinate(x,y));
                else if(character == 'X')
                    finish = new Coordinate(x,y);
            }
        }
        
        return new Movement(spaces, finish, pipesCount);
    }
    
}
