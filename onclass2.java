import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import java.util.function.Function;

static <T> ConsList<T> reverse(ConsList<T> list) {
    return FoldLeft(
            (T x, ConsList<T> acc) -> new Cons<>(x, acc), // 每次把元素 x 插在前面
            new Nil<>(),                                 // 初始累加器是空表
            list
    );
}

/** --------- 树的定义（多叉树；整数标签） --------- */
sealed interface Tree permits Leaf, Node {}
record Leaf(int value) implements Tree {}
record Node(int value, ConsList<Tree> children) implements Tree {}

/** --------- 1) 树的高度 ---------
 * 叶子高度 = 1
 * 内部节点高度 = 1 + max(各子树高度)；若无子树则也为 1
 */
static int height(Tree t) {
        return switch (t) {
            case Leaf l -> 1;
            case Node n -> {
                // 对子树做 height 映射 -> 取最大值（空子树时初值为 0）
                ConsList<Integer> hs = Map(x -> height(x), n.children());
                int mx = FoldLeft((Integer h, Integer acc) -> Math.max(acc, h), 0, hs);
                yield 1 + mx;
            }
        };
}

/** --------- 2) 统计叶子数量 ---------
 * 叶子 → 1；内部节点 → 各子树叶子数之和
 */
static int countLeaves(Tree t) {
    return switch (t) {
        case Leaf l -> 1;
        case Node n -> FoldLeft((Tree child, Integer acc) -> acc + countLeaves(child),
                                0,
                                n.children());
    };
}

/** --------- 3) 先序遍历（根 → 子树） ---------
 * 返回所有节点的值（根值在前，再依次拼接每棵子树的先序）
 */
static ConsList<Integer> preorder(Tree t) {
    return switch (t) {
        case Leaf l -> MakeList(l.value());
        case Node n -> {
            // 先把每棵子树转成其先序列表：ConsList<ConsList<Integer>>
            ConsList<ConsList<Integer>> lists = Map(x -> preorder(x), n.children());
            // 把它们“展平”为一个列表，然后在最前面加上根值
            yield new Cons<>(n.value(), flatten(lists));
        }
    };
}

/** flatten：把 ConsList<ConsList<T>> 展平成 ConsList<T>（纯函数式 Append + 递归/Fold） */
static <T> ConsList<T> flatten(ConsList<ConsList<T>> xss) {
    return FoldLeft(
        (ConsList<T> xs, ConsList<T> acc) -> Append(acc, xs), // 维持原相对顺序
        new Nil<>(),
        xss
    );
}

/** --------- 4) 是否包含某个值（DFS） --------- */
static boolean contains(Tree t, int target) {
    return switch (t) {
        case Leaf l -> l.value() == target;
        case Node n -> {
            if (n.value() == target) yield true;
            // 只要任一子树包含即可：左折叠布尔“或”
            boolean found = FoldLeft(
                (Tree child, Boolean acc) -> acc || contains(child, target),
                false,
                n.children()
            );
            yield found;
        }
    };
}

/** --------- 5) 所有“叶子结点”的和 --------- */
static int sumLeaves(Tree t) {
    return switch (t) {
        case Leaf l -> l.value();
        case Node n -> FoldLeft((Tree child, Integer acc) -> acc + sumLeaves(child),
                                0,
                                n.children());
    };
}

/** --------- 6) 映射一棵树（对每个节点值应用 f） --------- */
static Tree mapTree(Function<Integer,Integer> f, Tree t) {
    return switch (t) {
        case Leaf l -> new Leaf(f.apply(l.value()));
        case Node n -> new Node(
            f.apply(n.value()),
            Map(child -> mapTree(f, child), n.children())
        );
    };
}

/* ------------------ 小示例（可选） ------------------
Tree t =
    new Node(1, MakeList(
        new Leaf(5),
        new Node(2, MakeList(
            new Leaf(7),
            new Leaf(8)
        )),
        new Leaf(3)
    ));

height(t);            // 3
countLeaves(t);       // 4
preorder(t);          // [1,5,2,7,8,3]
contains(t, 7);       // true
sumLeaves(t);         // 5 + 7 + 8 + 3 = 23
mapTree(x -> x*10, t);// 所有节点值 * 10
----------------------------------------------------- */
// void main(){
//     Tree t =
//     new Node(1, MakeList(
//         new Leaf(5),
//         new Node(2, MakeList(
//             new Leaf(7),
//             new Leaf(8)
//         )),
//         new Leaf(3)
//     ));

//     println(height(t));            // 3
//     println(countLeaves(t));       // 4
//     println(preorder(t));          // [1,5,2,7,8,3]
//     println(contains(t, 7));       // true
//     println(sumLeaves(t));         // 5 + 7 + 8 + 3 = 23
//     println(mapTree(x -> x*10, t));// 所有节点值 * 10
// }

