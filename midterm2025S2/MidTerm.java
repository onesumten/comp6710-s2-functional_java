import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;
//P1
int totalLength1(ConsList<String> strings){
    return switch(strings){
        case Nil<String>() -> 0;
        case Cons<String>(var first, var rest) -> Length(first)+totalLength1(rest);
    };
}
int totalLength2(ConsList<String> strings){
    return FoldLeft((String str, Integer acc)->acc+Length(str), 0, strings);
}
//P2
double average(ConsList<Integer> numbers){
    int n = Length(numbers);
    //题目说假设input list都是non-empty，所以其实没必要有这个
    if(Equals(n,0)){
        return DefaultCaseError("It is an empty list");
    }
    double sum = FoldLeft((Integer num, Double acc)->acc+num,0.0,numbers);
    return sum/n;
}
//P3
ConsList<Pair<Integer,Integer>> allPair(ConsList<Integer> l1, ConsList<Integer> l2){
    ConsList<Integer> sortedl1 = Sort(l1);
    ConsList<Integer> sortedl2 = Sort(l2);
    return FoldLeft(
        (Integer x, ConsList<Pair<Integer,Integer>> acc)
        ->Append(acc,Map((Integer y)->new Pair<>(x,y),sortedl2))
        ,new Nil<>()
        ,sortedl1);
}
ConsList<Pair<Integer,Integer>> allPair2(ConsList<Integer> l1, ConsList<Integer> l2){
    ConsList<Integer> sortedl1 = Sort(l1);
    ConsList<Integer> sortedl2 = Sort(l2);
    return allPairRecursive(sortedl1,sortedl2);
}
//将pariWithList的链表结果从小到大排序
ConsList<Pair<Integer, Integer>> allPairRecursive(ConsList<Integer> a, ConsList<Integer> b){
    return switch(a){
        case Nil<Integer> nil -> new Nil<>();
        case Cons<Integer> cons -> {
            ConsList<Pair<Integer,Integer>> pairsForFirst = pairWithList(First(cons), b);
            yield Append(pairsForFirst, allPairRecursive(Rest(cons), b));
        }
    };
}
//将a中的第一个元素与b中的所有元素配对，形成Pair<>类型的新链表
ConsList<Pair<Integer, Integer>> pairWithList(Integer firstelement, ConsList<Integer> b){
    return switch(b){
        case Nil<Integer> nil -> new Nil<>();
        case Cons<Integer> cons ->{
            Pair<Integer,Integer> newPair = new Pair<>(firstelement, First(b));
            yield new Cons<>(newPair, pairWithList(firstelement,Rest(b)));
        }
    };
}
//p4-1
sealed interface BinTree<T> permits Leaf,Node {}
record Leaf<T>(T value) implements BinTree<T>{}
record Node<T>(BinTree<T> left, BinTree<T> right) implements BinTree<T>{}

static <T> T binTreeFold(BiFunction<T,T,T> agg, BinTree<T> tree){
    return switch(tree){
        case Leaf<T>(var value) -> value;
        case Node<T>(var left, var right) -> agg.apply(binTreeFold(agg,left), binTreeFold(agg,right));
    };
}
void main(){
    println(totalLength1(MakeList("hi","ab","")));
    println(totalLength1(MakeList()));
    println(totalLength2(MakeList("hi","ab","")));
    println(average(MakeList(3,4,5)));
    println(allPair(MakeList(11,5,7),MakeList(2,8)));
    println(allPair2(MakeList(11,5,7),MakeList(2,8)));
   BinTree<Integer> t1 =
        new Node<>(
            new Node<>(new Leaf<>(1), new Leaf<>(2)),
            new Node<>(new Leaf<>(3), new Leaf<>(4))
        );
    int folded = binTreeFold((a,b) -> a - b, t1);
    println(folded);           
}