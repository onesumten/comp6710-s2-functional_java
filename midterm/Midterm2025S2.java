/**
 * 题目1：查找最短字符串
 * 
 * 题目描述：
 * 编写一个函数 findShortest，接收一个字符串列表 ConsList<String>，返回列表中长度最短的字符串。
 * 
 * 函数签名：
 * String findShortest(ConsList<String> lst)
 * 
 * 测试用例：
 * - 输入：["abc", "erft", "we"] → 输出："we"
 * - 输入：[""] → 输出：""
 */

/**
 * 题目2：整数到布尔值的Maybe转换
 * 
 * 题目描述：
 * 编写一个函数 returnList，接收一个整数列表 ConsList<Integer>，将列表中的每个整数按以下规则转换为 Maybe<Boolean> 类型：
 * - 1 转换为 Something(true)
 * - 0 转换为 Something(false)
 * - 其他数字转换为 Nothing()
 * 
 * 函数签名：
 * ConsList<Maybe<Boolean>> returnList(ConsList<Integer> lst)
 * Maybe<Boolean> helper(int a)
 * 
 * 测试用例：
 * - 输入：[1, 2, 3, 0, -1, 2, 3]
 * - 输出：[Something(true), Nothing(), Nothing(), Something(false), Nothing(), Nothing(), Nothing()]
 */

/**
 * 题目3：二叉树最深路径查找
 * 
 * 题目描述：
 * 给定一个二叉树，编写函数 findDeepestPath 找到从根节点到最深叶子节点的路径。
 * 如果有多条相同深度的路径，任意返回一条即可。
 * 
 * 函数签名：
 * ConsList<String> findDeepestPath(Binarytree<String> tree)
 * 
 * 测试用例：
 * 1. 单个叶子节点 A → 输出：["A"]
 * 2. 简单树结构：
 *        B
 *       / \
 *      A   C
 *    输出：["B", "A"] or ["B" , "C"]
 * 
 * 3. 不平衡树：
 *        A
 *       / \
 *      B   C
 *         / \
 *        D   E
 *    输出：["A", "C", "D"] or ["A", "C", "E"]
 */

/**
 * 题目4：基于条件的二叉树左右子树交换
 * 
 * 题目描述：
 * 编写一个函数 swapOnCondition，接收一个泛型二叉树和一个判断函数（Function<T, Boolean>）。
 * 遍历二叉树，对于每个内部节点（Node），如果该节点的值满足判断函数返回 true，
 * 则交换该节点的左右子节点的根值，但保持它们的子树结构不变。
 * 
 * 函数签名：
 * <T> BinaryTree<T> swapOnCondition(BinaryTree<T> tree, Function<T, Boolean> predicate)
 * 
 * 测试用例：
 * 1. 对于字符串树，交换值为 "A" 的节点的左右子树：
 *    原树：     A          交换后：    A
 *             / \                   / \
 *            B   C                 C   B
 *    
 * 2. 对于整数树，交换偶数值节点的左右子节点的根值：
 *    原树：     2          交换后：    2
 *             / \                   / \
 *            1   4                 4   1
 *               / \                   / \
 *              3   5                 5   3
 *    (节点2和节点4都是偶数，所以都会交换它们的子节点值)
 * 
 * 3. 多层交换示例：
 *    如果多个节点都满足条件，则每个满足条件的节点都会交换其左右子节点的根值
 */