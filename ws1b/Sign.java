import static comp1110.testing.Comp1110Unit.*;
int sign(int number){
    if(number>=0){
        return number;
    }else{
        return -number;
    }
}
void main(){
    test();
    int result = sign(3);
    int result2 = sign(-1);
    println(result+" "+result2);
}
void test(){
    runAsTest(this::testExercise5);
}
void testExercise5(){
    testEqual(sign(0), 0, "Normal case: If the number is 0.");
    testEqual(sign(1), 1, "Normal case: If the number is 1.");
    testEqual(sign(-1), 1, "Special case: If the number is negative.");
}