void main() {
    int x = 2;
    int y = 7;
    
    println("x: "+x);
    println("y: "+y);

    int z = x;
    x = y;
    y = z;

    println("x: "+x);
    println("y: "+y);
}
