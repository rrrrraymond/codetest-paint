# Read Me First
This is about the project to build a "Console Paint Application".

# What is used in this project
* java
* maven only
* jupiter junit test
* Spring-boot is NOT used, to make a smaller simple application to resemble Paint Application
P.S Not all test cases are included, since the fillConnectedArea function is too complicated to test

# How to run this project
1. Check out this project
2. Run mvn clean install
3. java -jar codetest-paint-jar-with-dependencies.jar

# Sample input
C 40 15  
L 1 1 1 13  
L 3 2 10 2  
L 20 0 20 14  
R 5 2 8 10  
B 39 14 t  
B 5 2 c  
B 6 3 f  
bye  

# Remarks
This application is not fancy. No canvas or JFrame.  
This application is designed to be as simple as Lotus123 and pascal programming.  
If this is designed to be an application with fancy layout, it should not focus on "console like".  
Fancy layout application should consider to use javascript or nodeJS or reactJS.  

I hope you dun think this is copying homework, although the logic itself is very much unexplained.

# Technical Design
The fillConnectedArea function:
Original I want to build this function with recursive programming.  
1. Starting from the shaded point, the find out all it's 4 neighbour  
2. then create a set of connected points, and vice versa  
3. and exhaust all the points on the board.  

Later I realize is certainly not what your company is asking for,  
* As the points will be loop through more than 1 times,  
* and there seems to be no easy way to handle concave obstacle.  

So I change the logic to loop through the board from the top left hand corner. this ensures:  
1. there are only 2 directions, going right or going down, so the scanned points will not repeat  
2. the logic is similar to finding shortest path between 2 points. zzZ (old and popular question)  
3. visualizing the processing slowing (uncomment the debug code), the process is actually scanning the diagonal lines one by one, and group the points into different connected SET  
4. the process merge different SETs together, if it finds the 2 neighbour (up and left) are connected  
5. it should be O(1)  

Example:  (B 1 0 t)
Before filling:  

x
xxxxxxxxx
xxx
xxx
xxx
xxx
xxx
xxx
xxx
xxxxx
x
x
x

  
After step : 1  
ttt
tx
txxxxxxxxx
xxx
xxx
xxx
xxx
xxx
xxx
xxx
xxxxx
x
x
x

  
After step : 10
tttttttttttt
txttttttttt
txtxxxxxxxx
txtttxx
txtttxx
txtttxx
txtttxx
txtttxx
txttxx
txtxx
txxxxx
tx
x
x

  
After step : 53
tttttttttttttttttttttttttttttttttttttttt
txtttttttttttttttttttttttttttttttttttttt
txtxxxxxxxxttttttttttttttttttttttttttttt
txtttxxttttttttttttttttttttttttttttttt
txtttxxttttttttttttttttttttttttttttttt
txtttxxttttttttttttttttttttttttttttttt
txtttxxttttttttttttttttttttttttttttttt
txtttxxttttttttttttttttttttttttttttttt
txtttxxttttttttttttttttttttttttttttttt
txtttxxttttttttttttttttttttttttttttttt
txtttxxxxttttttttttttttttttttttttttttttt
txtttttttttttttttttttttttttttttttttttttt
txtttttttttttttttttttttttttttttttttttttt
txtttttttttttttttttttttttttttttttttttttt
tttttttttttttttttttttttttttttttttttttttt