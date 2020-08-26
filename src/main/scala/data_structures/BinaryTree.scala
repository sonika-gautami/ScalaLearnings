package data_structures

object BinaryTree extends App {
  /*
  > To store information that naturally forms a hierarchy.
  > Trees (with some ordering e.g., BST) provide moderate access/search (quicker than Linked List and slower than arrays).
  > Trees provide moderate insertion/deletion (quicker than Arrays and slower than Unordered Linked Lists).
  > Like Linked Lists and unlike Arrays, Trees don’t have an upper limit on number of nodes as nodes are linked using pointers.

  Ref:
  https://www.geeksforgeeks.org/binary-tree-set-1-introduction/

  2^(#LEVEL -1)
  2^(#HEIGHT) - 1

  log2(#Leaves) + 1
  log2(#Nodes + 1) - 1

  L = T + 1
  Where L = Number of leaf nodes
        T = Number of internal nodes with two children
     */

  val sampleT = Fork(Fork(Leaf('4'), Leaf('5'), '2'), Leaf('3'), '1')

  println(preOrder(sampleT))
  println(postOrder(sampleT))
  println(inOrder(sampleT))

  println(inOrder(mirror(sampleT)))
  println(allPath(sampleT))
  println(bfs(sampleT))

  assert(preOrder(sampleT) == "12453")
  assert(postOrder(sampleT) == "45231")
  assert(inOrder(sampleT) == "42513")
  assert(inOrder(mirror(sampleT)) == "31524")
  assert(allPath(sampleT) == List("124", "125", "13"))
  assert(bfs(sampleT) == "12345")

  trait BiTree

  case class Leaf(c: Char) extends BiTree

  case class Fork(l: BiTree, r: BiTree, c: Char) extends BiTree


  /*
  bfs: breadth first search (level traversal)
  dfs: depth first search (pre/post/in Order)
  */

  def bfs(t: BiTree): String = {
    def loop(acc: StringBuilder, list: List[BiTree]): String = {

      val next = new scala.collection.mutable.ListBuffer[BiTree]()
      list.foreach {
        case Leaf(c) => acc append c
        case Fork(l, r, c) =>
          acc append c;
          next ++= List(l, r)
      }

      if(list.isEmpty) acc.toString else loop(acc, next.toList)
    }

    loop(new StringBuilder(""), List(t))
  }

  //Root, Left, Right
  def preOrder(t: BiTree): String = t match {
    case Leaf(c) => c.toString
    case Fork(l, r, c) => c + preOrder(l) + preOrder(r)
  }

  //Left, Right, Root
  def postOrder(t: BiTree): String = t match {
    case Leaf(c) => c.toString
    case Fork(l, r, c) => postOrder(l) + postOrder(r) + c
  }

  //Left, Root, Right
  def inOrder(t: BiTree): String = t match {
    case Leaf(c) => c.toString
    case Fork(l, r, c) => inOrder(l) + c + inOrder(r)
  }

  def mirror(t: BiTree): BiTree = t match {
    case l: Leaf => l
    case f: Fork => f.copy(l = mirror(f.r), r = mirror(f.l))
  }

  def allPath(t: BiTree): List[String] = {

    def loop(nodes: String,
             tree: BiTree): List[String] = tree match {

      case Leaf(c) => List(nodes :+ c)
      case Fork(l, r, c) => loop(nodes :+ c, l) ::: loop(nodes :+ c, r)
    }

    loop("", t)
  }
}
