import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import java.util.function.Function;

// P1 totalLength()
// 给你一个 ConsList<String> strings，返回所有字符串长度之和。
static int totalLength(ConsList<String> strings) {
    // 左折叠：acc 初始 0，每个 s 累加 Length(s)
    return FoldLeft((String s, Integer acc) -> acc + Length(s), 0, strings);
}

// P2 average()
// 给你一个 ConsList<Integer> numbers，返回所有数字的平均值（浮点型）。
static double average(ConsList<Integer> numbers) {
    int n = Length(numbers);
    if (n == 0) throw new IllegalArgumentException("average of empty list");
    int sum = FoldLeft((Integer x, Integer acc) -> acc + x, 0, numbers);
    return (double) sum / n;
}

// P3 allPair()
// 给你两个 ConsList<Integer>，返回它们的笛卡尔积中所有的 Pair，并按字典序输出（即先按第一个元素升序，再按第二个元素升序）。
// 例：list1 = {11,5,7}，list2 = {2,8} →
// (5,2),(5,8),(7,2),(7,8),(11,2),(11,8)（先排序再成对枚举）。
static ConsList<Pair<Integer,Integer>> allPair(ConsList<Integer> list1, ConsList<Integer> list2) {
    ConsList<Integer> a = Sort(list1); // 升序
    ConsList<Integer> b = Sort(list2); // 升序

    // 对每个 a，把 (a,b)（b ∈ B）映射成一段 Pair 列表，再 Append 到总结果
    return FoldLeft(
        (Integer x, ConsList<Pair<Integer,Integer>> acc) ->
            Append(acc, Map((Integer y) -> new Pair<>(x, y), b)),
        new Nil<>(),
        a
    );
}

// P4-1 BinTreeFold
// 实现二叉树折叠：BinTreeFold(BiFunction<T,T,T> agg, BinTree<T> tree)
// 示例：agg: (a,b) -> a - b，树结构（叶子从左到右为 1,2,3,4）→ 结果 ((1-2)-(3-4)) = 0。
sealed interface BinTree<T> permits Leaf, Branch {}
record Leaf<T>(T value) implements BinTree<T> {}
record Branch<T>(BinTree<T> left, BinTree<T> right) implements BinTree<T> {}

static <T> T BinTreeFold(BiFunction<T,T,T> agg, BinTree<T> tree) {
    return switch (tree) {
        case Leaf<T> l   -> l.value();
        case Branch<T> b -> agg.apply(BinTreeFold(agg, b.left()), BinTreeFold(agg, b.right()));
    };
}


// P4-2 BinTreeMap
// 实现二叉树映射：BinTreeMap(Function<T,T> mapper, BinTree<T> tree)
// 示例：mapper: a -> Length(a)，树叶是 "abc", "x", "34", "" → 返回同结构的新树，叶子分别变为 3, 1, 2, 0。
static <S,T> BinTree<T> BinTreeMap(Function<S,T> mapper, BinTree<S> tree) {
    return switch (tree) {
        case Leaf<S> l   -> new Leaf<>(mapper.apply(l.value()));
        case Branch<S> b -> new Branch<>(BinTreeMap(mapper, b.left()),
                                         BinTreeMap(mapper, b.right()));
    };
}


void main() {
    // P1/P2
    ConsList<String> ss = MakeList("ab","", "xyz");
    ConsList<Integer> ns = MakeList(10, 20, 30);
    System.out.println(totalLength(ss));     // 5
    System.out.println(average(ns));         // 20.0

    // P3
    System.out.println(allPair(MakeList(11,5,7), MakeList(2,8)));
    // 期望: [(5,2),(5,8),(7,2),(7,8),(11,2),(11,8)]（打印格式由库决定）

    // P4-1
    BinTree<Integer> t1 =
        new Branch<>(
            new Branch<>(new Leaf<>(1), new Leaf<>(2)),
            new Branch<>(new Leaf<>(3), new Leaf<>(4))
        );
    int folded = BinTreeFold((a,b) -> a - b, t1);
    System.out.println(folded);              // 0  (= (1-2) - (3-4))

    // P4-2
    BinTree<String> t2 =
        new Branch<>(
            new Branch<>(new Leaf<>("abc"), new Leaf<>("x")),
            new Branch<>(new Leaf<>("34"),  new Leaf<>(""))
        );
    BinTree<Integer> t2Mapped = BinTreeMap((String s) -> Length(s), t2);
    // 结构不变，叶子变为 3,1,2,0
    System.out.println(t2Mapped);
}
