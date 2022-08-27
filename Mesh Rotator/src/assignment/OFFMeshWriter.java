package assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class OFFMeshWriter implements MeshWriter
{
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
		
		file.write("OFF\n" + all_verts.size() + " " + Polygons.size() + " 0\n" );
		
		for (Vertex v: all_verts)
		{
			file.write(v.x + " " + v.y + " " + v.z + "\n");
		}
		
		for (Polygon p: Polygons)
		{
			file.write(p.vertices.size() + " ");
			for (Vertex v: p.vertices)
			{
				file.write(all_verts.indexOf(v) + " ");
			}
			file.write("\n");
		}
		file.close();
	}
}
