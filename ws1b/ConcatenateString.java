import static comp1110.testing.Comp1110Unit.*;
String concatenateString(String left, String right){
    return left+right;
}

void main(){
    test();
    String result = concatenateString("H", "i");
    println(result);
}

void test(){
     runAsTest(this::testExercise2b);
}

void testExercise2b(){
    testEqual(concatenateString("", ""), "", "Special case: Addition of two empty string.");
    testEqual(concatenateString("a", ""), "a", "Special case: Addition of one String and one empty String.");
}