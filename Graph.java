import java.util.ArrayList;
import java.util.List;

/**
 * 有向图类，用于计算顶点a到b的简单路径数量，以及最短和最长路径
 */
public class Graph {
    private int vertices; // 顶点数量
    private List<List<Integer>> adjacencyList; // 邻接表
    
    /**
     * 构造函数
     * @param vertices 顶点数量
     */
    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }
    
    /**
     * 添加有向边
     * @param from 起始顶点
     * @param to 目标顶点
     */
    public void addEdge(int from, int to) {
        if (from >= 0 && from < vertices && to >= 0 && to < vertices) {
            adjacencyList.get(from).add(to);
        }
    }
    
    /**
     * 计算从顶点a到顶点b的简单路径数量
     * @param a 起始顶点
     * @param b 目标顶点
     * @return 简单路径数量
     */
    public int countSimplePaths(int a, int b) {
        if (a == b) return 0; // 同一个顶点，没有简单路径
        boolean[] visited = new boolean[vertices];
        return dfsCount(a, b, visited);
    }
    
    /**
     * DFS辅助方法，计算路径数量
     */
    private int dfsCount(int current, int target, boolean[] visited) {
        if (current == target) {
            return 1;
        }
        
        visited[current] = true;
        int count = 0;
        
        for (int neighbor : adjacencyList.get(current)) {
            if (!visited[neighbor]) {
                count += dfsCount(neighbor, target, visited);
            }
        }
        
        visited[current] = false;
        return count;
    }
    
    /**
     * 查找从a到b的所有简单路径
     * @param a 起始顶点
     * @param b 目标顶点
     * @return 所有简单路径的列表
     */
    public List<List<Integer>> findAllSimplePaths(int a, int b) {
        List<List<Integer>> allPaths = new ArrayList<>();
        if (a == b) return allPaths;
        
        boolean[] visited = new boolean[vertices];
        List<Integer> currentPath = new ArrayList<>();
        dfsFindAllPaths(a, b, visited, currentPath, allPaths);
        
        return allPaths;
    }
    
    /**
     * DFS辅助方法，查找所有路径
     */
    private void dfsFindAllPaths(int current, int target, boolean[] visited, 
                                 List<Integer> currentPath, List<List<Integer>> allPaths) {
        visited[current] = true;
        currentPath.add(current);
        
        if (current == target) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            for (int neighbor : adjacencyList.get(current)) {
                if (!visited[neighbor]) {
                    dfsFindAllPaths(neighbor, target, visited, currentPath, allPaths);
                }
            }
        }
        
        currentPath.remove(currentPath.size() - 1);
        visited[current] = false;
    }
    
    /**
     * 获取从a到b的最短简单路径
     * @param a 起始顶点
     * @param b 目标顶点
     * @return 最短路径，如果不存在则返回空列表
     */
    public List<Integer> getShortestPath(int a, int b) {
        List<List<Integer>> allPaths = findAllSimplePaths(a, b);
        if (allPaths.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Integer> shortest = allPaths.get(0);
        for (List<Integer> path : allPaths) {
            if (path.size() < shortest.size()) {
                shortest = path;
            }
        }
        return shortest;
    }
    
    /**
     * 获取从a到b的最长简单路径
     * @param a 起始顶点
     * @param b 目标顶点
     * @return 最长路径，如果不存在则返回空列表
     */
    public List<Integer> getLongestPath(int a, int b) {
        List<List<Integer>> allPaths = findAllSimplePaths(a, b);
        if (allPaths.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Integer> longest = allPaths.get(0);
        for (List<Integer> path : allPaths) {
            if (path.size() > longest.size()) {
                longest = path;
            }
        }
        return longest;
    }
    
    /**
     * 获取图的字符串表示
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("有向图（邻接表表示）:\n");
        for (int i = 0; i < vertices; i++) {
            sb.append(i).append(" -> ").append(adjacencyList.get(i)).append("\n");
        }
        return sb.toString();
    }
}