import static comp1110.testing.Comp1110Unit.*;
void incrementAll(int[] numbers){
    for(int i=0;i<numbers.length;i++){
        numbers[i]++;
    }
}
void main(){
    testExercise1();
}
void testExercise1()
{
   int [] numbers = new int[3];
   numbers[0]=1;
   numbers[1]=2;
   numbers[2]=3;
   incrementAll(numbers);
   testEqual(2, numbers[0]);
   testEqual(3, numbers[1]);
   testEqual(4, numbers[2]);
}
