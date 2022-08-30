package assignment;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import org.junit.jupiter.api.Test;

class AssignmentJUnit {

	@Test
	void testVertexEquals() {
		Vertex v1 = new Vertex (1.0, 2.0, 3.0);
		Vertex v2 = new Vertex (1.0, 2.0, 3.0);
		assertEquals(v1, v2);	
	}
	
	@Test
	void testEquals2() {
		Vertex v1 = new Vertex (1.0, 2.0, 3.0);
		Vertex v2 = new Vertex (0.0, 2.0, 3.0);
		assertEquals(v2.equals(v1), false);	
	}
	
	@Test
	void testEquals3() {
		Vertex v1 = new Vertex (1.0, 2.0, 4.0);
		Vertex v2 = new Vertex (0.0, 2.0, 3.0);
		assertEquals(v2.equals(v1), false);	
	}
	
	@Test
	void testHashCode()
	{
		Vertex v1 = new Vertex (1.0, 2.0, 3.0);
		Vertex v2 = new Vertex (1.0, 2.0, 3.0);
		assertEquals(v1.hashCode(), v2.hashCode());
	}
	
	@Test
	void testRotatex()
	{
		Vertex initial = new Vertex (3.0, 4.0, 2.0);
		initial.rotateXAxis(Math.PI/2);
		Vertex result = new Vertex (3.0, -2.0, 4.0);
		assertEquals(initial, result);
	}
	
	@Test
	void testRotatey()
	{
		Vertex initial = new Vertex (15.0, 4.0, 7.0);
		initial.rotateYAxis(Math.PI/3);
		Vertex result = new Vertex (13.56218, 4.0, -9.49038);
		assertEquals(initial, result);
	}
	
	@Test
	void testRotatez()
	{
		Vertex initial = new Vertex (23.42, 31.21, 26.32);
		initial.rotateZAxis(Math.PI/5);
		Vertex result = new Vertex (0.60240, 39.01535, 26.32);
		assertEquals(initial, result);
	}
	
	@Test
	void testVertextoString()
	{
		Vertex v = new Vertex (1.0, 2.0, 3.0);
		assertEquals(v.toString(), "1.0 2.0 3.0");
	}
	
	@Test
	void testPolygonEquals()
	{
		Vertex a1 = new Vertex(0.0, 1.0, 2.0);
		Vertex a2 = new Vertex(3.0, 4.0, 5.0);
		LinkedHashSet<Vertex> a = new LinkedHashSet<Vertex>();
		a.add(a1);
		a.add(a2);
		Polygon pa = new Polygon(a);
		
		Vertex b1 = new Vertex(3.0, 4.0, 5.0);
		Vertex b2 = new Vertex(0.0, 1.0, 2.0);
		LinkedHashSet<Vertex> b = new LinkedHashSet<Vertex>();
		b.add(b1);
		b.add(b2);
		Polygon pb = new Polygon(b);
		
		assertEquals(pa, pb);
	}
	
	@Test
	void testPolygonhashcode()
	{
		Vertex a1 = new Vertex(0.0, 1.0, 2.0);
		Vertex a2 = new Vertex(3.0, 4.0, 5.0);
		LinkedHashSet<Vertex> a = new LinkedHashSet<Vertex>();
		a.add(a1);
		a.add(a2);
		Polygon pa = new Polygon(a);
		
		Vertex b1 = new Vertex(3.00000, 4.0, 5.0);
		Vertex b2 = new Vertex(0.0, 1.0, 2.0);
		LinkedHashSet<Vertex> b = new LinkedHashSet<Vertex>();
		b.add(b1);
		b.add(b2);
		Polygon pb = new Polygon(b);
		
		assertEquals(pa.hashCode(), pb.hashCode());
	}
	
	@Test
	void testPolygontransform()
	{
		Vertex a1 = new Vertex(0.0, 1.0, 2.0);
		Vertex a2 = new Vertex(3.0, 4.0, 5.0);
		LinkedHashSet<Vertex> a = new LinkedHashSet<Vertex>();
		a.add(a1);
		a.add(a2);
		Polygon pa = new Polygon(a);
		
		pa.rotateZAxis(Math.PI/2);
		
		Vertex b1 = new Vertex(-1.0, 0.0, 2.0);
		Vertex b2 = new Vertex(-4.0, 3.0, 5.0);
		LinkedHashSet<Vertex> b = new LinkedHashSet<Vertex>();
		b.add(b1);
		b.add(b2);
		Polygon pb = new Polygon(b);
	
		assertEquals(pa, pb);
	}
	
	@Test
	void testMeshEquals() throws IOException
	{
		Mesh mesh = new Mesh();
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		LinkedHashSet<Vertex> verts_to_poly = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> a_poly = new LinkedHashSet<Vertex>();
		Vertex v1 = new Vertex(1.0, 2.0, 3.0);
		Vertex v2 = new Vertex(2.0, 4.0, 2.0);
		Vertex v3 = new Vertex(2.5, 3.0, 5.0);
		verts_to_poly.add(v1);
		verts_to_poly.add(v2);
		verts_to_poly.add(v3);
		Vertex a1 = new Vertex(-3.0, 2.0, 1.0);
		Vertex a2 = new Vertex(-6.0, 4.0, -5.0);
		Vertex a3 = new Vertex(-3.0, -2.0, 3.0);
		a_poly.add(a1);
		a_poly.add(a2);
		a_poly.add(a3);
		a_poly.add(v3);
		Polygon random_poly = new Polygon(verts_to_poly);
		Polygon another_poly = new Polygon(a_poly);
		polygons.add(random_poly);
		polygons.add(another_poly);
		mesh.polygons = polygons;
		
		mesh.rotateZAxis(Math.PI/2);
		
		mesh.setWriter(new OBJMeshWriter()); 
		mesh.writeToFile("src/assignment/files/car_rotated.obj"); 
		
		Mesh mesh2 = new Mesh();
		HashSet<Polygon> polygons2 = new HashSet<Polygon>();
		LinkedHashSet<Vertex> verts_to_poly2 = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> va_poly = new LinkedHashSet<Vertex>();
		Vertex v21 = new Vertex(-2.0, 1.0, 3.0);
		Vertex v22 = new Vertex(-4.0, 2.0, 2.0);
		Vertex v23 = new Vertex(-3.0, 2.5, 5.0);
		verts_to_poly2.add(v21);
		verts_to_poly2.add(v22);
		verts_to_poly2.add(v23);
		Vertex va1 = new Vertex(-2.0, -3.0, 1.0);
		Vertex va2 = new Vertex(-4.0, -6.0, -5.0);
		Vertex va3 = new Vertex(2.0, -3.0, 3.0);
		va_poly.add(va1);
		va_poly.add(va2);
		va_poly.add(va3);
		va_poly.add(v23);
		Polygon random_poly2 = new Polygon(verts_to_poly2);
		Polygon random_vpoly = new Polygon(va_poly);
		polygons2.add(random_poly2);
		polygons2.add(random_vpoly);
		mesh2.polygons = polygons2;
		
		assertEquals(mesh, mesh2);
	}
	
	@Test
	void TestOBJReader() throws FileNotFoundException, WrongFileFormatException
	{
		Mesh mesh = new Mesh();
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		LinkedHashSet<Vertex> verts_to_poly = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> verts_to_poly_2 = new LinkedHashSet<Vertex>();
		Vertex v1 = new Vertex(1.0, 2.0, 3.0);
		Vertex v2 = new Vertex(2.0, 4.0, 2.0);
		Vertex v3 = new Vertex(2.5, 3.0, 5.0);
		Vertex v4 = new Vertex(4.5, 6.0, 2.3);
		verts_to_poly.add(v1);
		verts_to_poly.add(v2);
		verts_to_poly.add(v3);
		verts_to_poly_2.add(v2);
		verts_to_poly_2.add(v3);
		verts_to_poly_2.add(v4);
		Polygon random_poly = new Polygon(verts_to_poly);
		Polygon random_poly_2 = new Polygon(verts_to_poly_2);
		polygons.add(random_poly);
		polygons.add(random_poly_2);
		mesh.polygons = polygons;
		
		Mesh reading = new Mesh();
		reading.setReader(new OBJMeshReader());
		reading.readFromFile("src/assignment/files/testing.obj");
		
		assertEquals(mesh, reading);
	}
	
	@Test
	void TestOFFReader() throws FileNotFoundException, WrongFileFormatException
	{
		Mesh mesh = new Mesh();
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		LinkedHashSet<Vertex> verts_to_poly = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> verts_to_poly_2 = new LinkedHashSet<Vertex>();
		Vertex v1 = new Vertex(1.0, 2.0, 3.0);
		Vertex v2 = new Vertex(2.0, 4.0, 2.0);
		Vertex v3 = new Vertex(2.5, 3.0, 5.0);
		Vertex v4 = new Vertex(4.5, 6.0, 2.3);
		verts_to_poly.add(v1);
		verts_to_poly.add(v2);
		verts_to_poly.add(v3);
		verts_to_poly_2.add(v2);
		verts_to_poly_2.add(v3);
		verts_to_poly_2.add(v4);
		Polygon random_poly = new Polygon(verts_to_poly);
		Polygon random_poly_2 = new Polygon(verts_to_poly_2);
		polygons.add(random_poly);
		polygons.add(random_poly_2);
		mesh.polygons = polygons;
	
		Mesh reading = new Mesh();
		reading.setReader(new OFFMeshReader());
		reading.readFromFile("src/assignment/files/testing2.off.txt");

		assertEquals(mesh, reading);
		
//		String regex = "[0-9]+( [0-9]+)+(( [0-9]{1,3}){3})?";
//		assertEquals("3 1 2 3".matches(regex), true);
	}
	
	@Test
	void TestOFFWriter() throws WrongFileFormatException, IOException
	{
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		LinkedHashSet<Vertex> verts_to_poly = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> verts_to_poly_2 = new LinkedHashSet<Vertex>();
		Vertex v1 = new Vertex(1.0, 2.0, 3.0);
		Vertex v2 = new Vertex(2.0, 4.0, 2.0);
		Vertex v3 = new Vertex(2.5, 3.0, 5.0);
		Vertex v4 = new Vertex(4.5, 6.0, 2.3);
		verts_to_poly.add(v1);
		verts_to_poly.add(v2);
		verts_to_poly.add(v3);
		verts_to_poly_2.add(v2);
		verts_to_poly_2.add(v3);
		verts_to_poly_2.add(v4);
		Polygon random_poly = new Polygon(verts_to_poly);
		Polygon random_poly_2 = new Polygon(verts_to_poly_2);
		polygons.add(random_poly);
		polygons.add(random_poly_2);
		
		Mesh writer = new Mesh();
		writer.setWriter(new OFFMeshWriter());
		writer.polygons = polygons;
		writer.writeToFile("src/assignment/files/writing.off");
		
		Mesh reader = new Mesh();
		reader.setReader(new OFFMeshReader());
		reader.readFromFile("src/assignment/files/writing.off");
		
		assertEquals(writer, reader);
	}
	
	@Test
	void TestOBJWriter() throws WrongFileFormatException, IOException
	{
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		LinkedHashSet<Vertex> verts_to_poly = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> verts_to_poly_2 = new LinkedHashSet<Vertex>();
		Vertex v1 = new Vertex(1.0, 2.0, 3.0);
		Vertex v2 = new Vertex(2.0, 4.0, 2.0);
		Vertex v3 = new Vertex(2.5, 3.0, 5.0);
		Vertex v4 = new Vertex(4.5, 6.0, 2.3);
		verts_to_poly.add(v1);
		verts_to_poly.add(v2);
		verts_to_poly.add(v3);
		verts_to_poly_2.add(v2);
		verts_to_poly_2.add(v3);
		verts_to_poly_2.add(v4);
		Polygon random_poly = new Polygon(verts_to_poly);
		Polygon random_poly_2 = new Polygon(verts_to_poly_2);
		polygons.add(random_poly);
		polygons.add(random_poly_2);
		
		Mesh writer = new Mesh();
		writer.setWriter(new OBJMeshWriter());
		writer.polygons = polygons;
		writer.writeToFile("src/assignment/files/writing2.obj");
		
		Mesh reader = new Mesh();
		reader.setReader(new OBJMeshReader());
		reader.readFromFile("src/assignment/files/writing2.obj");
		
		assertEquals(writer, reader);
	}
	
	@Test
	void TestPLYWriter() throws WrongFileFormatException, IOException
	{
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		LinkedHashSet<Vertex> verts_to_poly = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> verts_to_poly_2 = new LinkedHashSet<Vertex>();
		Vertex v1 = new Vertex(1.0, 2.0, 3.0);
		Vertex v2 = new Vertex(2.0, 4.0, 2.0);
		Vertex v3 = new Vertex(2.5, 3.0, 5.0);
		Vertex v4 = new Vertex(4.5, 6.0, 2.3);
		verts_to_poly.add(v1);
		verts_to_poly.add(v2);
		verts_to_poly.add(v3);
		verts_to_poly_2.add(v2);
		verts_to_poly_2.add(v3);
		verts_to_poly_2.add(v4);
		Polygon random_poly = new Polygon(verts_to_poly);
		Polygon random_poly_2 = new Polygon(verts_to_poly_2);
		polygons.add(random_poly);
		polygons.add(random_poly_2);
		
		Mesh writer = new Mesh();
		writer.setWriter(new PLYMeshWriter());
		writer.polygons = polygons;
		writer.writeToFile("src/assignment/files/writing3.txt");
		
		Mesh reader = new Mesh();
		reader.setReader(new PLYMeshReader());
		reader.readFromFile("src/assignment/files/writing3.txt");
		
		assertEquals(writer, reader);
	}
	
	@Test
	void testException1() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new OBJMeshReader());
			reading.readFromFile("src/assignment/files/ex1.obj");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void testException2() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new OBJMeshReader());
			reading.readFromFile("src/assignment/files/ex2.obj");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void testException3() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new OBJMeshReader());
			reading.readFromFile("src/assignment/files/ex3.obj");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void testException4() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new OBJMeshReader());
			reading.readFromFile("src/assignment/files/ex4.obj");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException1() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex1.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException2() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex2.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException3() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex3.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException4() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex4.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException5() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex5.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException6() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex6.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException7() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex7.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException8() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex8.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException9() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex9.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException10() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex10.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException11() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex11.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException12() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex12.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException13() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex13.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException14() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex14.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void plyException15() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new PLYMeshReader());
			reading.readFromFile("src/assignment/files/plyex15.txt");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void objException5() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new OBJMeshReader());
			reading.readFromFile("src/assignment/files/objer2.obj");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void offException1() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new OFFMeshReader());
			reading.readFromFile("src/assignment/files/offex1");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void offException2() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new OFFMeshReader());
			reading.readFromFile("src/assignment/files/offex2");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void offException3() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new OFFMeshReader());
			reading.readFromFile("src/assignment/files/offex3");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void offException4() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new OFFMeshReader());
			reading.readFromFile("src/assignment/files/offex4");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void offException5() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new OFFMeshReader());
			reading.readFromFile("src/assignment/files/offex5");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	@Test
	void offException6() throws FileNotFoundException {
		try
		{
			Mesh reading = new Mesh();
			reading.setReader(new OFFMeshReader());
			reading.readFromFile("src/assignment/files/offex6");
		}
		catch(WrongFileFormatException ex) 
		{
			assertEquals(ex.message, "Wrong file type");
		}
	}
	
	
	
}
