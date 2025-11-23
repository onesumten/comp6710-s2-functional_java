import static comp1110.testing.Comp1110Unit.*;
int factorial(int n){
    int result = 1;
    for(int i=1;i<=n;i++){
        result = result*i;
    }
    return result;
}
void main(){
    testExercise1();
}
void testExercise1()
{
  testEqual(factorial(1), 1   , "Base case: factorial of 1");
  testEqual(factorial(2), 2   , "Recursive case: factorial of 2");
  testEqual(factorial(4), 24  , "Recursive case: factorial of 4");  
  testEqual(factorial(6), 720 , "Recursive case: factorial of 6");
}
