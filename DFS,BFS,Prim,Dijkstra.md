## 图论中的优先级搜索——DFS,BFS,Prim,Dijkstra

 

![此博文包含图片](http://simg.sinajs.cn/blog7style/images/common/sg_trans.gif)	  在图算法中经常要执行遍历每个顶点和每条边的操作，即图搜索。许多图算法都以图搜索为基础，如2-着色问题、连通性计算基于深度优先搜寻（depth-first search, DFS），而无权最短路径则基于广度优先搜索（breadth-first search， BFS）。基于搜索的算法还包括计算最小生成树的Prim算法以及计算最短路径的Dijkstra算法，这4个算法就是今天的主题。我写这篇文章无意证明算法的正确和有效性，而是希望能揭示图搜索算法背后统一的思想。

 

[DFS](http://en.wikipedia.org/wiki/Depth-first_search)非常简单，在搜索的过程中一旦遇到一个未标记的顶点，就沿着这个顶点继续搜索，直至遍历完所有可达的顶点。DFS具有回溯的性质，由此形成的搜索树很瘦很高。执行DFS时，同时会维护一个时间戳（time-stamp），以此记录每个顶点被搜索到的顺序，DFS形成的搜索树包含不同类型的边（树边，回边，下边以及交叉边），边的性质能通过时间戳识别出来。

[BFS](http://en.wikipedia.org/wiki/Breadth-first_search)则相反，遇到一个未搜索的顶点时，先将与该顶点邻接的搜索完，然后进入下一轮搜索。与源点距离为k+1的顶点总是在所有与源点距离小于等于k的所有顶点都搜索完之后才被遍历。对于不区分边权值的图，BFS能计算出最短路径——DFS对此无能为力。

[Prim算法](http://en.wikipedia.org/wiki/Prim's_algorithm)是计算最小生成树（minimum spanning tree，MST）的经典算法，在计算最小生成树的过程中，Prim算法维护图的一个割（cut），割区分了树顶点（被选入MST的顶点）和非树顶点，树顶点集合构成了该图的部分MST（part MST）。每一次向树顶点集合加入新顶点时，都选取距离部分MST最近的非树顶点，显然，相同的操作重复V-1次后，所有顶点都已加入树顶点集，最小生成树计算完毕。

[Dijkstra算法](http://en.wikipedia.org/wiki/Dijkstra's_algorithm)可以解决非负权值的单源最短路径问题（shortest-paths problem），我们可以采用与Prim算法几乎一样的思想：计算最短路径的过程中维护一棵最短路径树（shortest-paths tree，SPT），包含了最短路径上的顶点。最开始这棵树中只有源点，作为树根。每次向SPT增加新顶点时，都选取非树顶点中与源点距离最近的顶点。相同的操作重复V-1次后，最短路径树计算完毕。与BFS类似，Dijkstra算法形成的搜索树偏胖~

 

仔细观察可以发现，这4种图搜索算法都具有相同的模式：首先它们都遍历了所有顶点，并且在遍历的过程中维护了一个顶点集，搜索一个顶点时把相邻的顶点按照某种规则加入到这个集合中，搜索下一个顶点时再按照某种规则从这个顶点集中取出顶点；取出顶点的规则是第二个共同点——它们都是贪心算法，在选择下一个顶点的时刻都依据某种优先级，选择优先级最大的顶点。

换言之，DFS、BFS、Prim算法和Dijkstra算法其实都是优先级优先搜索（priority-first search，PFS），它们依赖预定义的优先级执行搜索，而存放顶点的顶点集就是广义优先队列（priority queue，PQ）。显然，搜索算法的效率严重依赖于优先队列的操作效率。

既然这4种图搜索算法都有共同的思想，理所当然的，它们具有共同的代码接口（蓝色的函数调用包含与优先队列相关的操作）：

 ```java


void GraphPFS(Graph G, int src) {

  PQinit(G, src);

  while (!PQempty()) {

int v = PQdelmax(); // 取出优先级最大的顶点

Update(v);

Link t;

for (t = G->adj[v]; t != NULL; t = t->next) {

  if (ShouldInsert(t))

    PQinsert(t, v); // 顶点t->v加入优先队列

  else if (ShouldChange(t))

    PQchange(t, v); // 改变顶点t->v的优先级

}

  }

}

 ```

 

优先级搜索的算法接口基于图的邻接表（adjacency list）实现，其中顶点用int型数据表示，权值以double型表示，Graph与Link的声明如下，graph中的V和E分别表示图中顶点和边的数目：

 

typedef struct Node* Link;

struct Node { int v; double wt; Link next; };

 

typedef struct graph* Graph;

struct graph { int V, E; Link* adj; };

 

GraphPFS函数完整地包含了优先级搜索的模式，只需要按照算法的要求选择合适的数据结构作为PQ，就能利用GraphPFS完成DFS、BFS、Prim算法和Dijkstra算法。

 

Depth-first search

大动干戈地用优先级队列实现DFS，无异于大炮打蚊子。但这个例子非常经典，所以我忍不住开一炮~

DFS中的优先级是什么呢？DFS总是选择刚搜到的顶点进行下一轮搜索，显然，最近加入队列的顶点具有最高的优先级。不多想就能想到可以用栈（stack）作为DFS的PQ：栈总能访问到最近加入的元素。

 ```python
#define PQinit(G, src)  { int v; \

    for (v = 0; v < G->V; ++v) \

      ts[v] = -1; \

    cnt = 0; \

    StackInit(G->V); \

    StackPush(src); \

    ts[src] = cnt++; \

}

#define PQempty()   StackEmpty()

#define PQdelmax()  StackPop()

#define Update(v)   ts[v] = cnt++

#define ShouldInsert(t) ts[t->v] == -1

#define PQinsert(t, v)  StackPush(t->v)

#define ShouldChange(t) 0

#define PQchange(t, v)

 ```

 

为了记录顶点的时间戳（time-stamp），增加了一个辅助数组ts，ts[v] = -1表示顶点v没有被访问过。Update记录了将顶点从PQ中取出时要更新的变化，在DFS中只需要记录时间戳即可，但不是所有的更新都在此处执行，有些更新还需要父顶点信息，所以在PQinsert中执行，在下一个例子中我们马上会遇到这种情况。DFS不需要中途改变优先级，所以PQchange定义为空操作。

不过要注意的是，DFS的PFS实现与直接递归实现有一些区别，递归DFS会在发现一个新顶点的时候立即进一步搜索，PFS是在遍历完邻接顶点后才选择下一个顶点，所以ts数组记录的时间戳会不一样。比如以0为源点遍历下面这个图：

![图论中的优先级搜索——DFS,BFS,Prim,Dijkstra](http://s9.sinaimg.cn/middle/4dff8712gc0a9657b5858&690)

 

PFS的时间戳（time-stamp，ts）

| vertex | 0    | 1    | 2    | 3    | 4    | 5    |
| ------ | ---- | ---- | ---- | ---- | ---- | ---- |
| ts     | 0    | 4    | 5    | 3    | 1    | 2    |

直接递归的时间戳

| vertex | 0    | 1    | 2    | 3    | 4    | 5    |
| ------ | ---- | ---- | ---- | ---- | ---- | ---- |
| ts     | 0    | 1    | 2    | 3    | 4    | 5    |

虽然PFS与递归搜索的路径有所不同，但它仍然是正宗的深度优先搜寻，栈的结构确保了它总是优先沿着更深的路径搜索。

 

Breadth-first search

在BFS中，我们希望离源点近的顶点先被搜索，然后再搜索那些离源点更远的顶点。这要求先加入PQ的顶点要先被搜索，只要利用队列（queue）先进先出（first in, first out，FIFO）的性质实现PQ就能达到目的。

 ```python
#define PQinit(G, src)  { int v; \

    for (v = 0; v < G->V; ++v) \

      pl[v] = -1; \

    QueueInit(G->V); \

    QueuePut(src); \

    pl[src] = src; \

}

#define PQempty()  QueueEmpty()

#define PQdelmax()  QueueGet()

#define Update(v)

#define ShouldInsert(t) pl[t->v] == -1

#define PQinsert(t, v)  do { \

    QueuePut(t->v); pl[t->v] = v; \

} while(0)

#define ShouldChange(t) 0

#define PQchange(t, v)

 ```

 

BFS的结果以父链接表示的形式保存在pl（parent link）数组中，父链接的意思就是BFS树中顶点的父节点，如若从源点s出发到顶点w的最短路径上要经过边v-w，那么pl[w] = v。BFS树根的父链接等于自身。另外pl数组也表示一个顶点是否有被搜索到，辅助了顶点集的插入操作。由于父链接的信息在插入顶点时已更新，所以Update不需要做任何事。

![图论中的优先级搜索——DFS,BFS,Prim,Dijkstra](http://s13.sinaimg.cn/middle/4dff8712gc09bf62b9a6c&690)

[![图论中的优先级搜索——DFS,BFS,Prim,Dijkstra](http://s4.sinaimg.cn/middle/4dff8712gc09bf4d45833&690)](http://photo.blog.sina.com.cn/showpic.html#blogid=4dff8712010143kb&url=http://s4.sinaimg.cn/orignal/4dff8712gc09bf4d45833)

 

| vertex | 0    | 1    | 2    | 3    | 4    | 5    | 6    |
| ------ | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| pl     | 0    | 0    | 0    | 0    | 1    | 2    | 3    |

父链接表示最终结果

 

BFS还可以获取到许多信息，比如顶点到源点之间经过的边数，以及BFS的时间戳等等，只要向PQinsert和Update内加入相应的代码即可获取。

 

 

Prim's Algorithm

DFS和BFS几乎是最简单的图算法，现在来个稍微高级一点的——最小生成树（minimum spanning tree，MST）。Prim算法的思想在文章最开始的时候就说过了，现在从PFS的角度审视这个算法。

Prim算法计算MST的过程中维护了一个割（cut），区分了树（tree）顶点和非树（nontree）顶点，树顶点即是被选入MST的顶点。显然的，每一次从PQ中取出的顶点应该都是树顶点，关键在于：如何确定顶点的优先级（priority）？

由于Prim算法的框架是每次选择距离树最近的非树顶点，选择V-1次之后计算结束，因此顶点的优先级理所应当的由非树顶点与树顶点之间的距离决定，距树最近的非树顶点具有最大的优先级。

确定了优先级之后，接下来要做的就是挑选合适的数据结构充当PQ。事实上我们有许多选择：无序数组、有序数组、自组织链表，都可以。但注意到队列操作的效率直接决定了整个算法的效率，我们应当选择能够快速插入和搜索最大值的数据结构，显然堆（heap）可以胜任。注意各顶点的优先级不是一开始就明确的，而是可能在搜索过程中发生变化，所以堆还必须具有修改元素优先级的功能。普通的堆没有这样的功能，而索引堆（即以目标数组的索引作为元素的堆）可以轻松实现这个功能，所以我们选择索引堆作为PQ（P.S. 不熟悉索引堆的读者请google“索引堆”，点击第一个搜索结果 :-)）。

好了，PQ的数据结构也选好了，接下来考虑如何表示Prim算法的中间过程和结果。

首先，Prim算法计算的是MST，表示一棵树的方式在BFS中已经介绍过一种，既父链接表示。这种表示非常方便更新，所以在Prim算法中同样使用一个pl（parent link）数组存放最终形成的MST父链接结构。

其次，刚才提到索引堆的元素是目标数组的索引，目标数组涉及到顶点的优先级，因此目标数组的元素即是各个非树顶点到MST的距离。由于边权值是double型，所以用一个double的dist（distance）数组作为索引堆的目标数组。

最后，在计算MST的每一步中，都要知道当前与每个非树顶点最近的树顶点，才能从其中选出所有非树顶点中与MST最近的的一个。这些信息保存在fr（fringe）数组中。换言之，fr存放了阶段性的搜索结果。

以上这些信息的保存方式与PQ一样有许多选择，但最简单的实现是使用顶点索引数组。OK，现在可以上代码了。

 

\#define P              t->wt

\#define PQinit(G, src)  { int v; \

​    for (v = 0; v < G->V; ++v) { \

​      pl[v] = -1; fr[v] = -1; \

​    } \

​    PQInit(G->V); \

​    priority = dist; \

​    fr[src] = src; \

​    PQInsert(src); \

}

\#define PQempty()  PQEmpty()

\#define PQdelmax()  PQDelmin()

\#define Update(v)  pl[v] = fr[v]

\#define ShouldInsert(t) fr[t->v] == -1

\#define PQinsert(t, v)  do { \

​    dist[t->v] = P; PQInsert(t->v); fr[t->v] = v; \

} while(0)

\#define ShouldChange(t) (pl[t->v] == -1) && (P < dist[t->v])

\#define PQchange(t, v)  do { \

​    dist[t->v] = P; PQDec(t->v); fr[t->v] = v; \

} while(0)

 

第一行用P表示优先级，即树顶点到非树顶点直接的距离t->wt，可以简化代码。PQinit重置了pl和fr，并设置了PQ的目标数组（priority = dist）。随后初始化索引堆并将源点插入索引堆。由于PQDelmin选出的顶点必然属于MST，所以Update可以放心地设置它的父链接。

算法的重点在于PQinsert和PQchange。在搜索顶点的过程中，如果一个顶点w是通过v首次被搜索到（w = t->v，fr[t->v] == -1），显然目前没有其它路径比v-w更短了，随即可以设置dist[w]和fr[w]，并将w插入PQ。另一方面，如果通过v遇到了一个已知的非树顶点w，并且v-w的距离比已知距离更短，此时就该通过PQchange修改顶点w的优先级——变高了。

经过V-1次迭代，所有的顶点最终都会成为树顶点。

 

Dijkstra's algorithm

伟大的Dijkstra又登场了。

Dijkstra算法用于计算无负权值图的单源最短路径，与Prim算法一样，我们从PFS出发，一一解决Dijkstra算法中将要遇到的问题。

既然是求最短路径，优先级当然由顶点到源点的路径长度决定，路径长度越短，优先级越高。与Prim算法一样，PQ仍然可以用索引堆实现。

与Prim算法一样的还有最终结果的表示。Dijkstra算法计算最短路径，最终结果应该完整地包含这两个信息：一、最短路径怎么走；二、最短路径的长度。第一个信息，首先最短路径肯定不会包含环，所以可以用最短路径树（shortest-paths tree，SPT）的方式表示，表示树的简便方法我们已经很熟悉了，用一个父链接数组pl即可。第二个信息更不用想：一个dist数组，dist[v]即表示顶点v到源点s的距离。

算法的运行方式如同文章开头所写，维护一棵SPT，最开始只有源点。随后逐步向SPT中添加非树顶点，规则是每一次都选择距离源点最近的非树顶点。遍历顶点的同时，反复地执行一项被称为松弛（relaxation）的操作——对于顶点v，每当遇到一个比已知从源点到达v的最短路径还要短的路径，就更新关于v的路径信息（其实我觉得“松弛”这个术语很不恰当，一旦遇到更短的路径，那么这条路径应该更短更紧才对，怎么会是“松弛”呢？）。松弛是在遍历顶点的过程中进行的，从PQ中取出V-1个顶点、或所有可达顶点后，算法结束。

 

\#define P      dist[v] + t->wt

\#define PQinit(G, src)  { \

​    PQInit(G->V); \

​    priority = dist; \

​    int v; \

​    for (v = 0; v < G->V; ++v) { \

​      pl[v] = -1; dist[v] = MAX_WT; PQInsert(v); \

​    } \

​    dist[src] = 0.0; \

​    PQDec(src); \

}

\#define PQempty() PQEmpty()

\#define PQdelmax() PQDelmin()

\#define Update(v) if (wt[v] == MAX_WT) break;

\#define ShouldInsert(t)  0

\#define PQinsert(t, v)

\#define ShouldChange(t)  P < wt[t->v]

\#define PQchange(t, v)   do { \

​    wt[t->v] = P; PQDec(t->v); pl[t->v] = v; \

} while (0)

 

Dijkstra算法同样定义了一个P，即源点到目标顶点的距离，方便写代码。注意算法初始化PQ的时候就已经把所有顶点都插入索引堆（为什么？），因此PQinsert也不需要做任何事情，这也是Dijkstra算法与Prim算法的一个区别。刚才提到Dijkstra算法在计算完所有从源点可达的顶点后结束，此处不可达的顶点以距离MAX_WT表示，所以Update做的事情并不是更新什么信息，而是判断当前是否遇到了一个不可到达的顶点，如果是，那么算法也没有继续进行下去的必要了。初始情况下，顶点到自己的距离为0，其它所有顶点都不可达，随着松弛操作的进行，其它顶点的正确路径信息也会慢慢明晰，所有的松弛操作都在PQchange内完成。

 

Dijkstra算法与Prim算法是如此的相似，所以把它们对比一下是件有趣的事情。它们的相通之处贯穿全文：优先级优先搜索的本质。然而它们有个明显的区别，即刚才提到的：Prim算法在计算过程中多维护了一个fr数组，记录每一步当前与每个非树顶点最近的树顶点，每当遇到一个未曾搜索过的顶点就加入到fr。

之所以要这样做是因为Prim算法必须区分树顶点和非树顶点，Prim的优先级取决于非树顶点与树的距离，必须在计算过程中保存这些中间结果——每个非树顶点所关联的最近的树顶点。Dijkstra算法不需要，所以一开始就把所有顶点插入到PQ中，然后不断地松弛。其实Dijkstra算法也要做出某种区分，然而它只需要区分源点和非源点……

 

 

无论是“看起来不太像”的DFS和BFS，还是“看起来很像”的Prim算法和Dijkstra算法，都通过优先级优先搜索（PFS）这一算法思想联系到一起。现在再度总结一下：PFS是一种搜索图（Graph）的一般性算法，它先将图中的顶点（部分或全部地）加入到一个广义的优先队列中，然后根据特定的优先级，每次从队列里选取一条优先级最高的边，加入到特定的搜索树。搜索树就是PFS的最终结果（如最小生成树、最短路径树、DFS树……）。由此，DFS、BFS、Prim算法和Dijkstra算法都表征出相同的思想：

| 算法     | 优先级/P                 | 优先队列（PQ） | 结果     |
| -------- | ------------------------ | -------------- | -------- |
| DFS      | 逆先序                   | 栈             | DFS tree |
| BFS      | 先序                     | 队列           | SPT      |
| Prim     | 边权值/t->wt             | 堆             | MST      |
| Dijkstra | 路径长度/dist[v] + t->wt | 堆             | SPT      |

其实，你可以选择任何你想得到的优先级对图进行搜索，也许会得到意想不到的结果哦~