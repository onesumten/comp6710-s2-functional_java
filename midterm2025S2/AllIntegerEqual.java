import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;

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
boolean allIntegersEqual(ConsList<Integer> aie) {
    return switch(aie) {
        case Nil() -> true;
        case Cons(var first, var rest) -> IsEmpty(rest) ? true : Equals(first, First(rest)) ? allIntegersEqual(rest) :false;
    };
}


void main(){
   runAsTest(this::testExercise7);
}

void testExercise7() {
    ConsList<Integer> list1 = MakeList(); 
    testTrue(allIntegersEqual(list1), "Empty list");

    ConsList<Integer> list2 = MakeList(1); 
    testTrue(allIntegersEqual(list2), "One element");

    ConsList<Integer> list3 = MakeList(1,1,1); 
    testTrue(allIntegersEqual(list3), "Multiple elements (equal)");

    ConsList<Integer> list4 = MakeList(2,2,2,2,2,2); 
    testTrue(allIntegersEqual(list4), "Multiple elements (equal)");

    ConsList<Integer> list5 = MakeList(2,2,2,2,2,1); 
    testFalse(allIntegersEqual(list5), "Last element unequal");

    ConsList<Integer> list6 = MakeList(4,3,3,3,3); 
    testFalse(allIntegersEqual(list6), "First element unequal");

    ConsList<Integer> list7 = MakeList(2,2,2,3,2,2,2); 
    testFalse(allIntegersEqual(list7), "Middle element unequal");

    ConsList<Integer> list8 = MakeList(1,2,3,4,5,6); 
    testFalse(allIntegersEqual(list8), "No elements equal");
}
