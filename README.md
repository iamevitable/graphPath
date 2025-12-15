# 图路径查找器

一个用于在有向图中查找路径的Java实现

## 功能特性
- 查找两个顶点间的所有简单路径
- 计算简单路径的数量
- 获取两个顶点间的最短路径
- 获取两个顶点间的最长路径

## 使用示例
```java
// 创建包含5个顶点的图
Graph graph = new Graph(5);

// 添加边
graph.addEdge(0, 1);
graph.addEdge(1, 2);
graph.addEdge(2, 3);
graph.addEdge(3, 4);

// 查找从0到4的所有路径
List<List<Integer>> paths = graph.findAllSimplePaths(0, 4);
```
