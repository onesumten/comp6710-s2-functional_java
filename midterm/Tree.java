import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;

sealed interface BinaryTree permits Leaf, Node{}
record Leaf() implements BinaryTree{}
record Node(BinaryTree left, BinaryTree right) implements BinaryTree{}

sealed interface Tree permits Leaf, Node{}
record Leaf(int value) implements Tree{}
record Node(int value, ConsList<Tree> children) implements Tree{}

sealed interface MixedTree permits BiNode, Node{}
record BiNode(MixedTree left, MixedTree right){}
record Node(ConsList<MixedTree> children){}
/**
 * 题目：树的高度（二叉树版本）
 * * 题目描述：
 * 编写一个函数 Height，用于计算一个 BinaryTree 的高度。
 * 该函数接收一个 BinaryTree 作为输入，
 * 返回一个整数 (int)，表示树的最大深度。
 * * 规则：
 * - 叶子节点 (Leaf) 的高度为 1。
 * - 内部节点 (Node) 的高度为 1 加上左右子树高度的最大值。
 * - 必须使用模式匹配（switch 表达式）和递归实现。
 * * 测试用例：
 * - 输入：只有 Leaf() → 输出：1
 * - 输入：Node(Node(Leaf(), Leaf()), Leaf()) → 输出：3
 */
int Height(BinaryTree tree){
    return switch(tree){
        case leaf() -> 1;
        case Node(var left, var right) -> 1 + Max(Height(left), Height(right));
    };
}

/**
 * 题目：树的高度（多叉树版本）
 * * 题目描述：
 * 编写一个函数 Height1，用于计算一个多叉树 Tree 的高度。
 * 该函数接收一个 Tree 作为输入，
 * 返回一个整数 (int)，表示树的最大深度。
 * * 规则：
 * - 叶子节点 (Leaf) 的高度为 1。
 * - 内部节点 (Node) 的高度为 1 加上其所有子节点高度中的最大值。
 * - 必须使用 Map 函数计算所有子节点的高度，并使用 FoldLeft 函数找到最大值。
 * * 测试用例：
 * - 输入：只有 Leaf(1) → 输出：1
 * - 输入：Node(0, [Node(1, [Leaf(2)]), Leaf(3)]) → 输出：3
 */
int Height1(Tree tree){
    return switch(tree){
        case Leaf l -> 1;
        case Node n -> {
            //将所有子节点的高度集合起来形成一个链表
            ConsList<Tree> sumhe = Map(x -> Height1(x), n.children);
            //从子节点高度中找出最大的高度
            int mx = FoldLeft((Integer h, Integer acc) -> Max(acc,h),0,sumhe);
            yield 1+mx;
        }
    };
}

/**
 * 题目：统计叶子节点的数量
 * * 题目描述：
 * 编写一个函数 countLeaves，用于统计一个多叉树 Tree 中叶子节点的总数量。
 * 该函数接收一个 Tree 作为输入，
 * 返回一个整数 (int)，表示树中 Leaf 节点的数量。
 * * 规则：
 * - 叶子节点 (Leaf) 贡献 1 个计数。
 * - 内部节点 (Node) 的计数是其所有子节点计数的总和。
 * - 必须使用 FoldLeft 函数对所有子节点的结果进行累加。
 * * 测试用例：
 * - 输入：只有 Leaf(1) → 输出：1
 * - 输入：Node(0, [Node(1, [Leaf(2)]), Leaf(3)]) → 输出：2
 */
int countLeaves(Tree tree){
    return switch(tree){
        case Leaf l -> 1;
        case Node n -> FoldLeft((Tree child, Integer acc) -> acc+countLeaves(child),0,n.children);
    };
}

/**
 * 题目：先序遍历输出所有值,返回一棵树中所有节点的值（前序遍历：先根，再子树）。
 * * 题目描述：
 * 编写一个函数 preorder，用于对多叉树 Tree 进行先序遍历（根、子树 1, 子树 2, ...）。
 * 该函数接收一个 Tree 作为输入，
 * 返回一个 ConsList<Integer>，按遍历顺序包含树中所有节点的值。
 * * 规则：
 * - 叶子节点 (Leaf) 返回其值组成的单元素列表。
 * - 内部节点 (Node) 的结果是：**自身的值** + **所有子树先序遍历的结果（展平后）**。
 * - 必须使用 Map 函数处理子节点，并使用 flatten 辅助函数连接结果。
 * * 测试用例：
 * - 输入：Node(1, [Leaf(2), Node(3, [Leaf(4)])]) → 输出：[1, 2, 3, 4]
 */
ConsList<Integr> preorder(Tree t){
    return switch(t){
        case Leaf l -> MakeList(l.value);
        case Node n -> {
            ConsList<ConsList<Integer>> lists = Map(x->preorder(x), n.children);
            return new Cons<>(n.value, flatten(lists));
        }
    };
}

/**
 * 题目：辅助函数：展平链表（Flatten）
 * * 题目描述：
 * 编写一个函数 flatten，用于将一个包含链表的链表（嵌套链表 ConsList<ConsList<T>>）展平为一个单一的链表 ConsList<T>。
 * 该函数接收一个嵌套链表作为输入，
 * 返回一个展平后的链表，其中包含所有内部链表的元素，按原顺序排列。
 * * 规则：
 * - 必须使用 FoldLeft 高阶函数和 Append 辅助函数实现。
 * * 测试用例：
 * - 输入：[[1, 2], [3], [4, 5]] → 输出：[1, 2, 3, 4, 5]
 */
ConsList<T> flatten(ConsList<ConsList<T>> xss){
    return FoldLeft((ConsList<T> xs, ConsList<T> acc) -> Append(acc,xs), new Nil<>(), xss);
}

/**
 * 题目：在树中查找一个值
 * * 题目描述：
 * 编写一个函数 contains，用于在一个多叉树 Tree 中查找是否存在某个目标值 (target)。
 * 该函数接收一个 Tree 和一个整数 target 作为输入，
 * 返回一个布尔值 (boolean)，如果树中任一节点的值与 target 相等，则返回 true。
 * * 规则：
 * - 叶子节点 (Leaf) 检查自身值是否等于 target。
 * - 内部节点 (Node) 检查自身值是否等于 target，或者递归检查任一子树是否包含 target。
 * - 必须使用 FoldLeft 函数实现对子节点（child）的逻辑“或” (acc || contains(child,target)) 聚合操作。
 * * 测试用例：
 * - 输入：Tree=Node(1, [Leaf(2), Leaf(3)]), target=3 → 输出：true
 * - 输入：Tree=Node(1, [Leaf(2), Leaf(3)]), target=4 → 输出：false
 */
boolean contains(Tree t, int target){
    return switch(t){
        case Leaf l -> Equals(l.value, target);
        case Node n -> Equals(n.value, target)||FoldLeft((Tree child, Boolean acc)-> acc|| contains(child,target),false,n.children);
    };
}

BinaryTree E = new leaf();
BinaryTree D = new leaf();
BinaryTree B = new node(D,E);
BinaryTree C = new leaf();
BinaryTree A = new node(B,C);

void main(){
    println(Height(A));
}