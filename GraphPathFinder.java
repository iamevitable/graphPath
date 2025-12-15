import java.util.List;

/**
 * 主程序：演示有向图中顶点a到b的简单路径计算
 */
public class GraphPathFinder {
    public static void main(String[] args) {
        System.out.println("=== 有向图简单路径计算器 ===\n");
        
        // 示例1：简单有向图
        System.out.println("示例1：简单有向图");
        Graph graph1 = createSimpleGraph();
        testGraph(graph1, 0, 3);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // 示例2：更复杂的有向图
        System.out.println("示例2：复杂有向图");
        Graph graph2 = createComplexGraph();
        testGraph(graph2, 0, 5);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // 示例3：测试不存在路径的情况
        System.out.println("示例3：不存在路径的情况");
        testGraph(graph2, 5, 0);
    }
    
    /**
     * 创建简单有向图示例
     */
    private static Graph createSimpleGraph() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(1, 2);
        
        System.out.println(graph);
        return graph;
    }
    
    /**
     * 创建复杂有向图示例
     */
    private static Graph createComplexGraph() {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(3, 4); // 增加环路可能性
        
        System.out.println(graph);
        return graph;
    }
    
    /**
     * 测试图的路径计算功能
     */
    private static void testGraph(Graph graph, int a, int b) {
        System.out.println("从顶点 " + a + " 到顶点 " + b + " 的分析：");
        
        // 计算简单路径数量
        int pathCount = graph.countSimplePaths(a, b);
        System.out.println("简单路径数量: " + pathCount);
        
        if (pathCount > 0) {
            // 查找所有路径
            List<List<Integer>> allPaths = graph.findAllSimplePaths(a, b);
            System.out.println("所有简单路径:");
            for (int i = 0; i < allPaths.size(); i++) {
                System.out.println("  路径" + (i+1) + ": " + allPaths.get(i));
            }
            
            // 获取最短路径
            List<Integer> shortestPath = graph.getShortestPath(a, b);
            System.out.println("最短路径: " + shortestPath + " (长度: " + shortestPath.size() + ")");
            
            // 获取最长路径
            List<Integer> longestPath = graph.getLongestPath(a, b);
            System.out.println("最长路径: " + longestPath + " (长度: " + longestPath.size() + ")");
        } else {
            System.out.println("不存在从顶点 " + a + " 到顶点 " + b + " 的简单路径");
        }
    }
}