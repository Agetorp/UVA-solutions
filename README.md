# UVA-solutions
My solutions to problems from UVA, given as assignments in the course Advanced Programming

## Problems in the courses order
##### 10300 - Ecological Premium
Ignore animals, just multiply size with environment-friendliness
##### 591 - Box of Bricks
Calculate the average height, which is the height all stacks should have. Then sum the difference between the average height and the height of each stack higher than average.
##### 10878 - Decode the tape
Each row represents a character. Find this character by replacing 'o' with 1 and ' ' with 0, then convert to ASCII.
##### 839 - Not so Mobile
Build the tree, then recursively check that all subtrees are balanced. This could be solved without building the tree, though this is how I solved it during the course.
##### 10608 - Friends
Use Disjoint set and Union-Find.
##### 10307 - Killing Aliens in Borg Maze
Create a graph that represents the maze, then use BFS on each Alien and the start positions. Doing this will get you the distance between each node you must visit. After this you can use Kruskals or Prims algorithm to get the shortest path.
##### 374 - Big Mod
Since I used Java, there was a build in function to solve this.
##### 11054 - Wine trading in Gergovia
If the first house wants to sell 5, the cost to go to next house is 5. The same goes if the first house wants to buy 5 instead, so keep track of the balance (either negative or positive) when traveling to the next house, the cost goes up with the absolute value of your balance for each house visited.
##### 10340 - All in All
Take the first char in the first word, search for a match in the second word until found. Then repeat for the next char in the first word, continuing from the position of the last match in the second word.
##### 10943 - How do you add?
Use dynamic programming. Loop from i = 0 to i = 100, and fill in memory[i][1] = 1, memory[1][i] = i.
Then save the result of each calculation so that you never calculate the same one again.
##### 116 - Unidirectional TSP
The way to solve this is to fill in the matrix from right to left. For each node, save the minimum distance and what node that distance is to. When you have done this for each column, find the node with the lowest distance in the leftmost column. If there are more than one with the same distance, take the one highest up to get the lexicographically smallest path. From this node, traverse through the path you saved during the first part and thats the correct shortest path.
##### 185 - Roman Numerals
The roman part is really easy, but the arabic is harder. I solved this (in a complex manner) by representing the three numbers with pointers to integers, then looping through each possible value for each letter. After each possible value-combination the equation is tested to see if it works. Skip all cases where two numbers are equal and so on.
##### 920 - Sunny Mountains
Sort the coordinates on x and keep track of the highest peak you have visited. For all coordinates higher than this, calculate a triangle from this coordinate and the previous to get the angle of the peak. From this you can calculate the triangle from the peak reaching down to the height of the previously highest peak. Then you get the length of the sunny side of that triangle (or peak).
##### 10078 - The Art Gallery
The solution to this problem is to check if a polygon is convex, and this can be done using the
Counter-Clockwise Test (Left-turn) and Clockwise-Test(Right-turn).
##### 10245 - The Closest Pair Problem
I solved this one with brute force. Silly I know, but it was fast enough to pass.
