# 🧩 LostAF -  Maze Generation and Pathfinding (Java)

This Java project implements maze generation and pathfinding algorithms from scratch, featuring:

- Custom Disjoint Set data structure implementation
- Randomized maze generation using Kruskal's algorithm
- Pathfinding with Dijkstra's algorithm

## 🔍 Overview

This project demonstrates graph theory algorithms in a practical, visual application. Using a custom implementation of the Disjoint Set data structure, I built both maze generation and path-finding capabilities.

## ✨ Features

### 🔗 Disjoint Set Implementation
- Custom implementation of the Union-Find data structure
- Path compression and union-by-rank optimizations
- Efficient representation of sets for maze generation

### 🗺️ Maze Generation
- Random maze generation using Kruskal's algorithm
- The maze is represented as a graph where:
  - Vertices are cells in the maze
  - Edges represent possible passages between cells
  - A spanning tree of this graph forms a perfect maze with exactly one path between any two points

### 🔎 Pathfinding
- Implementation of Dijkstra's algorithm to find the shortest path through the maze
- Efficient priority queue handling for optimal path discovery
- Visual representation of the discovered path

## ⚙️ Technical Details

### Core Components:
- **DisjointSet**: A Union-Find implementation with path compression
- **MazeGenerator**: Uses Kruskal's algorithm with the DisjointSet to create random mazes
- **PathFinder**: Implements Dijkstra's algorithm to find optimal paths

### Implementation Highlights:
- Efficient memory management using appropriate data structures
- Performance optimizations in the Disjoint Set implementation
- Clean separation of concerns between generation and pathfinding

## 📝 Development Notes

The testing framework and build system were provided by faculty, but all algorithm implementations and core logic were developed by me.

## 🚀 Future Improvements

- Add additional maze generation algorithms (e.g., Prim's, Recursive Backtracking)
- Implement alternative pathfinding algorithms (A*, BFS, DFS) for comparison
- Add visualization options for the generation and solving processes
- Support for 3D mazes

## ▶️ Running the Project

To run the application:
1. Find and run `Main.java` inside the package
2. This will launch a program that generates and solves mazes

**Note:** If IntelliJ fails to run (e.g., cannot find symbol `ExtrinsicMinPQ`), remember to refresh Gradle manually through the Gradle tool window (in the upper right by default).

