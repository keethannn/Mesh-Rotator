package assignment;

public class Vertex extends GraphicalObject{
	
	double x, y, z;
	
	Vertex(double inputx, double inputy, double inputz)
	{
		this.x = inputx;
		this.y = inputy;
		this.z = inputz;
		
	}

	public void transform(double[][] matrix) 
	{
		double x, y, z;
		
		x = this.x * matrix[0][0] + this.y * matrix[0][1] + this.z * matrix[0][2];
		y = this.x * matrix[1][0] + this.y * matrix[1][1] + this.z * matrix[1][2];
		z = this.x * matrix[2][0] + this.y * matrix[2][1] + this.z * matrix[2][2];
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int hashCode()
	{
		return (int)(this.x + 10 * this.y + 100 * this.z);
	}
	
	public boolean equals(Object check_vertex)
	{
		Vertex check_vert = (Vertex)check_vertex;
		double this_x, this_y, this_z, vert_x, vert_y, vert_z;
		
		
		this_x = Math.round(this.x * 100000.0)/100000.0;
		this_y = Math.round(this.y * 100000.0)/100000.0;
		this_z = Math.round(this.z * 100000.0)/100000.0;
		vert_x = Math.round(check_vert.x * 100000.0)/100000.0;
		vert_y = Math.round(check_vert.y * 100000.0)/100000.0;
		vert_z = Math.round(check_vert.z * 100000.0)/100000.0;
		
		return (this_x == vert_x && this_y == vert_y && this_z == vert_z);
	}
	
	public String toString()
	{
		return new String(Double.toString(this.x) + " " + Double.toString(this.y) + " " + Double.toString(this.z));
	}
}
