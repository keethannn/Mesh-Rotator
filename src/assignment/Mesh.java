package assignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Mesh extends GraphicalObject{
	
	HashSet<Polygon> polygons;
	MeshReader reader;
	MeshWriter writer;
		
	public void setReader(MeshReader stream)
	{
		this.reader = stream;
	}
	
	public void setWriter(MeshWriter stream)
	{
		this.writer = stream;
	}
	
	public void readFromFile(String filename) throws FileNotFoundException, WrongFileFormatException
	{
		this.polygons = new HashSet<Polygon>();
		this.polygons.addAll(this.reader.read(filename));
	}
	
	public void writeToFile(String filename) throws IOException
	{
		this.writer.write(filename, this.polygons);
	}
	
	public void transform(double[][] matrix) 
	{

		//		Iterator<Polygon> it_poly = this.polygons.iterator();
//		Polygon p;
//		Iterator<Vertex> it_vert;
//		Vertex v;
//		while (it_poly.hasNext())
//		{
//			p = it_poly.next();
//			it_vert = p.vertices.iterator();
//			while(it_vert.hasNext())
//			{
//				v = it_vert.next();
//		        v.transform(matrix);	
//			}
//		}
		ArrayList<Vertex> all_verts = new ArrayList<Vertex>();
		
		for(Polygon p: this.polygons)
		{
			for(Vertex v: p.vertices)
			{
				if (!all_verts.contains(v))
				{
					all_verts.add(v);
					v.transform(matrix);
				}
			}
		}
	}
	
	public boolean equals(Object another_mesh)
	{
		Mesh check_mesh = (Mesh)another_mesh;
		return (check_mesh.polygons.equals(this.polygons));
	}
	
	
	

}
