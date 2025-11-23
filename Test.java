import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;
// 使用FoldLeft函数翻转链表
static <T> ConsList<T> reverseFoldLeft(ConsList<T> list){
    return FoldLeft((T x, ConsList<T> y) -> new Cons<>(x,y), new Nil<>(), list);
}
//Append贯彻Last In First Out.
static <T> ConsList<T> reverseAppend(ConsList<T> list){
    return IsEmpty(list)?new Nil<>():Append(reverseAppend(Rest(list)), MakeList(First(list)));
}
// 将链表内的字符串拼接并且返回一个字符串
String concatStringsFoldLeft(ConsList<String> list){
    return FoldLeft((String x, String acc)->acc+x, "", list);
}
String concatStringsFold(ConsList<String> list){
    return Fold((String x, String acc)->x+acc, "", list);
}
//将链表先排列，然后从第一个和第二个开始相互对比，去除掉重复数字。
ConsList<Integer> uniqueSorted(ConsList<Integer> list){
     ConsList<Integer> sorted = Sort(list);
     return uniqueHelp(sorted);
}

ConsList<Integer> uniqueHelp(ConsList<Integer> list){
    if(IsEmpty(list)){return list;}
    if(IsEmpty(Rest(list))){return list;}
    Integer a = First(list);
    Integer b = First(Rest(list));
    return Equals(a,b) ? uniqueHelp(Rest(list)):new Cons<>(a,uniqueHelp(Rest(list))); 
}
//将两个长度相同的链表相加，通过Map函数一一相加，然后返回一个新的总和链表
ConsList<Integer> pairwiseSumMap(ConsList<Integer> list1, ConsList<Integer> list2){
    return Map((x,y)-> x+y,list1,list2);
}
//同上，不过使用了递归
ConsList<Integer> pairwiseSumRecursion(ConsList<Integer> list1, ConsList<Integer> list2){
    if(IsEmpty(list1)&&IsEmpty(list2)){
        return new Nil<>();
    }
    Integer sum = First(list1)+First(list2);
    return new Cons<>(sum, pairwiseSumRecursion(Rest(list1), Rest(list2)));
}
//斐波那契数列
ConsList<Integer> fibList(int n){
    return reverseAppend(fibAcc(n,0,1,new Nil<>()));
}
ConsList<Integer> fibAcc(int k, int a, int b, ConsList<Integer> acc){
    return k==0 ? acc: fibAcc(k-1, b, a+b, new Cons<>(a,acc));
}
//将一个大数组的内部小数组整合成一个大数组，如：[[1,2],[3],[4,5]] -> [1,2,3,4,5]
ConsList<Integer> flatten(ConsList<ConsList<Integer>> list){
    return IsEmpty(list) ? new Nil<>(): Append(First(list),flatten(Rest(list)));
}
//统计一个字符串的每个字符的出现次数，如：："abaac" -> [(a,3),(b,1),(c,1)]
ConsList<Pair<Character,Integer>> countChar(String s){
    int n = Length(s);
    ConsList<Integer> index = BuildList(n, i->i);
    return FoldLeft((Integer i, ConsList<Pair<Character, Integer>> acc)->{
        char c = GetCharAt(s, i);
        int newV = Default(Map(Get(acc,c), (Integer oldValue)->oldValue+1),1);
        return Put(acc,c,newV);
    },
    new Nil<>(),
    index
    );
}
// 将字符串 s 转换为一个 ConsList<Character> 字符列表，目的是为了给 FoldLeft 函数提供一个可遍历的集合。
ConsList<Pair<Character,Integer> countChar1(String s){
    ConsList<Character> chardex = MakeList(s);
    return FoldLeft((Character c, ConsList<Pair<Character, Integer>> acc)
    ->{
        int newV = Default(Map(Get(acc,c), (Integer oldValue)->oldValue+1),1);
        return Put(acc,c,newV);
    },
    new Nil<>(),
    chardex
    );
}
void main(){
    println(reverseFoldLeft(MakeList(1,2,3,4)));
    println(reverseAppend(MakeList(1,2,3,4)));
    println(concatStringsFoldLeft(MakeList("Hi",",","Friend")));
    println(concatStringsFold(MakeList("Hi",",","Friend")));
    println(uniqueSorted(MakeList(1,2,2,3,3,4)));
    println(pairwiseSumMap(MakeList(1,2,3), MakeList(2,3,4)));
    println(pairwiseSumRecursion(MakeList(1,2,3), MakeList(1,3,4)));
    println(fibList(4));
    println(flatten(MakeList(MakeList(1,2),MakeList(3),MakeList(4,5))));
    println(countChar("abc"));
}