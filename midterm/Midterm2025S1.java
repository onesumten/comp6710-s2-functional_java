/**
 * 这是第一题
 * please check ws3a exercise "ConcatenateStrings"
 * 可以去查看一下 ws3a的练习题 “ConcatenateStrings”
 * totalLength() Give you a ConsList<String> strings
 * you need return sum of all strings
 */

/**
 * 这是第二题
 * Average() Give you a ConsList<Integer> numbers.
 * you need return average of all numbers (return floattype)
 */

/**
 * 这是第三题
 * AllPair() Give two ConsList<lnteger>, 
 * return all Pairof these two lists 
 * (follow lexicographical order,means need to sort)
 * Example：
 *  -Given [11,5,7] ,[2,8]
 *      -Return [(5,2),(5,8),(7,2),(7,8),(11,2),(11,8)]
 */


/**
 * 这是符合题目要求的数据类型定义
 * 直接复制即可
 */
sealed interface BinTree<T> permits Leaf, Node{}

record Leaf<T>(T value) implements BinTree<T>{} // Leaf也有value！

record Node<T>(BinTree<T> left, T value, BinTree<T> right) implements BinTree<T> {}
/**
 * 这是 P4-1
 * Implement:
 *   BinTreeFold(BiFunction<T,T,T> agg, BinTree<T> tree)
 *
 * Example:
 *   agg = (a, b) -> a - b
 *
 *   Given tree:
 *
 *            n/a
 *           /   \
 *        n/a     n/a
 *       /  \    /   \
 *      1    2  3     4
 *
 *   Evaluation:
 *      ((1 - 2) - (3 - 4)) = 0
 *
 *   Expected return: 0
 */

/**
 * 这是 P4-2
 * Implement:
 *   BinTreeMap(Function<T,T> mapper, BinTree<T> tree)
 *
 * Example:
 *   mapper = a -> Length(a)
 *
 *   Given tree:
 *
 *            n/a
 *           /   \
 *        n/a     n/a
 *       /  \    /   \
 *   "abc"  "x" "34"  ""
 *
 *   After mapping (string length):
 *
 *            n/a
 *           /   \
 *        n/a     n/a
 *       /  \    /   \
 *      3    1   2    0
 *
 *   Expected return: new mapped tree
 */