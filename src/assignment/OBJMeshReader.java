package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class OBJMeshReader implements MeshReader{

	public HashSet<Polygon> read(String filename) throws WrongFileFormatException, FileNotFoundException 
	{
		Scanner OBJfile = new Scanner(new File(filename));
		String line;
		Polygon poly_from_vert;
		ArrayList<Vertex> all_verts = new ArrayList<Vertex>();
		HashSet<Polygon> all_polys = new HashSet<Polygon>();
		String v_regex = "\s*v(\s+-?[0-9]+[.]?[0-9]*){3}\s*";
		String e_regex = "^v((\\s-?\\d+(\\.\\d+)?)(E-?\\d+)?){3}";
		String f_regex = "\s*f(\s+[0-9]+)+\s*";
		
		if(OBJfile.hasNextLine())
		{
			line = OBJfile.nextLine();
			if (!line.matches(v_regex) && !line.matches(f_regex) && !line.matches(e_regex))
			{
				throw new WrongFileFormatException();
			}
		}
		else
		{
			return all_polys;
		}
		
		while (line.split(" ")[0].equals("v"))
		{	
			if (!line.matches(v_regex) && !line.matches(e_regex))
			{
				System.out.println(line);
				throw new WrongFileFormatException();
			}
			all_verts.add(new Vertex(Double.parseDouble(line.split(" ")[1]),Double.parseDouble(line.split(" ")[2]), Double.parseDouble(line.split(" ")[3])));	
			if(OBJfile.hasNextLine())
			{
				line = OBJfile.nextLine();
			}
			else
			{
				return all_polys;
			}
		}
		while (line.split(" ")[0].equals("f"))
		{
			LinkedHashSet<Vertex> new_poly = new LinkedHashSet<Vertex>();
			for (int i = 1; i < line.split(" ").length; i++)
			{
				int index = Integer.parseInt(line.split(" ")[i]);
				if (1 > index || index > all_verts.size())
				{	
					throw new WrongFileFormatException();
				}
				new_poly.add(all_verts.get(Integer.parseInt(line.split(" ")[i]) - 1));
			}
			poly_from_vert = new Polygon(new_poly);
			all_polys.add(poly_from_vert);
			
			if(OBJfile.hasNextLine())
			{
				line = OBJfile.nextLine();
			}
			else
			{
				return all_polys;
			}
		}
		throw new WrongFileFormatException();		
	}

}
