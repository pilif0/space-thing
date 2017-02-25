package jamsandwich.spacething.engine.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Utils {

	static final BigDecimal TWO = BigDecimal.valueOf(2);
	public static final RoundingMode ROUND_HALF_UP = RoundingMode.HALF_UP;
	
	public static double fromRadians(double a) {
		return a*180/Math.PI;
	}
	
	public static double toRadians(double a) {
		return a*Math.PI/180;
	}
	
	public static BigDecimal distance(double[] x, double[] y) {
		BigDecimal x1 = BigDecimal.valueOf(x[0]);
		BigDecimal y1 = BigDecimal.valueOf(x[1]);
		BigDecimal x2 = BigDecimal.valueOf(y[0]);
		BigDecimal y2 = BigDecimal.valueOf(y[1]);
		BigDecimal d = ((x1.subtract(x2)).pow(2)).add((y1.subtract(y2)).pow(2));
		d = sqrt(d, d.scale());
		return d;
	}
	
	public static double[] centre(double[] points []) {
		double[] p = new double[2];
		for(int i = 0; i < points.length; i++) {
			p[0] += points[i][0];
			p[1] += points[i][1];
		}
		p[0] /= points.length;
		p[1] /= points.length;
		return p;
	}
	
	public static BigDecimal magnitude(double d []) {
		BigDecimal x = BigDecimal.valueOf(d[0]);
		BigDecimal y = BigDecimal.valueOf(d[1]);
		BigDecimal n = x.multiply(x).add(y.multiply(y));
		n = sqrt(n, n.scale());
		return n;
	}
	
	public static BigDecimal normalize(double[] v) []{
		BigDecimal x = BigDecimal.valueOf(v[0]);
		BigDecimal y = BigDecimal.valueOf(v[1]);
		BigDecimal m = magnitude(v);
		x = x.divide(m, 200, ROUND_HALF_UP);
		y = y.divide(m, 200, ROUND_HALF_UP);
		return new BigDecimal[] {x,y};
	}
	
	public static double toDoubles(BigDecimal[] n) []{
		double[] a = new double[n.length];
		for(int i = 0; i < n.length; i++) {
			a[i] = n[i].doubleValue();
		}
		return a;
	}
	
	public static BigDecimal sqrt(BigDecimal A, final int SCALE) {
		BigDecimal x0 = new BigDecimal(0);
		BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
		while(!x0.equals(x1)) {
			x0 = x1;
			x1 = A.divide(x0, SCALE, ROUND_HALF_UP);
			x1 = x1.add(x0);
			x1 = x1.divide(TWO, SCALE, ROUND_HALF_UP);
		}
		return x1;
	}
	
	public static BigDecimal projectSprite(double[][] s, double[][] v) [][]{
		ArrayList<double[][]> triangles = new ArrayList<double[][]>();
		double[] c = {0,0};
		for(int i = 0; i < s.length; i++) {
			c[0] += s[i][0];
			c[1] += s[i][1];
		}
		c[0] /= s.length;
		c[1] /= s.length;
		for(int i = 0; i < s.length; i++) {
			triangles.add(new double[][] {c,s[i],s[(i+1)%s.length]});
		}
		ArrayList<BigDecimal[][]> vs = new ArrayList<BigDecimal[][]>();
		for(double[][] u : triangles) {
			vs.add(projectT(u,v));
		}
		return intersectParallel(vs);
	}
	
	public static BigDecimal projectT(double[][] triangle, double[][] v) [][]{
		if(triangle.length == 3) {
			ArrayList<BigDecimal[][]> vs = new ArrayList<BigDecimal[][]>();
			for(int i = 0; i < 3; i++) {
				double[][] o = {{0,0},{triangle[i][0],triangle[i][1]}};
				double[][] u = {{0,0},{triangle[(i+1)%3][0],triangle[(i+1)%3][1]}};
				vs.add(minus(projectL(u,v), projectL(o,v)));
			}
			return intersectParallel(vs);
		}
		return null;
	}
	
	public static BigDecimal projectL(double[][] u, double[][] v) [][]{
		double[] d1 = {u[0][0]-v[0][0],u[0][1]-v[0][1]};
		double[] d2 = {u[1][0]-v[0][0],u[1][1]-v[0][1]};
		double[] d3 = {v[1][0]-v[0][0],v[1][1]-v[0][1]};
		BigDecimal[] p1 = project(d1,d3);
		BigDecimal[] p2 = project(d2,d3);
		p1[0] = p1[0].add(BigDecimal.valueOf(v[0][0]));
		p1[1] = p1[1].add(BigDecimal.valueOf(v[0][1]));
		p2[0] = p2[0].add(BigDecimal.valueOf(v[0][0]));
		p2[1] = p2[1].add(BigDecimal.valueOf(v[0][1]));
		BigDecimal[][] x = {p1,p2};
		return x;
	}

	public static BigDecimal project(double u [], double v []) []{
		BigDecimal[] v1 = {BigDecimal.valueOf(u[0]),BigDecimal.valueOf(u[1])};
		BigDecimal[] v2 = normalize(v);
		BigDecimal d = (v1[0].multiply(v2[0])).add(v1[1].multiply(v2[1]));
		BigDecimal[] x = {v2[0].multiply(d),v2[1].multiply(d)};
		return x;
	}
	
	public static BigDecimal intersectParallel(ArrayList<BigDecimal[][]> b) [][]{
		BigDecimal[] Min = {b.get(0)[0][0],b.get(0)[0][1]};
		BigDecimal[] Max = {b.get(0)[0][0],b.get(0)[0][1]};
		for(BigDecimal[][] v : b) {
			if(v.length == 2) {
				for(int i = 0; i < 2; i++) {
					for(int j = 0; j < 2; j++) {
						if(v[i][j].compareTo(Min[j])<0) {
							Min[j] = v[i][j];
						}
						if(v[i][j].compareTo(Max[j])>0) {
							Max[j] = v[i][j];
						}
					}
				}
			}
		}
		return new BigDecimal[][] {Min,Max};
	}
	
	public static BigDecimal[][] minus(BigDecimal[][] u, BigDecimal[][] v) {
		return new BigDecimal[][] {{v[1][0],v[1][1]},{u[1][0],u[1][1]}};
	}
	
	public static boolean parallel(double u [][], double v [][]) {
		BigDecimal[] d1 = normalize(direction(u));
		BigDecimal[] d2 = normalize(direction(v));
		return d1[0].multiply(d2[0]).add(d1[1].multiply(d2[1])).compareTo(BigDecimal.ONE)==0;
	}
	
	public static double[] direction(double[][] v) {
		return new double[] {v[1][0]-v[0][0],v[1][1]-v[0][1]};
	}
	
	public static BigDecimal overlap(BigDecimal[][] u, BigDecimal[][] v) {
		if(parallel(toDoubles(u),toDoubles(v))) {
			double[][] w = {toDoubles(u)[0],toDoubles(v)[1]};
			BigDecimal x = magnitude(direction(toDoubles(u))).add(magnitude(direction(toDoubles(v)))).subtract(magnitude(direction(w))); 
			return x.compareTo(BigDecimal.ZERO)>=0?x:BigDecimal.ZERO;
		}
		return BigDecimal.ZERO;
	}

	public static double[][] orthogonal(double[][] v) {
		return new double[][] {{v[0][0],v[0][1]},{(v[1][1]-v[0][1])+v[0][0],(v[0][0]-v[1][0])+v[0][1]}};
	}
	
	public static double[][] toDoubles(BigDecimal[][] b) {
		double[][] d = new double[b.length][b[0].length];
		for(int i = 0; i < b.length; i++) {
			BigDecimal[] b1 = b[i];
			for(int j = 0; j < b1.length; j++) {
				d[i][j] = b[i][j].doubleValue();
			}
		}
		return d;
	}
}
