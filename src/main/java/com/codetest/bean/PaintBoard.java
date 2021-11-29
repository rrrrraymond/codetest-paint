package com.codetest.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaintBoard {
	
	private final int width;
	private final int height;
	private final Map<PaintPoint, PaintPoint> paintPoints;
	private final static char WHITE_SPACE = Character.DIRECTIONALITY_WHITESPACE;
	// private final static char WHITE_SPACE = 'E';
	
	public PaintBoard(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		paintPoints = new ConcurrentHashMap<PaintPoint, PaintPoint>();
	}
	
	public void print() {
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				PaintPoint point = paintPoints.get(new PaintPoint(x, y));
				System.out.print(point == null ? WHITE_SPACE : point.print());
			}
			System.out.println();
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isValidPoint(PaintPoint paintPoint) {
		return paintPoint.getX() < this.width && paintPoint.getY() < this.height;
	}

	public void updatePaintBaord(Map<PaintPoint, PaintPoint> newPaintPoints) {
		paintPoints.putAll(newPaintPoints);
	}

	public Map<PaintPoint, PaintPoint> getPaintPoints() {
		return paintPoints;
	}
	
}
