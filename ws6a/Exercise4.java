import static comp1110.testing.Comp1110Unit.*;
String fizzWizzBuzzRizz(int input){
    /**
     * 先通过创建一个list来存储，你好
     */
    List<String> words = new ArrayList<>();
    if(input % 3 == 0){
        words.add("Fizz");
    }
    if(input % 5 == 0){
        words.add("Wizz");
    }
    if(input % 7 == 0){
        words.add("Buzz");
    }
    if(input % 11 == 0){
        words.add("Rizz");
    }
    if(words.isEmpty()){
        return "";
    }
    StringBuilder newWords = new StringBuilder();
    newWords.append(words.get(0));
    for(int i = 1;i<words.size();i++){
        newWords.append(" ");
        newWords.append(words.get(i).toLowerCase());
    }
    newWords.append("!");
    return newWords.toString();
}
void main(){
    testExercise4();
}
void testExercise4() {
    // Single-word cases
    String expected1 = "Fizz!";
    String actual1 = fizzWizzBuzzRizz(3);
    testEqual(expected1, actual1, "Divisible by 3 only -> Fizz!");

    String expected2 = "Wizz!";
    String actual2 = fizzWizzBuzzRizz(5);
    testEqual(expected2, actual2, "Divisible by 5 only -> Wizz!");

    String expected3 = "Buzz!";
    String actual3 = fizzWizzBuzzRizz(7);
    testEqual(expected3, actual3, "Divisible by 7 only -> Buzz!");

    String expected4 = "Rizz!";
    String actual4 = fizzWizzBuzzRizz(11);
    testEqual(expected4, actual4, "Divisible by 11 only -> Rizz!");

    // No word
    String expected5 = "";
    String actual5 = fizzWizzBuzzRizz(13);
    testEqual(expected5, actual5, "Divisible by none -> empty string");

    // All of the words (3 * 5 * 7 * 11 = 1155)
    String expected6 = "Fizz wizz buzz rizz!";
    String actual6 = fizzWizzBuzzRizz(1155);
    testEqual(expected6, actual6, "Divisible by 3,5,7,11 -> all words");

    // Some but not all
    String expected7 = "Fizz wizz!";
    String actual7 = fizzWizzBuzzRizz(15);
    testEqual(expected7, actual7, "Divisible by 3 & 5 -> Fizz wizz!");

    // Some but not all
    String expected8 = "Wizz rizz!";
    String actual8 = fizzWizzBuzzRizz(55);
    testEqual(expected8, actual8, "Divisible by 5 & 11 -> Wizz rizz!");
}
