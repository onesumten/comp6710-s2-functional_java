import static comp1110.testing.Comp1110Unit.*;
import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;
int greatestOfThree(int a, int b, int c){
    if(a>=b && a>=c){
        return a;
    }else if(b>=a && b>=c){
        return b;
    }else{
        return c;
    }
}
int greatestOfThree1(int a, int b, int c){
    return Max(a, Max(b,c));
}

void main(){
    test();
}
void test() {
    runAsTest(this::testExercise7);
}

void testExercise7() {
    testEqual(greatestOfThree(0,0,0), 0, "Special: Every number is equal and zero");
    testEqual(greatestOfThree(-1,0,1), 1, "General: Contains a negative number");
    testEqual(greatestOfThree(-1,-2,-3), -1, "General: Contains only negative numbers");
    testEqual(greatestOfThree(1,3,2), 3, "General: Contains 3 non-equal numbers");
    testEqual(greatestOfThree(2,1,2), 2, "Special: Two numbers have the greatest value");
    testEqual(greatestOfThree(3,1,4), 4, "Special: c>a>b");
    testEqual(greatestOfThree1(0,0,0), 0, "Special: Every number is equal and zero");
    testEqual(greatestOfThree1(-1,0,1), 1, "General: Contains a negative number");
    testEqual(greatestOfThree1(-1,-2,-3), -1, "General: Contains only negative numbers");
    testEqual(greatestOfThree1(1,3,2), 3, "General: Contains 3 non-equal numbers");
    testEqual(greatestOfThree1(2,1,2), 2, "Special: Two numbers have the greatest value");
    testEqual(greatestOfThree1(3,1,4), 4, "Special: c>a>b");
}
