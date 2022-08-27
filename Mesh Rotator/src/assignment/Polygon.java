package assignment;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class Polygon extends GraphicalObject {
	
	LinkedHashSet<Vertex> vertices;

	Polygon(LinkedHashSet<Vertex> vertexes)
	{
		this.vertices = new LinkedHashSet<Vertex>();
		this.vertices.addAll(vertexes); 	
	}
	
	public void transform(double[][] matrix) 
	{
		Iterator<Vertex> it_vert = this.vertices.iterator();
		Vertex v;
		double x, y, z;
		while(it_vert.hasNext())
		{
	        v = it_vert.next();
	        x = v.x * matrix[0][0] + v.y * matrix[0][1] + v.z * matrix[0][2];
			y = v.x * matrix[1][0] + v.y * matrix[1][1] + v.z * matrix[1][2];
			z = v.x * matrix[2][0] + v.y * matrix[2][1] + v.z * matrix[2][2];
			v.x = x;
			v.y = y;
			v.z = z;
		}
	}
	
	public int hashCode()
	{
		int total = 0;
		for (Vertex v: this.vertices)
		{
			total += (int)(v.x + 10 * v.y + 100 * v.z);
		}
		return total;
	}
	
	public boolean equals(Object another_polygon)
	{
		Polygon new_poly = (Polygon) another_polygon;
		return (new_poly.vertices.equals(this.vertices));
	}

}
