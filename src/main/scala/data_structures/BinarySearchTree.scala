package data_structures

object BinarySearchTree extends App {

/*
A Binary Search Tree is a type of binary tree data structure
  in which the nodes are arranged in order,
  hence also called as “ordered binary tree”.

It’s a node-based data structure which provides
  an efficient and fast way of sorting, retrieving, searching data.

(Left < Parent),  (Right > Parent); There should be no duplicate keys.


*/

  val sampleT =
    Fork(
      Fork(
        Leaf(4),
        Fork(Leaf(10), Leaf(14), 12),
        8),
      Leaf(22),
      20)

  println(lowestCommonAncestor(sampleT, 10, 14))
  println(lowestCommonAncestor(sampleT, 14, 8))
  println(lowestCommonAncestor(sampleT, 10, 22))


  assert(lowestCommonAncestor(sampleT, 10, 14) == 12)
  assert(lowestCommonAncestor(sampleT, 14, 8) == 8)
  assert(lowestCommonAncestor(sampleT, 10, 22) == 20)



  abstract class BiTree(val v: Int)

  case class Leaf(override val v: Int) extends BiTree(v)

  case class Fork(l: BiTree, r: BiTree, override val v: Int) extends BiTree(v)


  def lowestCommonAncestor(tree: BiTree, v1: Int, v2: Int): Int = {
    tree match {
      case Leaf(c) => c
      case Fork(l, r, c) =>
        if (v1 < c && v2 < c) lowestCommonAncestor(l, v1, v2)
        else if (v1 > c && v2 > c) lowestCommonAncestor(r, v1, v2)
        else c
    }
  }
}
