package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class PLYMeshReader implements MeshReader
{
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException, FileNotFoundException 
	{
		Scanner PLYfile = new Scanner(new File(filename));
		String line = PLYfile.nextLine();
		String[] values; 
		Polygon poly_from_vert;
		LinkedHashSet<Vertex> new_poly;
		HashSet<Polygon> all_polygons = new HashSet<Polygon>();
		ArrayList<Vertex> all_verts = new ArrayList<Vertex>();
		int num_vert;
		int num_face;
		
		if (!line.split(" ")[0].equals("ply"))
		{
			throw new WrongFileFormatException();
		}
		
		line = PLYfile.nextLine();
		
		if (!line.equals("format ascii 1.0"))
		{
			throw new WrongFileFormatException();
		}
		
		line = PLYfile.nextLine();
				
		if (!line.matches("element vertex [0-9]+"))
		{
			throw new WrongFileFormatException();
		}
		
		num_vert = Integer.parseInt(line.split(" ")[2]);
		
		line = PLYfile.nextLine();
		
		if (!line.equals("property float32 x"))
		{
			throw new WrongFileFormatException();
		}
		
		line = PLYfile.nextLine();
		
		if (!line.equals("property float32 y"))
		{
			throw new WrongFileFormatException();
		}
		
		line = PLYfile.nextLine();
		
		if (!line.equals("property float32 z"))
		{
			throw new WrongFileFormatException();
		}
		
		line = PLYfile.nextLine();
		
		if (!line.matches("element face [0-9]+"))
		{
			throw new WrongFileFormatException();
		}
		
		num_face = Integer.parseInt(line.split(" ")[2]);
		
		line = PLYfile.nextLine();
		
		if (!line.equals("property list uint8 int32 vertex_indices"))
		{
			throw new WrongFileFormatException();
		}
		
		line = PLYfile.nextLine();
		
		if (!line.equals("end_header"))
		{
			throw new WrongFileFormatException();
		}
		
		for (int i = 0; i < num_vert; i++)
		{
			if (!PLYfile.hasNext())
			{
				throw new WrongFileFormatException();
			}
			
			line = PLYfile.nextLine();
			
			if(!line.matches("\s*-?[0-9]*[.]?[0-9]+\s+-?[0-9]*[.]?[0-9]+\s+-?[0-9]*[.]?[0-9]+\s*"))
			{
				throw new WrongFileFormatException();
			}
			
			values = line.split(" ");
			all_verts.add(new Vertex(Double.parseDouble(values[0]), Double.parseDouble(values[1]), Double.parseDouble(values[2])));
		}
		
		for (int i = 0; i < num_face; i++)
		{
			if (!PLYfile.hasNext())
			{
				throw new WrongFileFormatException();
			}
			
			line = PLYfile.nextLine();
			
			if(!line.matches("\s*[0-9]+(\s+[0-9]+)+\s*"))
			{
				throw new WrongFileFormatException();
			}
			
			new_poly = new LinkedHashSet<Vertex>();
			
			for (int j = 1; j < line.split(" ").length; j++)
			{
				int index = Integer.parseInt(line.split(" ")[j]);
				if (index < 0 || index >= all_verts.size())
				{
					throw new WrongFileFormatException();
				}
				new_poly.add(all_verts.get(Integer.parseInt(line.split(" ")[j])));
			}
			poly_from_vert = new Polygon(new_poly);
			
			all_polygons.add(poly_from_vert);
		}
		if (PLYfile.hasNext())
		{
			throw new WrongFileFormatException();
		}
		return all_polygons;
	}	
}
