package assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class OBJMeshWriter implements MeshWriter{

	public void write(String filename, HashSet<Polygon> Polygons) throws IOException
	{
		ArrayList<Vertex> all_verts = new ArrayList<Vertex>();
		FileWriter file = new FileWriter(filename);
		
		for (Polygon p: Polygons)
		{
			for (Vertex v: p.vertices)
			{
				if (!all_verts.contains(v))
				{
					all_verts.add(v);
				}
			}
		}
		
		for (Vertex v : all_verts)
		{
			double this_x, this_y, this_z;
				
			this_x = Math.round(v.x * 100000.0)/100000.0;
			this_y = Math.round(v.y * 100000.0)/100000.0;
			this_z = Math.round(v.z * 100000.0)/100000.0;
			file.write("v " + this_x + " " + this_y + " " + this_z + "\n" );
		}
		
		for(Polygon p: Polygons)
		{
			file.write("f ");
			
			for (Vertex v: p.vertices)
			{
				file.write((all_verts.indexOf(v) + 1) + " ");
			}
			
			file.write("\n");
		}
		
		file.close();
	}

}
