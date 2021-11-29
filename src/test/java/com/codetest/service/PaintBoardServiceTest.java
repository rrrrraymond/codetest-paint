package com.codetest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codetest.bean.PaintBoard;
import com.codetest.bean.PaintPoint;

public class PaintBoardServiceTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@BeforeEach
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	@Test
	public void testAddLine_1() {
		PaintBoardService paintBoardService = new PaintBoardService();
		
		PaintBoard paintBoard = new PaintBoard(40,50);
		paintBoardService.addLine(paintBoard, new PaintPoint(1,1), new PaintPoint(1,4));
//		assertEquals("Line added", outContent.toString());
		Map<PaintPoint, PaintPoint> paintPoints = paintBoard.getPaintPoints();
		assertEquals(4, paintPoints.size());
		assertTrue(paintPoints.containsKey(new PaintPoint(1,1)));
		assertTrue(paintPoints.containsKey(new PaintPoint(1,2)));
		assertTrue(paintPoints.containsKey(new PaintPoint(1,3)));
		assertTrue(paintPoints.containsKey(new PaintPoint(1,4)));
	}
	
	@Test
	public void testAddLine_2() {
		PaintBoardService paintBoardService = new PaintBoardService();
		
		PaintBoard paintBoard = new PaintBoard(40,50);
		paintBoardService.addLine(paintBoard, new PaintPoint(2,2), new PaintPoint(2,5));
//		assertEquals("Line added", outContent.toString());
		Map<PaintPoint, PaintPoint> paintPoints = paintBoard.getPaintPoints();
		assertEquals(4, paintPoints.size());
		assertTrue(paintPoints.containsKey(new PaintPoint(2,2)));
		assertTrue(paintPoints.containsKey(new PaintPoint(2,3)));
		assertTrue(paintPoints.containsKey(new PaintPoint(2,4)));
		assertTrue(paintPoints.containsKey(new PaintPoint(2,5)));
	}
	
	@Test
	public void testAddLine_3() {
		PaintBoardService paintBoardService = new PaintBoardService();
		
		PaintBoard paintBoard = new PaintBoard(40,50);
		paintBoardService.addLine(paintBoard, new PaintPoint(1,1), new PaintPoint(1,60));
		assertEquals("Input Point is out of Boundary. (1, 60)", errContent.toString());
	}
	
	@Test
	public void testAddLine_4() {
		PaintBoardService paintBoardService = new PaintBoardService();
		
		PaintBoard paintBoard = new PaintBoard(40,50);
		paintBoardService.addLine(paintBoard, new PaintPoint(1,1), new PaintPoint(2,2));
		assertEquals("Input Points are not horizontal or vertical.", errContent.toString());
	}
	
	@Test
	public void testAddRectangle_1() {
		PaintBoardService paintBoardService = new PaintBoardService();
		
		PaintBoard paintBoard = new PaintBoard(40,50);
		paintBoardService.addRectangle(paintBoard, new PaintPoint(1,1), new PaintPoint(3,4));
//		assertEquals("Line added", outContent.toString());
		Map<PaintPoint, PaintPoint> paintPoints = paintBoard.getPaintPoints();
		assertEquals(10, paintPoints.size());
		assertTrue(paintPoints.containsKey(new PaintPoint(1,1)));
		assertTrue(paintPoints.containsKey(new PaintPoint(1,2)));
		assertTrue(paintPoints.containsKey(new PaintPoint(1,3)));
		assertTrue(paintPoints.containsKey(new PaintPoint(1,4)));
		
		assertTrue(paintPoints.containsKey(new PaintPoint(2,1)));
		assertTrue(paintPoints.containsKey(new PaintPoint(2,4)));
		
		assertTrue(paintPoints.containsKey(new PaintPoint(3,1)));
		assertTrue(paintPoints.containsKey(new PaintPoint(3,2)));
		assertTrue(paintPoints.containsKey(new PaintPoint(3,3)));
		assertTrue(paintPoints.containsKey(new PaintPoint(3,4)));
	}
	
	@Test
	public void testAddRectangle_2() {
		PaintBoardService paintBoardService = new PaintBoardService();
		
		PaintBoard paintBoard = new PaintBoard(40,50);
		paintBoardService.addRectangle(paintBoard, new PaintPoint(2,2), new PaintPoint(6,4));
//		assertEquals("Line added", outContent.toString());
		Map<PaintPoint, PaintPoint> paintPoints = paintBoard.getPaintPoints();
		assertEquals(12, paintPoints.size());
		assertTrue(paintPoints.containsKey(new PaintPoint(2,2)));
		assertTrue(paintPoints.containsKey(new PaintPoint(2,3)));
		assertTrue(paintPoints.containsKey(new PaintPoint(2,4)));
		
		assertTrue(paintPoints.containsKey(new PaintPoint(3,2)));
		assertTrue(paintPoints.containsKey(new PaintPoint(3,4)));
		assertTrue(paintPoints.containsKey(new PaintPoint(4,2)));
		assertTrue(paintPoints.containsKey(new PaintPoint(4,4)));
		assertTrue(paintPoints.containsKey(new PaintPoint(5,2)));
		assertTrue(paintPoints.containsKey(new PaintPoint(5,4)));
		
		assertTrue(paintPoints.containsKey(new PaintPoint(6,2)));
		assertTrue(paintPoints.containsKey(new PaintPoint(6,3)));
		assertTrue(paintPoints.containsKey(new PaintPoint(6,4)));
	}
	
	@Test
	public void testAddRectangle_3() {
		PaintBoardService paintBoardService = new PaintBoardService();
		
		PaintBoard paintBoard = new PaintBoard(40,50);
		paintBoardService.addRectangle(paintBoard, new PaintPoint(1,1), new PaintPoint(1,60));
		assertEquals("Input Point is out of Boundary. (1, 60)", errContent.toString());
	}
	
}
