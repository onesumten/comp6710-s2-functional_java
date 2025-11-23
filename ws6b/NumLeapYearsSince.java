import static comp1110.testing.Comp1110Unit.*;
int numLeapYearsSince(int year){
    List<Integer> result = new ArrayList<>();
    for(int i = year;i<=2025;i++){
        if(i%4==0){
            result.add(i);
        }
    }
    return result.size();
}
int numLeapYearsSince1(int year){
    int count = 0;
    for(int i = year;i<=2025;i++){
        if(i%4==0){
           count++;
        }
    }
    return count;
}
int numLeapYearsSince2(int year){
    int lastYear = 2024;
    int leapYearFromLast = lastYear/4;
    int leapYearFromNow = (year-1)/4;
    int result = leapYearFromLast-leapYearFromNow;
    return result;
}
void main(){
    testExercise3();
}
void testExercise3() {
    testEqual(numLeapYearsSince2(1976), 13);
    testEqual(numLeapYearsSince2(1989), 9);
}
