import static comp1110.testing.Comp1110Unit.*;

int addIntegerNumbers(int a, int b){
    return a+b;
}
void main(){
     test();
}
void test(){
     runAsTest(this::testExercise2a);
}

void testExercise2a(){
    testEqual(addIntegerNumbers(3,5), 8, "General case, Addition of two positive numbers.");
}
