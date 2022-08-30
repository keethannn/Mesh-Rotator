package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class OFFMeshReader implements MeshReader{

	public HashSet<Polygon> read(String filename) throws WrongFileFormatException, FileNotFoundException
	{
		Scanner OFFfile = new Scanner(new File(filename));
		String line = OFFfile.nextLine();
		String[] coordinates;
		Polygon poly_from_vert;
		int num_vert;
		int num_face;
		ArrayList<Vertex> all_verts = new ArrayList<Vertex>();
		LinkedHashSet<Vertex> new_poly;
		HashSet<Polygon> all_polygons = new HashSet<Polygon>();
		
		if (!line.equals("OFF"))
		{
			throw new WrongFileFormatException();
		}
		
		line = OFFfile.nextLine();
		
		if (!line.matches("\s*[0-9]+\s+[0-9]+ 0"))
		{
			throw new WrongFileFormatException();
		}
		
		num_vert = Integer.parseInt(line.split(" ")[0]);
		num_face = Integer.parseInt(line.split(" ")[1]);
		
		for (int i = 0; i < num_vert; i++)
		{
			line = OFFfile.nextLine();
			
			if(!line.matches("\s*-?[0-9]+[.]?[0-9]*\s+-?[0-9]+[.]?[0-9]*\s+-?[0-9]+[.]?[0-9]*\s*"))
			{
				throw new WrongFileFormatException();
			}
			
			coordinates = line.split(" ");
			all_verts.add(new Vertex(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]), Double.parseDouble(coordinates[2])));
		}
		
		for (int i = 0; i < num_face; i++)
		{
			line = OFFfile.nextLine();
			
			if(!(line.matches("\s*[0-9]+(\s+[0-9]+)+((\s+[0-9]{1,3}){3})?\s*")))
			{
				throw new WrongFileFormatException();
			}
			
			new_poly = new LinkedHashSet<Vertex>();
			
			for (int j = 1; j < Integer.parseInt(line.split(" ")[0]) + 1; j++)
			{
				int index = Integer.parseInt(line.split(" ")[j]);
				if (index < 0 || index >= all_verts.size())
				{
					throw new WrongFileFormatException();
				}
				new_poly.add(all_verts.get(index));
			}
			
			poly_from_vert = new Polygon(new_poly);
			all_polygons.add(poly_from_vert);
		}
		if (OFFfile.hasNext())
		{
			throw new WrongFileFormatException();
		}
		return all_polygons;	
	}
}
