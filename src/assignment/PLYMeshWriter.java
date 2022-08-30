package assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class PLYMeshWriter implements MeshWriter{
	
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
		
		file.write("ply\nformat ascii 1.0\nelement vertex " + all_verts.size() + "\nproperty float32 x\n"
				+ "property float32 y\nproperty float32 z\nelement face " + Polygons.size() + 
				"\nproperty list uint8 int32 vertex_indices\nend_header\n");
		
		for(Vertex v: all_verts)
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
