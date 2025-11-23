在 record File(String name, int size) implements Item {} 这段代码中，name 和 size 被称为 record components（记录组件）。

你可以将它们理解为：

成员变量（Member variables）：它们是 File 类的实例变量(instance variables)。

构造函数参数（Constructor parameters）：它们在创建 File 实例时被赋值。

访问器方法（Accessor methods）：Java 编译器会自动为每个记录组件生成一个同名的公共方法，例如 file.name() 和 file.size()，用来获取这些组件的值。
------------------------------------------
具名模式（Named Pattern）: case File file 这一部分尝试将匹配到的 File 对象绑定到一个名为 file 的新变量上。

解构模式（Deconstruction Pattern）: (var name, var size) 这一部分尝试将 File 对象中的组件（name 和 size）解构到单独的变量中。
===================================================
基本数据类型 (int, double, char, etc.)：它们不是类。它们直接存储值，不具备对象的属性和方法。它们在内存中占用固定的、较小的空间，并且存放在栈（stack）上。

对象类型 (Integer, Double, Character, etc.)：它们是类。它们是封装了基本数据类型的包装类（wrapper classes）。它们存储在堆（heap）上，拥有方法和字段，可以被赋予 null 值。
=======================================================
activation record
function parameters
local variables
return address
