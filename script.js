
print('Hello World!');//加载文件时，即运行代码

var fun1 = function(name) {	//java调用的js代码
    print('Hi there from Javascript, ' + name);
    return "greetings from javascript";
};
 
var fun2 = function (object) {//java调用js的代码
    print("JS Class Definition: " + Object.prototype.toString.call(object));
};

/** JS调用Java的代码 begin **/
var MyJavaClass = Java.type('com.tsty.nashorn.javajs.TestNashorn');

var result = MyJavaClass.fun1('John Doe');
print(result);

MyJavaClass.fun2(123);
//class java.lang.Integer

MyJavaClass.fun2(49.99);
//class java.lang.Double

MyJavaClass.fun2(true);
//class java.lang.Boolean

MyJavaClass.fun2("hi there")
//class java.lang.String

MyJavaClass.fun2(new Number(23));
//class jdk.nashorn.internal.objects.NativeNumber

MyJavaClass.fun2(new Date());
//class jdk.nashorn.internal.objects.NativeDate

MyJavaClass.fun2(new RegExp());
//class jdk.nashorn.internal.objects.NativeRegExp

MyJavaClass.fun2({foo: 'bar'});
//class jdk.nashorn.internal.scripts.JO4


MyJavaClass.fun3({
    foo: 'bar',
    bar: 'foo'
});
 
// Object: [foo, bar]



/** 我们也可以在Java端调用JavaScript对象中的函数。
 * 我们首先定义一个JavaScript类型 Person，包含属性 firstName 、lastName 和函数getFullName。 */
function Person(firstName, lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.getFullName = function() {
        return this.firstName + " " + this.lastName;
    }
}
// 我们传入一个新的person给java 方法
var person1 = new Person("Peter","Parker");
MyJavaClass.fun4(person1);

/** JS调用Java的代码 end **/


/** 类型数组 */
//原始javascript 数组是无类型的。 Nashorn可以让你在JavaScript中使用java数组：

var IntArray = Java.type("int[]");
 
var array = new IntArray(5);
array[0] = 5;
array[1] = 4;
array[2] = 3;
array[3] = 2;
array[4] = 1;
 
try {
    array[5] = 23;
} catch (e) {
    print(e.message);  // Array index out of range: 5
}
 
array[0] = "17";
print(array[0]);  // 17
 
array[0] = "wrong type";	//int[] 数组的行为像一个真正的 java int 数组。 但当我们试图添加非整数的值的数组时，Nashorn 会执行隐式类型转换。 字符串会自动转换为int
print(array[0]);  // 0
 
array[0] = "17.3";	//int[] 数组的行为像一个真正的 java int 数组。 但当我们试图添加非整数的值的数组时，Nashorn 会执行隐式类型转换。 字符串会自动转换为int
print(array[0]);  // 17



/**集合与For Each我们可以使用java的集合来代替数组。首先定义使用 Java.type定义一个java类型，而后根据需要*/
var ArrayList = Java.type('java.util.ArrayList');
var list = new ArrayList();
list.add('a');
list.add('b');
list.add('c');
 
for each (var el in list) print(el);  // a, b, c


/**为了遍历集合和数组中的元素，Nashorn 引入了 for each 语句。" +
"这就像是 Java 的 for 循环一样。这里是一个对集合元素进行遍历的例子，使用的是 : */
var map = new java.util.HashMap();
map.put('foo', 'val1');
map.put('bar', 'val2');
 
for each (var e in map.keySet()) print(e);  // foo, bar
 
for each (var e in map.values()) print(e);  // val1, val2

/** 
 * Lambda 表达式和 Streams
似乎大家都比较喜欢 Lambda 和 Streams —— Nashorn 也是！
虽然 ECMAScript 5.1 中缺少 Java 8 Lambda 表达式中的紧缩箭头的语法，
但我们可以在接受 Lambda 表达式的地方使用函数来替代。*/
var list2 = new java.util.ArrayList();
list2.add("ddd2");
list2.add("aaa2");
list2.add("bbb1");
list2.add("aaa1");
list2.add("bbb3");
list2.add("ccc");
list2.add("bbb2");
list2.add("ddd1");
 
list2
    .stream()
    .filter(function(el) {
        return el.startsWith("aaa");
    })
    .sorted()
    .forEach(function(el) {
        print(el);
    });
    // aaa1, aaa2

/** 扩展类
Java 的类型可以简单的通过 Java.extend 进行扩展，在下个例子你将在脚本中创建一个多线程示例 */
var Runnable = Java.type('java.lang.Runnable');
var Printer = Java.extend(Runnable, {
    run: function() {
        print('printed from a separate thread');
    }
});
 
var Thread = Java.type('java.lang.Thread');
new Thread(new Printer()).start();
 
new Thread(function() {
    print('printed from another thread');
}).start();
 
// printed from a separate thread
// printed from another thread

/** 参数重载
方法和函数可以使用点符号或方括号来进行调用。*/
var System = Java.type('java.lang.System');
System.out.println(10);              // 10
System.out["println"](11.0);         // 11.0
System.out["println(double)"](12);   // 12.0
/** 在使用重载的参数来调用方法时可以传递可选参数来确定具体调用了哪个方法，如 println(double)。*/

/**Java Beans
我们不需要常规的用 getter 或者 setter 来访问类成员属性，可直接用属性名简单访问 Java Bean 中的属性。例如：*/

var Date = Java.type('java.util.Date');
var date = new Date();
date.year += 1900;
print(date.year);  // 2014

/**函数语法
如果只是简单的一行函数我们可以不用大括号： */
function sqr(x) x * x;
print(sqr(3));    // 9

/**属性绑定
来自不同对象的属性可以绑定在一起：*/

var o1 = {};
var o2 = { foo: 'bar'};
 
Object.bindProperties(o1, o2);
 
print(o1.foo);    // bar
o1.foo = 'BAM';
print(o2.foo);    // BAM
/**字符串处理
我喜欢字符串裁剪.*/

print("   hehe".trimLeft());            // hehe
print("hehe    ".trimRight() + "he");   // hehehe

/**在哪里
以防忘记你在哪里: */

print(__FILE__, __LINE__, __DIR__);

/**Import 的范围
有时，这在一次性导入多个java 包时非常有用。我们可以使用JavaImporter并结合with，在with块范围内引用：**/

var imports = new JavaImporter(java.io, java.lang);
with (imports) {
    var file = new File(__FILE__);
    System.out.println(file.getAbsolutePath());
    // /path/to/my/script.js
}

/**数组转换
有些包时可以直接使用而不必引用 Java.type 或JavaImporter引入，如 java.util: */

var list = new java.util.ArrayList();
list.add("s1");
list.add("s2");
list.add("s3");
/** 如下的代码演示了将java list转换为JavaScript的数组：*/
var jsArray = Java.from(list);
print(jsArray);                                  // s1,s2,s3
print(Object.prototype.toString.call(jsArray));  // [object Array]
/**其他的方式： */
var javaArray = Java.to([3, 5, 7, 11], "int[]");


/**调用父类函数
在 JavaScript 中访问重载的成员会有一点点尴尬，因为 ECMAScript 没有类似 Java 的 super 关键字一样的东西。
所幸的是 Nashorn 有办法解决。
首先我们在 Java 代码中定义一个超类：
*/
/**class SuperRunner implements Runnable {
    @Override
    public void run() {
        System.out.println("super run");
    }
}*/
/**接下来我们在 JavaScript 中重载 SuperRunner 。
 * 创建一个新的 Runner 实例时请注意 Nashorn 的扩展语法：其重载成员的语法是参考 Java 的匿名对象的做法。*/

var SuperRunner = Java.type('com.tsty.nashorn.javajs.SuperRunner');
var Runner = Java.extend(SuperRunner);
 
var runner = new Runner() {
    run: function() {
        Java.super(runner).run();
        print('on my run');
    }
}
runner.run();
 
// super run
// on my run

/**我们使用Java.super调用了重载方法 SuperRunner.run()。
 在JavaScript中执行其它脚本是十分容易的。我们可以load函数载入本地或远程的脚本.*/


/**在作者提供的的很多web前端中都使用了 Underscore.js ，因此在Nashorn中我们可以重用 Underscore： */
/**load('http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.6.0/underscore-min.js');
 
var odds = _.filter([1, 2, 3, 4, 5, 6], function (num) {
    return num % 2 == 1;
});
 
print(odds);  // 1, 3, 5 */
/**扩展脚本的执行是在同一个 JavaScript 上下文中，因此我们可以直接访问 underscore 变量。
 * 记住脚本的加载可能会因为变量名的重叠导致代码出问题。
*/
//loadWithNewGlobal('script.js');//我们可以通过将加载的脚本文件放置到一个新的全局上下文来解决这个问题：
/**命令行脚本
如果你对用 Java 编写命令行脚本很感兴趣的话，可以试试 Nake 。Nake 是一个为 Java 8 Nashorn 准备的简单 Make 工具。
你可以在 Nakefile 文件中定义任务，然后使用 nake — myTask 来运行任务。
任务使用 JavaScript 编写并通过 Nashorn 脚本模式运行，
因此你可以让你的终端应用完全利用 Java 8 API 和其他 Java 库强大的功能。
对 Java 开发者而言，编写命令行脚本从来没有如此简单过。*/