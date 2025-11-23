import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;

sealed interface BinaryTree permits Leaf, Node{}
record Leaf() implements BinaryTree{}
record Node(BinaryTree left, BinaryTree right) implements BinaryTree{}

sealed interface Tree permits Leaf, Node{}
record Leaf(int value) implements Tree{}
record Node(int value, ConsList<Tree> children) implements Tree{}

sealed interface MixedTree permits BiNode, Node{}
record BiNode(MixedTree left, MixedTree right){}
record Node(ConsList<MixedTree> children){}
//树的高度
int Height(BinaryTree tree){
    return switch(tree){
        case leaf() -> 1;
        case Node(var left, var right) -> 1 + Max(Height(left), Height(right));
    };
}
int Height1(Tree tree){
    return switch(tree){
        case Leaf l -> 1;
        case Node n -> {
            //将所有子节点的高度集合起来形成一个链表
            ConsList<Tree> sumhe = Map(x -> Height1(x), n.children);
            //从子节点高度中找出最大的高度
            int mx = FoldLeft((Integer h, Integer acc) -> Max(acc,h),0,sumhe);
            yield 1+mx;
        }
    };
}
//统计叶子的数量
int countLeaves(Tree tree){
    return switch(tree){
        case Leaf l -> 1;
        case Node n -> FoldLeft((Tree child, Integer acc) -> acc+countLeaves(child),0,n.children);
    };
}
//先序遍历输出所有值
/**
 * 返回一棵树中所有节点的值（前序遍历：先根，再子树）。
*/
ConsList<Integr> preorder(Tree t){
    return switch(t){
        case Leaf l -> MakeList(l.value);
        case Node n -> {
            ConsList<ConsList<Integer>> lists = Map(x->preorder(x), n.children);
            return new Cons<>(n.value, flatten(lists));
        }
    };
}
ConsList<T> flatten(ConsList<ConsList<T>> xss){
    return FoldLeft((ConsList<T> xs, ConsList<T> acc) -> Append(acc,xs), new Nil<>(), xss);
}
//在树中查找一个值
boolean contains(Tree t, int target){
    return switch(t){
        case Leaf l -> Equals(l.value, target);
        case Node n -> Equals(n.value, target)||FoldLeft((Tree child, Boolean acc)-> acc|| contains(child,target),false,n.children);
    };
}

BinaryTree E = new leaf();
BinaryTree D = new leaf();
BinaryTree B = new node(D,E);
BinaryTree C = new leaf();
BinaryTree A = new node(B,C);

void main(){
    println(Height(A));
}