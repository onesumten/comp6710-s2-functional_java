import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;
/**
 * 题目：使用 FoldLeft 翻转链表
 * * 题目描述：
 * 编写一个函数 reverseFoldLeft，使用 FoldLeft 高阶函数来实现链表的翻转。
 * 该函数接收一个类型为 ConsList<T> 的链表作为输入，
 * 返回一个新的 ConsList<T>，其元素顺序与原列表相反。
 * * 规则：
 * - 必须使用 FoldLeft 函数实现。
 * * 测试用例：
 * - 输入：[1, 2, 3] → 输出：[3, 2, 1]
 * - 输入：["a", "b"] → 输出：["b", "a"]
 * - 输入：[] → 输出：[]
 */
static <T> ConsList<T> reverseFoldLeft(ConsList<T> list){
    return FoldLeft((T x, ConsList<T> y) -> new Cons<>(x,y), new Nil<>(), list);
}

/**
 * 题目：使用 Append 递归翻转链表
 * * 题目描述：
 * 编写一个函数 reverseAppend，使用递归和 Append 函数来实现链表的翻转。
 * 该函数接收一个类型为 ConsList<T> 的链表作为输入，
 * 返回一个类型为 ConsList<T> 的新链表，其元素顺序与原列表相反。
 * * 规则：
 * - 必须使用递归和 Append 函数。
 * - Append 具有 Last In First Out 的效果 (通常 Append 是 O(n)，用于此处的递归会效率较低，但作为练习题目的要求)。
 * * 测试用例：
 * - 输入：[1, 2, 3] → 输出：[3, 2, 1]
 * - 输入：["a", "b"] → 输出：["b", "a"]
 * - 输入：[] → 输出：[]
 */
static <T> ConsList<T> reverseAppend(ConsList<T> list){
    return IsEmpty(list)?new Nil<>():Append(reverseAppend(Rest(list)), MakeList(First(list)));
}

/**
 * 题目：使用 FoldLeft 拼接字符串
 * * 题目描述：
 * 编写一个函数 concatStringsFoldLeft，使用 FoldLeft 高阶函数将链表中的所有字符串元素拼接成一个单一的字符串。
 * 该函数接收一个类型为 ConsList<String> 的字符串链表作为输入，
 * 返回一个 String，是所有链表元素按原顺序拼接的结果。
 * * 规则：
 * - 必须使用 FoldLeft 函数实现。
 * * 测试用例：
 * - 输入：["Hello", " ", "World"] → 输出："Hello World"
 * - 输入：["a", "b", "c"] → 输出："abc"
 * - 输入：[] → 输出：""
 */
String concatStringsFoldLeft(ConsList<String> list){
    return FoldLeft((String x, String acc)->acc+x, "", list);
}

/**
 * 题目：使用 Fold (Right/Left) 拼接字符串
 * * 题目描述：
 * 编写一个函数 concatStringsFold，使用 Fold 高阶函数将链表中的所有字符串元素拼接成一个单一的字符串。
 * 该函数接收一个类型为 ConsList<String> 的字符串链表作为输入，
 * 返回一个 String，是所有链表元素拼接后的结果。
 * * 注意：根据您的实现 (x+acc)，如果 Fold 是 FoldRight，结果可能是翻转的。
 * 如果 Fold 是 FoldLeft (如上一个函数)，结果是正序的。
 * 此处的实现 (x+acc) 看起来是为了展示与 FoldLeft(acc+x) 的区别。
 * * 规则：
 * - 必须使用 Fold 函数实现。
 * * 测试用例：
 * - 输入：["a", "b", "c"] 
 * - (假设 Fold 是 FoldRight) → 输出："cba"
 * - (假设 Fold 是 FoldLeft) → 输出："abc"
 * - 输入：[] → 输出：""
 */
String concatStringsFold(ConsList<String> list){
    return Fold((String x, String acc)->x+acc, "", list);
}

/**
 * 题目：排序并去除重复元素
 * * 题目描述：
 * 编写一个函数 uniqueSorted，它接收一个类型为 ConsList<Integer> 的整数链表作为输入。
 * 函数首先对链表进行排序，然后返回一个新的 ConsList<Integer>，其中所有重复的元素都被去除。
 * * 规则：
 * - 必须先使用 Sort(list) 对列表进行排序。
 * - 排序后，使用辅助函数 uniqueHelp 遍历列表，通过比较相邻元素来消除重复项。
 * * 测试用例：
 * - 输入：[3, 1, 4, 1, 5, 9, 2, 6] → (先排序) [1, 1, 2, 3, 4, 5, 6, 9] → (去重) [1, 2, 3, 4, 5, 6, 9]
 * - 输入：[5, 5, 5] → 输出：[5]
 * - 输入：[1, 2, 3] → 输出：[1, 2, 3]
 */
ConsList<Integer> uniqueSorted(ConsList<Integer> list){
     ConsList<Integer> sorted = Sort(list);
     return uniqueHelp(sorted);
}

/**
 * 题目：辅助函数：去除已排序链表中的重复元素
 * * 题目描述：
 * 编写一个辅助函数 uniqueHelp，用于去除**已排序**整数链表中的重复元素。
 * 该函数接收一个类型为 ConsList<Integer> 的已排序链表作为输入，
 * 返回一个新的 ConsList<Integer>，其中相邻的重复元素被跳过，只保留一个。
 * * 规则：
 * - 该函数假设输入链表已经经过排序。
 * - 采用递归方式实现，通过比较当前元素 A 和下一个元素 B 来决定是否保留 A。
 * * 测试用例：
 * - 输入：[1, 1, 2, 3, 3, 3, 4] → 输出：[1, 2, 3, 4]
 * - 输入：[5] → 输出：[5]
 * - 输入：[] → 输出：[]
 */
ConsList<Integer> uniqueHelp(ConsList<Integer> list){
    if(IsEmpty(list)){return list;}
    if(IsEmpty(Rest(list))){return list;}
    Integer a = First(list);
    Integer b = First(Rest(list));
    return Equals(a,b) ? uniqueHelp(Rest(list)):new Cons<>(a,uniqueHelp(Rest(list))); 
}

/**
 * 题目：使用 Map 函数按位求和
 * * 题目描述：
 * 编写一个函数 pairwiseSumMap，将两个长度相同的整数链表进行按位（逐个元素）相加。
 * 该函数接收两个 ConsList<Integer> 链表（list1 和 list2）作为输入，
 * 返回一个新的 ConsList<Integer>，其中每个元素是 list1 和 list2 对应元素的总和。
 * * 规则：
 * - 必须使用 Map 高阶函数实现。
 * - 假设两个输入链表长度相同。
 * * 测试用例：
 * - 输入：[1, 2, 3] 和 [10, 20, 30] → 输出：[11, 22, 33]
 * - 输入：[] 和 [] → 输出：[]
 */
ConsList<Integer> pairwiseSumMap(ConsList<Integer> list1, ConsList<Integer> list2){
    return Map((x,y)-> x+y,list1,list2);
}

/**
 * 题目：使用递归按位求和
 * * 题目描述：
 * 编写一个函数 pairwiseSumRecursion，将两个长度相同的整数链表进行按位（逐个元素）相加。
 * 该函数接收两个 ConsList<Integer> 链表（list1 和 list2）作为输入，
 * 返回一个新的 ConsList<Integer>，其中每个元素是 list1 和 list2 对应元素的总和。
 * * 规则：
 * - 必须使用递归实现，不能使用 Map 或其他高阶函数。
 * - 假设两个输入链表长度相同。
 * * 测试用例：
 * - 输入：[1, 2, 3] 和 [10, 20, 30] → 输出：[11, 22, 33]
 * - 输入：[] 和 [] → 输出：[]
 */
ConsList<Integer> pairwiseSumRecursion(ConsList<Integer> list1, ConsList<Integer> list2){
    if(IsEmpty(list1)&&IsEmpty(list2)){
        return new Nil<>();
    }
    Integer sum = First(list1)+First(list2);
    return new Cons<>(sum, pairwiseSumRecursion(Rest(list1), Rest(list2)));
}

/**
 * 题目：生成斐波那契数列（主函数）
 * * 题目描述：
 * 编写一个函数 fibList，生成前 n 个斐波那契数列的数字。
 * 该函数接收一个整数 n 作为输入（表示要生成的项数），
 * 返回一个 ConsList<Integer>，包含从第 0 项到第 n-1 项的斐波那契数字。
 * * 规则：
 * - 必须使用尾递归辅助函数 fibAcc 实现。
 * - 结果链表需要按升序（从 F(0) 开始）排列。
 * * 测试用例：
 * - 输入：5 → 输出：[0, 1, 1, 2, 3]
 * - 输入：1 → 输出：[0]
 */
ConsList<Integer> fibList(int n){
    return reverseAppend(fibAcc(n,0,1,new Nil<>()));
}

/**
 * 题目：辅助函数：尾递归计算斐波那契数列
 * * 题目描述：
 * 编写一个尾递归辅助函数 fibAcc，用于高效地计算斐波那契数列。
 * 该函数接收当前剩余项数 k，前两项 a 和 b，以及累加器 acc（已计算的斐波那契数字链表）作为输入，
 * 返回一个 ConsList<Integer>，包含从 F(0) 开始的 k 项斐波那契数字（注意：结果是逆序的）。
 * * 规则：
 * - 必须使用尾递归实现。
 * - F(0)=0, F(1)=1。
 * - 该函数返回的结果链表是逆序的，需要由主函数翻转。
 */
ConsList<Integer> fibAcc(int k, int a, int b, ConsList<Integer> acc){
    return k==0 ? acc: fibAcc(k-1, b, a+b, new Cons<>(a,acc));
}

/**
 * 题目：扁平化链表（展平嵌套链表）
 * * 题目描述：
 * 编写一个函数 flatten，用于将一个包含链表的链表（嵌套链表）展平为一个单一的链表。
 * 该函数接收一个类型为 ConsList<ConsList<Integer>> 的嵌套链表作为输入，
 * 返回一个 ConsList<Integer>，其中包含所有内部链表的元素，按原顺序排列。
 * * 规则：
 * - 必须使用递归和 Append 函数实现。
 * * 测试用例：
 * - 输入：[[1, 2], [3], [4, 5]] → 输出：[1, 2, 3, 4, 5]
 * - 输入：[[10], [], [20]] → 输出：[10, 20]
 * - 输入：[] → 输出：[]
 */
ConsList<Integer> flatten(ConsList<ConsList<Integer>> list){
    return IsEmpty(list) ? new Nil<>(): Append(First(list),flatten(Rest(list)));
}

/**
 * 题目：统计字符串中字符出现次数（基于索引）
 * * 题目描述：
 * 编写一个函数 countChar，统计给定字符串中每个字符的出现次数。
 * 该函数接收一个 String s 作为输入，
 * 返回一个 ConsList<Pair<Character, Integer>>，其中每个 Pair 表示一个字符及其出现次数。
 * * 规则：
 * - 通过 BuildList(n, i->i) 创建索引列表，然后使用 FoldLeft 遍历这些索引。
 * - 使用 Map 和 Get/Put 等函数操作累加器（ConsList<Pair<Character, Integer>>）进行计数。
 * - 这里的累加器被用作一个简易的 Map/字典结构。
 * * 测试用例：
 * - 输入："abaac" → 输出：[(a, 3), (b, 1), (c, 1)] 
 * - 输入："zzza" → 输出：[(z, 3), (a, 1)]
 */
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

/**
 * 题目：统计字符串中字符出现次数（基于字符列表）
 * * 题目描述：
 * 编写一个函数 countChar1，统计给定字符串中每个字符的出现次数。
 * 该函数接收一个 String s 作为输入，
 * 返回一个 ConsList<Pair<Character, Integer>>，其中每个 Pair 表示一个字符及其出现次数。
 * * 规则：
 * - 通过 MakeList(s) 将字符串转换为 ConsList<Character> 字符列表。
 * - 使用 FoldLeft 遍历字符列表。
 * - 使用 Map 和 Get/Put 等函数操作累加器（ConsList<Pair<Character, Integer>>）进行计数。
 * - 这里的累加器被用作一个简易的 Map/字典结构。
 * * 测试用例：
 * - 输入："abaac" → 输出：[(a, 3), (b, 1), (c, 1)] 
 * - 输入："zzza" → 输出：[(z, 3), (a, 1)]
 */
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