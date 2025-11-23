import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;
/**
 * 题目 P1-1：计算字符串链表的总长度（递归）
 * * 题目描述：
 * 编写一个函数 totalLength1，用于计算 ConsList<String> 中所有字符串的总长度（即所有字符串长度之和）。
 * 该函数接收一个类型为 ConsList<String> 的字符串链表作为输入，
 * 返回一个整数 (int)，表示所有字符串的长度总和。
 * * 规则：
 * - 必须使用递归和模式匹配（switch 表达式）实现。
 * - 使用 Length(String) 辅助函数获取单个字符串的长度。
 * * 测试用例：
 * - 输入：["Hello", " ", "World"] → 输出：11 (5 + 1 + 5)
 * - 输入：["one", "two"] → 输出：6
 * - 输入：[] → 输出：0
 */
int totalLength1(ConsList<String> strings){
    return switch(strings){
        case Nil<String>() -> 0;
        case Cons<String>(var first, var rest) -> Length(first)+totalLength1(rest);
    };
}

/**
 * 题目 P1-2：计算字符串链表的总长度（FoldLeft）
 * * 题目描述：
 * 编写一个函数 totalLength2，用于计算 ConsList<String> 中所有字符串的总长度（即所有字符串长度之和）。
 * 该函数接收一个类型为 ConsList<String> 的字符串链表作为输入，
 * 返回一个整数 (int)，表示所有字符串的长度总和。
 * * 规则：
 * - 必须使用 FoldLeft 高阶函数实现。
 * - 使用 Length(String) 辅助函数获取单个字符串的长度。
 * * 测试用例：
 * - 输入：["Hello", " ", "World"] → 输出：11 (5 + 1 + 5)
 * - 输入：["one", "two"] → 输出：6
 * - 输入：[] → 输出：0
 */
int totalLength2(ConsList<String> strings){
    return FoldLeft((String str, Integer acc)->acc+Length(str), 0, strings);
}

/**
 * 题目 P2：计算整数链表的平均值
 * * 题目描述：
 * 编写一个函数 average，用于计算 ConsList<Integer> 中所有整数的平均值。
 * 该函数接收一个类型为 ConsList<Integer> 的整数链表作为输入，
 * 返回一个双精度浮点数 (double)，表示所有元素的平均值。
 * * 规则：
 * - 必须使用 FoldLeft 高阶函数计算总和。
 * - 必须使用 Length(ConsList) 辅助函数获取链表长度。
 * - 假设输入的链表是非空 (non-empty) 的。
 * * 测试用例：
 * - 输入：[1, 2, 3] → 输出：2.0
 * - 输入：[10, 20, 30, 40] → 输出：25.0
 * - 输入：[5] → 输出：5.0
 */
double average(ConsList<Integer> numbers){
    int n = Length(numbers);
    //题目说假设input list都是non-empty，所以其实没必要有这个
    if(Equals(n,0)){
        return DefaultCaseError("It is an empty list");
    }
    double sum = FoldLeft((Integer num, Double acc)->acc+num,0.0,numbers);
    return sum/n;
}

/**
 * 题目 P3-1：计算笛卡尔积（FoldLeft 实现）
 * * 题目描述：
 * 编写一个函数 allPair，用于计算两个整数链表 l1 和 l2 的笛卡尔积（Cartesian Product）。
 * 该函数接收两个 ConsList<Integer> 链表作为输入，
 * 返回一个 ConsList<Pair<Integer, Integer>>，其中包含 l1 的每个元素与 l2 的每个元素配对形成的所有组合。
 * * 规则：
 * - 首先对 l1 和 l2 进行排序（Sort）。
 * - 必须使用 FoldLeft 高阶函数，并在累加器中利用 Map 和 Append 函数实现配对。
 * * 测试用例：
 * - 输入：l1=[2, 1], l2=[3, 4] → (排序后) l1=[1, 2], l2=[3, 4] → 输出：[(1, 3), (1, 4), (2, 3), (2, 4)]
 * - 输入：l1=[1], l2=[10, 20] → 输出：[(1, 10), (1, 20)]
 */
ConsList<Pair<Integer,Integer>> allPair(ConsList<Integer> l1, ConsList<Integer> l2){
    ConsList<Integer> sortedl1 = Sort(l1);
    ConsList<Integer> sortedl2 = Sort(l2);

    // 对每个 a，把 (a,b)（b ∈ B）映射成一段 Pair 列表，再 Append 到总结果
    return FoldLeft(
        (Integer x, ConsList<Pair<Integer,Integer>> acc)
        ->Append(acc,Map((Integer y)->new Pair<>(x,y),sortedl2))
        ,new Nil<>()
        ,sortedl1);
}

/**
 * 题目 P3-2：计算笛卡尔积（递归主函数）
 * * 题目描述：
 * 编写一个函数 allPair2，用于计算两个整数链表 l1 和 l2 的笛卡尔积。
 * 该函数接收两个 ConsList<Integer> 链表作为输入，
 * 返回一个 ConsList<Pair<Integer, Integer>>，包含 l1 的每个元素与 l2 的每个元素配对形成的所有组合。
 * * 规则：
 * - 首先对 l1 和 l2 进行排序（Sort）。
 * - 必须调用递归辅助函数 allPairRecursive 实现主要逻辑。
 * * 测试用例：
 * - 输入：l1=[2, 1], l2=[3, 4] → (排序后) l1=[1, 2], l2=[3, 4] → 输出：[(1, 3), (1, 4), (2, 3), (2, 4)]
 */
ConsList<Pair<Integer,Integer>> allPair2(ConsList<Integer> l1, ConsList<Integer> l2){
    ConsList<Integer> sortedl1 = Sort(l1);
    ConsList<Integer> sortedl2 = Sort(l2);
    return allPairRecursive(sortedl1,sortedl2);
}

/**
 * 题目 P3-2：递归辅助函数：计算笛卡尔积
 * * 题目描述：
 * 编写一个递归辅助函数 allPairRecursive，计算两个已排序链表 a 和 b 的笛卡尔积。
 * 该函数接收两个已排序的 ConsList<Integer> 链表作为输入（a 和 b），
 * 返回一个 ConsList<Pair<Integer, Integer>>，包含所有可能的配对。
 * * 规则：
 * - 对第一个链表 a 使用递归，对 a 中的每个元素 x，调用 pairWithList(x, b) 生成所有配对。
 * - 使用 Append 将结果列表连接起来。
 * - 结果链表内的配对（Pair）是按从小到大排序的。
 * * 测试用例：
 * - 输入：a=[1, 2], b=[3, 4] → 输出：[(1, 3), (1, 4), (2, 3), (2, 4)]
 */
ConsList<Pair<Integer, Integer>> allPairRecursive(ConsList<Integer> a, ConsList<Integer> b){
    return switch(a){
        case Nil<Integer> nil -> new Nil<>();
        case Cons<Integer> cons -> {
            ConsList<Pair<Integer,Integer>> pairsForFirst = pairWithList(First(cons), b);
            yield Append(pairsForFirst, allPairRecursive(Rest(cons), b));
        }
    };
}

/**
 * 题目 P3-2：辅助函数：将单个元素与链表配对
 * * 题目描述：
 * 编写一个辅助函数 pairWithList，将一个元素与目标链表中的所有元素配对。
 * 该函数接收一个整数 firstelement 和一个 ConsList<Integer> b 作为输入，
 * 返回一个新的 ConsList<Pair<Integer, Integer>>，其中包含 (firstelement, y) 形式的所有配对，y 是 b 中的元素。
 * * 规则：
 * - 必须使用递归和模式匹配（switch 表达式）实现。
 * * 测试用例：
 * - 输入：firstelement=1, b=[3, 4] → 输出：[(1, 3), (1, 4)]
 */
ConsList<Pair<Integer, Integer>> pairWithList(Integer firstelement, ConsList<Integer> b){
    return switch(b){
        case Nil<Integer> nil -> new Nil<>();
        case Cons<Integer> cons ->{
            Pair<Integer,Integer> newPair = new Pair<>(firstelement, First(b));
            yield new Cons<>(newPair, pairWithList(firstelement,Rest(b)));
        }
    };
}

/**
 * 【结构定义：二叉树 (BinTree)】
 * -----------------------------------------------------------
 * BinTree<T> 是一个密封接口 (sealed interface)，定义了二叉树这种代数数据类型 (ADT) 的结构。
 * 这种结构强制二叉树只能是以下两种形式之一：
 * * 1. Leaf<T> (叶子节点)：
 * - 结构：record Leaf<T>(T value)
 * - 作用：表示树的末端，包含一个实际的值 (value)。
 * * 2. Node<T> (内部节点)：
 * - 结构：record Node<T>(BinTree<T> left, BinTree<T> right)
 * - 作用：表示树的内部连接点，包含左子树 (left) 和右子树 (right)，用于递归构建树形结构。
 * -----------------------------------------------------------
 */
sealed interface BinTree<T> permits Leaf,Node {}
record Leaf<T>(T value) implements BinTree<T>{}
record Node<T>(BinTree<T> left, BinTree<T> right) implements BinTree<T>{}

/**
 * 题目 P4-1：二叉树的折叠（BinTreeFold）
 * * 题目描述：
 * 编写一个高阶函数 binTreeFold，用于对二叉树 BinTree<T> 进行**折叠（Fold）或归约（Reduce）**操作。
 * 该函数接收一个聚合函数 agg（类型为 BiFunction<T, T, T>）和一个 BinTree<T> 作为输入。
 * 它从树的叶子节点开始，向上应用聚合函数 agg，最终将整个树归约为一个 T 类型的值。
 * * 规则：
 * - **叶子节点 (Leaf<T>)**：直接返回其包含的值。
 * - **内部节点 (Node<T>)**：对左右子树递归调用 binTreeFold，然后将两个结果应用聚合函数 agg 进行合并。
 * * 测试用例：
 * - 聚合函数 agg：(x, y) -> x + y （求和）
 * - 输入：二叉树结构 
 * 
 * （例如：Node(Leaf(1), Node(Leaf(2), Leaf(3)))) → 输出：1 + (2 + 3) = 6
 */
static <T> T binTreeFold(BiFunction<T,T,T> agg, BinTree<T> tree){
    return switch(tree){
        case Leaf<T>(var value) -> value;
        case Node<T>(var left, var right) -> agg.apply(binTreeFold(agg,left), binTreeFold(agg,right));
    };
}
static <T> T BinTreeFold(BiFunction<T,T,T> agg, BinTree<T> tree) {
    return switch (tree) {
        case Leaf<T> l   -> l.value();
        case Branch<T> b -> agg.apply(BinTreeFold(agg, b.left()), BinTreeFold(agg, b.right()));
    };
}
/**
 * 题目 P4-2：二叉树的映射（BinTreeMap）
 * * 题目描述：
 * 编写一个高阶函数 BinTreeMap，实现二叉树的映射（Map）操作。
 * 该函数接收一个映射函数 mapper（类型为 Function<S, T>）和一个 BinTree<S> 作为输入。
 * 它遍历树的每个叶子节点，将叶子节点中包含的值 S 转换为新的值 T，并返回一个结构不变的新 BinTree<T>。
 * * 规则：
 * - **叶子节点 (Leaf<S>)**：将叶子节点中的值应用 mapper 函数，并创建新的 Leaf<T>。
 * - **内部节点 (Branch<S>)**：对左右子树递归调用 BinTreeMap，并创建新的 Branch<T>，保持树的结构。
 * * 函数签名：
 * static <S,T> BinTree<T> BinTreeMap(Function<S,T> mapper, BinTree<S> tree)
 * * 测试用例：
 * - 映射函数 mapper：(String s) -> Length(s)
 * - 输入：树叶是 "abc", "x", "34", "" 组成的 BinTree<String>
 * - 输出：返回同结构的新 BinTree<Integer>，其叶子值分别为 3, 1, 2, 0。
 */
static <S,T> BinTree<T> BinTreeMap(Function<S,T> mapper, BinTree<S> tree) {
    return switch (tree) {
        case Leaf<S> l   -> new Leaf<>(mapper.apply(l.value()));
        case Branch<S> b -> new Branch<>(BinTreeMap(mapper, b.left()),
                                         BinTreeMap(mapper, b.right()));
    };
}
void main(){
    // P1-1 
    println(totalLength1(MakeList("hi","ab","")));
    println(totalLength1(MakeList()));
    
    // P1-2
    println(totalLength2(MakeList("hi","ab","")));

    // P2
    println(average(MakeList(3,4,5)));

    // P3-1
    println(allPair(MakeList(11,5,7),MakeList(2,8)));

    // P3-2
    println(allPair2(MakeList(11,5,7),MakeList(2,8)));

    // P4-1
    BinTree<Integer> t1 =
        new Node<>(
            new Node<>(new Leaf<>(1), new Leaf<>(2)),
            new Node<>(new Leaf<>(3), new Leaf<>(4))
        );
    int folded = binTreeFold((a,b) -> a - b, t1);
    // 0  (= (1-2) - (3-4))
    println(folded);

    // P4-2
    BinTree<String> t2 =
        new Branch<>(
            new Branch<>(new Leaf<>("abc"), new Leaf<>("x")),
            new Branch<>(new Leaf<>("34"),  new Leaf<>(""))
        );
    BinTree<Integer> t2Mapped = BinTreeMap((String s) -> Length(s), t2);
    // 结构不变，叶子变为 3,1,2,0
    println(t2Mapped);           
}