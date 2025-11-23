ConsList<Integer> arrayToConsListFor(int[] numbers){
    ConsList<Integer> currentList = new Nil<>();
    for(int i = numbers.length-1;i>=0;i--){
        currentList = new Cons<>(numbers[i],currentList);
    }
    return currentList;
}

ConsList<Integer> arrayToConsListWhile(int[] numbers){
    ConsList<Integer> currentList = new Nil<>();
    int i = numbers.length-1;
    while(i>=0){
        currentList = new Cons<>(numbers[i],currentList);
        i--;
    }
    return currentList;
}