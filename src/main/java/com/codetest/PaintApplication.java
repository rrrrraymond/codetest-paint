package com.codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.codetest.bean.PaintBoard;
import com.codetest.bean.PaintPoint;
import com.codetest.service.PaintBoardService;

public class PaintApplication {
	public static void main(String[] args) {
		PaintBoard paintBoard = null;
		
		try (BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in))){
	        // Reading data using readLine
			String line;
			System.out.println("Please input command:");
			while ((line = reader.readLine()) != null && !line.equals("bye")) {
	        	// Printing the read line
	        	paintBoard = processCommand(line, paintBoard);
	        	paintBoard.print();
	        	System.out.println("Please input command:");
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 

		/** for debug only **/
//		paintBoard = new PaintBoard(40,15);
//		
//		PaintBoardService paintBoardService = new PaintBoardService();
//		paintBoardService.addLine(paintBoard, new PaintPoint(1,1), new PaintPoint(1,13));
//		paintBoardService.addLine(paintBoard, new PaintPoint(3,2), new PaintPoint(10,2));
////		paintBoardService.addLine(paintBoard, new PaintPoint(20,0), new PaintPoint(20,14));
//		
//		paintBoardService.addRectangle(paintBoard, new PaintPoint(5,2), new PaintPoint(8,10));
//		
//		paintBoardService.fillConnectedArea(paintBoard, new PaintPoint(1,1), 't');
//		
//		paintBoard.print();
	}
	
	private static PaintBoard processCommand(String line, PaintBoard paintBoard) {
		String[] args = line.split(" ");
		try{
			if (args[0].equals("C") && args.length >= 3) {
				return new PaintBoard(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
			} else if (args[0].equals("L") && args.length >= 5) {
				PaintBoardService paintBoardService = new PaintBoardService();
				paintBoardService.addLine(paintBoard, 
						new PaintPoint(Integer.parseInt(args[1]),Integer.parseInt(args[2])), 
						new PaintPoint(Integer.parseInt(args[3]),Integer.parseInt(args[4])));
				return paintBoard;
			} else if (args[0].equals("R") && args.length >= 5) {
				PaintBoardService paintBoardService = new PaintBoardService();
				paintBoardService.addRectangle(paintBoard, 
						new PaintPoint(Integer.parseInt(args[1]),Integer.parseInt(args[2])), 
						new PaintPoint(Integer.parseInt(args[3]),Integer.parseInt(args[4])));
				return paintBoard;
			} else if (args[0].equals("B") && args.length >= 4) {
				PaintBoardService paintBoardService = new PaintBoardService();
				paintBoardService.fillConnectedArea(paintBoard, 
						new PaintPoint(Integer.parseInt(args[1]),Integer.parseInt(args[2])),
						args[3].charAt(0));
				return paintBoard;
			}
		} catch (NumberFormatException e) {
			System.err.println("NumberFormatException :: please check input");
			System.err.print(e);
		}
		
		return paintBoard;
	}
}
