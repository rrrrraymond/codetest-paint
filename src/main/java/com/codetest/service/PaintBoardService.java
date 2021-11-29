package com.codetest.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.codetest.bean.PaintBoard;
import com.codetest.bean.PaintPoint;

public class PaintBoardService {
	public void addLine(PaintBoard paintBoard, PaintPoint start, PaintPoint end) {
		if (paintBoard == null) {
			System.err.printf("Paint Board is null");
			return;
		}
		if (!paintBoard.isValidPoint(start)) {
			System.err.printf("Input Point is out of Boundary. (%d, %d)", start.getX(), start.getY());
			return;
		}
		if (!paintBoard.isValidPoint(end)) {
			System.err.printf("Input Point is out of Boundary. (%d, %d)", end.getX(), end.getY());
			return;
		}
		if (start.getX() != end.getX() && start.getY() != end.getY()) {
			System.err.printf("Input Points are not horizontal or vertical.");
			return;
		} else if (start.getX() == end.getX()){
			paintBoard.updatePaintBaord(
				IntStream.rangeClosed(Math.min(start.getY(), end.getY()), Math.max(start.getY(), end.getY()))
					.mapToObj(y -> new PaintPoint(start.getX(), y))
					.collect(Collectors.toMap(point -> point, point -> point))
				);
		} else if (start.getY() == end.getY()){
			paintBoard.updatePaintBaord(
					IntStream.rangeClosed(Math.min(start.getX(), end.getX()), Math.max(start.getX(), end.getX()))
						.mapToObj(x -> new PaintPoint(x, start.getY()))
						.collect(Collectors.toMap(point -> point, point -> point))
					);
			}
	}
	
	public void addRectangle(PaintBoard paintBoard, PaintPoint vertex1, PaintPoint vertex2) {
		if (paintBoard == null) {
			System.err.printf("Paint Board is null");
			return;
		}
		if (!paintBoard.isValidPoint(vertex1)) {
			System.err.printf("Input Point is out of Boundary. (%d, %d)", vertex1.getX(), vertex1.getY());
			return;
		}
		if (!paintBoard.isValidPoint(vertex2)) {
			System.err.printf("Input Point is out of Boundary. (%d, %d)", vertex2.getX(), vertex2.getY());
			return;
		}
		addLine(paintBoard, vertex1, new PaintPoint(vertex1.getX() ,vertex2.getY()));
		addLine(paintBoard, new PaintPoint(vertex1.getX() ,vertex2.getY()), vertex2);
		addLine(paintBoard, vertex1, new PaintPoint(vertex2.getX() ,vertex1.getY()));
		addLine(paintBoard, new PaintPoint(vertex2.getX() ,vertex1.getY()), vertex2);
	}
	
	public void fillConnectedArea(PaintBoard paintBoard, PaintPoint point, char color) {
		if (paintBoard == null) {
			System.err.printf("Paint Board is null");
			return;
		}
		if (!paintBoard.isValidPoint(point)) {
			System.err.printf("Input Point is out of Boundary. (%d, %d)", point.getX(), point.getY());
			return;
		}
		final PaintPoint lookupPoint = paintBoard.getPaintPoints().get(point);
		final Character originalColor = lookupPoint == null ? null : lookupPoint.print();
		
		final Set<Set<PaintPoint>> connectedPointGroups = new HashSet<Set<PaintPoint>>();
		for (int distance = 0; distance < paintBoard.getHeight() + paintBoard.getWidth() - 1; distance++ ) {
			for (int cursorX = 0; cursorX <= Math.min(distance, paintBoard.getWidth()-1); cursorX ++) {
				PaintPoint thisPaintPoint = new PaintPoint(cursorX, distance-cursorX, color);
				if (isSameColor(paintBoard.getPaintPoints().get(thisPaintPoint), originalColor)) {
					Set<PaintPoint> connectedPointGroup = null;
					for (Set<PaintPoint> pointGroup : connectedPointGroups) {
						if (pointGroup.contains(new PaintPoint(cursorX, distance-cursorX-1))) {
							pointGroup.add(thisPaintPoint);
							connectedPointGroup = pointGroup;
						}
						if (cursorX > 0 && pointGroup.contains(new PaintPoint(cursorX-1, distance-cursorX))) {
							if (connectedPointGroup == null) {
								pointGroup.add(thisPaintPoint);
								connectedPointGroup = pointGroup;
							} else {
								connectedPointGroup.addAll(pointGroup);
								connectedPointGroups.remove(pointGroup);
							}
							break;
						}
					}
					if (connectedPointGroup == null) {
						connectedPointGroups.add(new HashSet<PaintPoint>(Arrays.asList(thisPaintPoint)));
					}
				}
			}
			
			/** for debug only **/
//			connectedPointGroups
//				.parallelStream()
//				.forEach(set -> {
//					if (set.contains(point)) {
//						paintBoard.updatePaintBaord(
//								set.parallelStream().collect(Collectors.toMap(p -> p, p -> p)));
//					}
//				});
//			paintBoard.print();
//			System.out.println("After step : " + distance);
		}
		connectedPointGroups
			.parallelStream()
			.forEach(set -> {
				if (set.contains(point)) {
					paintBoard.updatePaintBaord(
							set.parallelStream().collect(Collectors.toMap(p -> p, p -> p)));
				}
		});
	}
	
	private boolean isSameColor(PaintPoint point, Character color) {
		return (color == null && point == null) || (point != null && color != null && point.print() == color);
	}
}
