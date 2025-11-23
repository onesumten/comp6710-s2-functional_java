import static comp1110.testing.Comp1110Unit.*;
int daysRemaining(int firstday){
    return 32-firstday;
}
void main(){
    test();
    int remainday = daysRemaining(1);
    println(remainday);
}
void test(){
     runAsTest(this::testExercise6);
}
void testExercise6(){
    testEqual(daysRemaining(1), 31);
    testEqual(daysRemaining(31), 1);
    testEqual(daysRemaining(5), 27);
}