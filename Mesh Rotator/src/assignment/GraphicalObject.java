package assignment;

import java.lang.Math;

public abstract class GraphicalObject {
	
	public abstract void transform(double[][] matrix);
	
	public void rotateXAxis(double rotation)
	{
		double[][] rotation_matrix = {{1, 0, 0}, 
				                      {0, Math.cos(rotation), -Math.sin(rotation)},
				                      {0, Math.sin(rotation), Math.cos(rotation)}};
		transform(rotation_matrix);
		
	}
	
	public void rotateYAxis(double rotation)
	{
		double[][] rotation_matrix = {{Math.cos(rotation), 0, Math.sin(rotation)}, 
                					  {0, 1, 0},
                					  {-Math.sin(rotation), 0, Math.cos(rotation)}};
		transform(rotation_matrix);
	}
	
	public void rotateZAxis(double rotation)
	{
		double[][] rotation_matrix = {{Math.cos(rotation), -Math.sin(rotation), 0}, 
				  					  {Math.sin(rotation), Math.cos(rotation), 0},
				  					  {0, 0, 1}};
		transform(rotation_matrix);
	}
}